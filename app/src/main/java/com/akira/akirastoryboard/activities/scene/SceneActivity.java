package com.akira.akirastoryboard.activities.scene;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.akira.akirastoryboard.R;
import com.akira.akirastoryboard.StartApplication;
import com.akira.akirastoryboard.activities.AkiraActivity;
import com.akira.akirastoryboard.activities.frame.FrameActivity;
import com.akira.akirastoryboard.bottomsheets.scene.AddSceneBottomSheet;
import com.akira.akirastoryboard.bottomsheets.scene.EditSceneBottomSheet;
import com.akira.akirastoryboard.databinding.ActivitySceneBinding;
import com.akira.akirastoryboard.di.AppComponent;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.SceneAdapter;
import com.akira.akirastoryboard.widgets.recyclerview.AkiraRecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class SceneActivity extends AkiraActivity implements SceneAdapter.SceneItemClickListener {
  private ActivitySceneBinding binding;
  private LinearLayoutManager lm;
  private AkiraRecyclerView rv;
  private SceneAdapter adapter;
  private MaterialToolbar toolbar;
  private String toolbar_title = "";
  private FloatingActionButton fab;
  private TextView emptyView;
  private AppBarLayout appbar;
  private ProjectItemModel model;
  private SceneActivityViewModel viewModel;
  private SceneActivityVMFactory viewModelFactory;
  private AppComponent component;
  private ActivityResultLauncher<Intent> launcher =
      registerForActivityResult(
          new ActivityResultContracts.StartActivityForResult(),
          result -> {
            if (result.getResultCode() == RESULT_OK) {
              Intent intent = result.getData();
              SceneItemModel model;
              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                model = intent.getExtras().getParcelable("updated_model", SceneItemModel.class);
              else model = intent.getExtras().getParcelable("updated_model");

              viewModel.setUpdateScene(model);
            }
          });

  @Override
  public void onSceneClick(int position, SceneItemModel model) {
    Bundle bundle = new Bundle();
    bundle.putParcelable("scene", model);

    launcher.launch(new Intent(this, FrameActivity.class).putExtras(bundle));
  }

  @Override
  public void onSceneLongClick(int position, SceneItemModel model) {
    Bundle bundle = new Bundle();
    bundle.putParcelable("scene", model);

    EditSceneBottomSheet bottomSheetDialog = new EditSceneBottomSheet();
    bottomSheetDialog.setArguments(bundle);
    bottomSheetDialog.show(getSupportFragmentManager(), null);
  }

  @SuppressWarnings("deprecation")
  @Override
  protected void onset() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
      this.model =
          getIntent().getExtras() != null
              ? getIntent().getExtras().getParcelable("project", ProjectItemModel.class)
              : null;
    else
      this.model =
          getIntent().getExtras() != null ? getIntent().getExtras().getParcelable("project") : null;

    this.component = StartApplication.getAppComponent();

    this.viewModelFactory = component.getSceneActivityVMFactory();
    this.viewModel =
        new ViewModelProvider(this, viewModelFactory).get(SceneActivityViewModel.class);

    this.binding = ActivitySceneBinding.inflate(getLayoutInflater());

    this.lm = new LinearLayoutManager(this);
    this.adapter = AdapterFactory.getSceneAdapter(this);
    this.toolbar_title = model != null ? model.getTitle() : getString(R.string.app_name);

    this.rv = binding.rv;
    this.toolbar = binding.toolBar;
    this.fab = binding.fab;
    this.emptyView = binding.emptyView;
    this.appbar = binding.appbar;
  }

  @Override
  protected void onsetViews() {
    setContentView(binding.getRoot());
    toolbar.setTitle(toolbar_title + " Scenes");
    rv.setLayoutManager(lm);
    rv.setAdapter(adapter);
    rv.setEmptyView(emptyView);
    rv.setToolbarCollapsedWhenEmpty(appbar);

    fab.setOnClickListener(
        v -> {
          Bundle bundle = new Bundle();
          bundle.putString("projectId", model.getProjectId());

          AddSceneBottomSheet bottomSheetDialog = new AddSceneBottomSheet();
          bottomSheetDialog.setArguments(bundle);
          bottomSheetDialog.show(getSupportFragmentManager(), null);
        });
  }

  @Override
  protected void onsetViewModels() {
    viewModel
        .getScenes(model.getProjectId())
        .observe(
            this,
            list -> {
              adapter.submitList(list);
            });

    viewModel
        .getNewScene()
        .observe(
            this,
            model -> {
              viewModel.addScene(model);
            });

    viewModel
        .getUpdateScene()
        .observe(
            this,
            model -> {
              viewModel.updateScene(model);
            });

    viewModel
        .getDeleteScene()
        .observe(
            this,
            model -> {
              if (model.getFrames().equals("0 frames")) viewModel.deleteScene(model);
              else
                Snackbar.make(
                        binding.getRoot(),
                        "Scene can't be deleted if it contains frames",
                        Snackbar.LENGTH_INDEFINITE)
                    .setAction("Okay", v -> {})
                    .show();
            });
  }

  @Override
  public void onBackPressed() {
    Bundle bundle = new Bundle();
    model.setScenes(String.valueOf(adapter.getItemCount()) + " scenes");
    bundle.putParcelable("updated_model", this.model);

    Intent resultIntent = new Intent();
    resultIntent.putExtras(bundle);

    setResult(RESULT_OK, resultIntent);
    super.onBackPressed();
  }
}
