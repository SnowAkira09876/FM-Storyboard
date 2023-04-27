package com.akira.akirastoryboard.activities.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.*;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.akira.akirastoryboard.StartApplication;
import com.akira.akirastoryboard.activities.AkiraActivity;
import com.akira.akirastoryboard.activities.scene.SceneActivity;
import com.akira.akirastoryboard.bottomsheets.project.AddProjectBottomSheet;
import com.akira.akirastoryboard.bottomsheets.project.EditProjectBottomSheet;
import com.akira.akirastoryboard.databinding.ActivityMainBinding;
import com.akira.akirastoryboard.di.AppComponent;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.ProjectAdapter;
import com.akira.akirastoryboard.widgets.recyclerview.AkiraRecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AkiraActivity implements ProjectAdapter.ProjectItemClickListener {
  private ActivityMainBinding binding;
  private LinearLayoutManager lm;
  private AkiraRecyclerView rv;
  private ProjectAdapter adapter;
  private MainActivityVMFactory viewModelfactory;
  private MainActivityViewModel viewModel;
  private FloatingActionButton fab;
  private TextView emptyView;
  private AppBarLayout appbar;
  private AppComponent component;
  private final int READ_STORAGE_PERMISSION_CODE = 1;
  private ActivityResultLauncher<Intent> launcher =
      registerForActivityResult(
          new ActivityResultContracts.StartActivityForResult(),
          result -> {
            if (result.getResultCode() == RESULT_OK) {
              Intent intent = result.getData();
              ProjectItemModel model;
              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                model = intent.getExtras().getParcelable("updated_model", ProjectItemModel.class);
              else model = intent.getExtras().getParcelable("updated_model");

              viewModel.setUpdateProject(model);
            }
          });

  @Override
  protected void onset() {
    this.component = StartApplication.getAppComponent();

    this.viewModelfactory = component.getMainActivityVMFactory();
    this.viewModel = new ViewModelProvider(this, viewModelfactory).get(MainActivityViewModel.class);

    this.binding = ActivityMainBinding.inflate(getLayoutInflater());

    this.lm = new LinearLayoutManager(this);
    this.adapter = AdapterFactory.getProjectAdapter(this);
    this.rv = binding.rv;
    this.fab = binding.fab;
    this.emptyView = binding.emptyView;
    this.appbar = binding.appbar;

    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(
          this,
          new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
          READ_STORAGE_PERMISSION_CODE);
    }
  }

  @Override
  protected void onsetViews() {
    setContentView(binding.getRoot());

    rv.setLayoutManager(lm);
    rv.setAdapter(adapter);
    rv.setEmptyView(emptyView);
    rv.setToolbarCollapsedWhenEmpty(appbar);

    fab.setOnClickListener(
        v -> {
          AddProjectBottomSheet project = new AddProjectBottomSheet();
          project.show(getSupportFragmentManager(), null);
        });
  }

  @Override
  protected void onsetViewModels() {
    viewModel
        .getProjects()
        .observe(
            this,
            list -> {
              adapter.submitList(list);
            });

    viewModel
        .getNewProject()
        .observe(
            this,
            model -> {
              viewModel.addProject(model);
            });

    viewModel
        .getUpdateProject()
        .observe(
            this,
            model -> {
              viewModel.updateProject(model);
            });

    viewModel
        .getDeleteProject()
        .observe(
            this,
            model -> {
              if (model.getScenes().equals("0 scenes")) viewModel.deleteProject(model);
              else
                Snackbar.make(
                        binding.getRoot(),
                        "Project can't be deleted if it contains scenes",
                        Snackbar.LENGTH_INDEFINITE)
                    .setAction("Okay", v -> {})
                    .show();
            });
  }

  @Override
  public void onProjectClick(int position, ProjectItemModel model) {
    Bundle bundle = new Bundle();
    bundle.putParcelable("project", model);

    launcher.launch(
        new Intent(this, SceneActivity.class).putExtras(bundle));
  }

  @Override
  public void onProjectLongClick(int position, ProjectItemModel model) {
    Bundle bundle = new Bundle();
    bundle.putParcelable("project", model);

    EditProjectBottomSheet project = new EditProjectBottomSheet();
    project.setArguments(bundle);
    project.show(getSupportFragmentManager(), null);
  }

  @Override
  public void onRequestPermissionsResult(
      int requestCode, String[] permissions, int[] grantResults) {
    if (requestCode == READ_STORAGE_PERMISSION_CODE) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        Snackbar.make(binding.getRoot(), "Akira Storyboard is ready", Snackbar.LENGTH_SHORT).show();
      } else {
        Snackbar.make(
                binding.getRoot(),
                "Akira Storyboard is still functional but you cannot put images",
                Snackbar.LENGTH_SHORT)
            .show();
      }
    }
  }
}
