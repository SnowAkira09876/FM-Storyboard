package com.akira.akirastoryboard.activities.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.akira.akirastoryboard.R;
import com.akira.akirastoryboard.StartApplication;
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
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
    implements ProjectAdapter.ProjectItemClickListener,
        ActionMode.Callback {
  private ActivityMainBinding binding;
  private LinearLayoutManager lm;
  private AkiraRecyclerView rv;
  private ProjectAdapter adapter;
  private MainActivityVMFactory viewModelfactory;
  private MainActivityViewModel viewModel;
  private MaterialToolbar toolbar;
  private NavigationView navigationView;
  private FloatingActionButton fab;
  private TextView emptyView;
  private AppBarLayout appbar;
  private AppComponent component;
  private final int READ_STORAGE_PERMISSION_CODE = 1;
  private DrawerLayout drawerLayout;
  private ActionBarDrawerToggle toggle;
  private ProjectItemModel selected_model;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.component = StartApplication.getAppComponent();

    this.viewModelfactory = component.getMainActivityVMFactory();
    this.viewModel = new ViewModelProvider(this, viewModelfactory).get(MainActivityViewModel.class);
    this.binding = ActivityMainBinding.inflate(getLayoutInflater());

    this.lm = new LinearLayoutManager(this);
    this.adapter = AdapterFactory.getProjectAdapter(this);
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(
          this,
          new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
          READ_STORAGE_PERMISSION_CODE);
    }

    onsetViewBinding();
    onsetViews();
    onsetViewModels();
  }

  @Override
  public void onProjectClick(int position, ProjectItemModel model) {
    Bundle bundle = new Bundle();
    bundle.putParcelable("project", model);

    startActivity(new Intent(this, SceneActivity.class).putExtras(bundle));
  }

  @Override
  public void onProjectLongClick(int position, ProjectItemModel model) {
    this.selected_model = model;
    startSupportActionMode(this);
  }

  @Override
  public void onRequestPermissionsResult(
      int requestCode, String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if (requestCode == READ_STORAGE_PERMISSION_CODE) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        Snackbar.make(binding.activityRoot, "FM Storyboard is ready", Snackbar.LENGTH_SHORT)
            .show();
      } else {
        Snackbar.make(
                binding.activityRoot,
                "FM Storyboard is still functional but you cannot put images",
                Snackbar.LENGTH_SHORT)
            .show();
      }
    }
  }

  @Override
  public boolean onCreateActionMode(ActionMode mode, Menu menu) {
    mode.getMenuInflater().inflate(R.menu.contextual_menu_project, menu);
    mode.setTitle(selected_model.getTitle());
    mode.setSubtitle(String.valueOf(selected_model.getNumber()));

    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.START);
    return true;
  }

  @Override
  public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    return false;
  }

  @Override
  public boolean onActionItemClicked(ActionMode mode, MenuItem menu) {
    switch (menu.getItemId()) {
      case R.id.menu_project_edit:
        Bundle bundle = new Bundle();
        bundle.putParcelable("project", selected_model);

        EditProjectBottomSheet project = new EditProjectBottomSheet();
        project.setArguments(bundle);
        project.show(getSupportFragmentManager(), null);
        mode.finish();
        return true;

      case R.id.menu_project_delete:
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Delete project");
        builder.setMessage(
            "Are you sure you want to delete " + selected_model.getTitle() + " project?");
        builder.setPositiveButton(
            "Delete",
            (DialogInterface dialog, int id) -> {
              viewModel.deleteProject(selected_model);
            });

        builder.setNegativeButton("Cancel", (DialogInterface dialog, int id) -> {});

        builder.create().show();

        mode.finish();
        return true;
    }

    return false;
  }

  @Override
  public void onDestroyActionMode(ActionMode mode) {

    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.START);
  }

  private void onsetViewBinding() {
    this.rv = binding.rv;
    this.fab = binding.fab;
    this.emptyView = binding.emptyView;
    this.appbar = binding.appbar;
    this.toolbar = binding.toolBar;
    this.navigationView = binding.navigationView;
    this.drawerLayout = binding.drawerLayout;
  }

  private void onsetViews() {
    setContentView(binding.getRoot());

    rv.setLayoutManager(lm);
    rv.setAdapter(adapter);
    rv.setEmptyView(emptyView);

    fab.setOnClickListener(
        v -> {
          new AddProjectBottomSheet().show(getSupportFragmentManager(), null);
        });

    this.toggle =
        new ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    navigationView.setNavigationItemSelectedListener(
        menuItem -> {
          switch (menuItem.getItemId()) {
            case R.id.drawer_info_facebook:
              openLink("https://www.facebook.com/profile.php?id=100087796637987");
              return true;

            case R.id.drawer_info_guide:
              openLink("https://www.facebook.com/profile.php?id=100087796637987");
              return true;

            case R.id.drawer_info_feed:
              openFeedBack();
              return true;

            case R.id.drawer_info_source:
              openLink("https://github.com/SnowAkira09876");
              return true;

            case R.id.drawer_info_policy:
              openLink(
                  "https://www.freeprivacypolicy.com/live/99ad50d9-2235-40f1-a9ad-9b28d7735815");
              return true;
          }
          return false;
        });
  }

  private void onsetViewModels() {
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
  }

  private void openLink(String url) {
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    startActivity(intent);
  }

  private void openFeedBack() {
    String email = "snowakira2814@gmail.com";
    Intent intent = new Intent(Intent.ACTION_SENDTO);
    intent.setData(Uri.parse("mailto:" + email));
    startActivity(intent);
  }
}
