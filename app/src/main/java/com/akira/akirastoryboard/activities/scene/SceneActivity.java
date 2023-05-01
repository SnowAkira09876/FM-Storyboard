package com.akira.akirastoryboard.activities.scene;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.akira.akirastoryboard.R;
import com.akira.akirastoryboard.StartApplication;
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import com.google.android.material.transition.platform.MaterialFadeThrough;

public class SceneActivity extends AppCompatActivity
    implements SceneAdapter.SceneItemClickListener, ActionMode.Callback {
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
  private SceneItemModel selected_model;

  @Override
  public void onSceneClick(int position, SceneItemModel model) {
    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);

    Bundle bundle = new Bundle();
    bundle.putParcelable("scene", model);
    startActivity(new Intent(this, FrameActivity.class).putExtras(bundle), options.toBundle());
  }

  @Override
  public void onSceneLongClick(int position, SceneItemModel model) {
    this.selected_model = model;
    startSupportActionMode(this);
  }

  @SuppressWarnings("deprecation")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    MaterialContainerTransformSharedElementCallback container_callback =
        new MaterialContainerTransformSharedElementCallback();
    container_callback.setTransparentWindowBackgroundEnabled(false);

    MaterialContainerTransform enter = new MaterialContainerTransform();
    enter.addTarget(R.id.activity_root);
    enter.setAllContainerColors(ContextCompat.getColor(this, R.color.colorSurface));
    
    MaterialContainerTransform exit = new MaterialContainerTransform();
    exit.addTarget(R.id.activity_root);
    
    MaterialFadeThrough fade_enter = new MaterialFadeThrough();
    getWindow().setEnterTransition(enter);
    
    MaterialFadeThrough fade_exit = new MaterialFadeThrough();
    getWindow().setExitTransition(enter);
    
    getWindow().setSharedElementEnterTransition(enter);
    getWindow().setSharedElementExitTransition(enter);
    getWindow().setSharedElementsUseOverlay(false);
    
    getWindow().setExitTransition(fade_exit);
    getWindow().setEnterTransition(fade_enter);

    setEnterSharedElementCallback(container_callback);

    super.onCreate(savedInstanceState);

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

    onsetViewBinding();
    onsetViews();
    onsetViewModels();
  }

  @Override
  public boolean onCreateActionMode(ActionMode mode, Menu menu) {
    mode.getMenuInflater().inflate(R.menu.contextual_menu_scene, menu);
    mode.setTitle(selected_model.getTitle());
    mode.setSubtitle(String.valueOf(selected_model.getNumber()));
    return true;
  }

  @Override
  public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    return false;
  }

  @Override
  public boolean onActionItemClicked(ActionMode mode, MenuItem menu) {
    switch (menu.getItemId()) {
      case R.id.menu_scene_edit:
        Bundle bundle = new Bundle();
        bundle.putParcelable("scene", selected_model);

        EditSceneBottomSheet bottomSheetDialog = new EditSceneBottomSheet();
        bottomSheetDialog.setArguments(bundle);
        bottomSheetDialog.show(getSupportFragmentManager(), null);
        mode.finish();
        return true;

      case R.id.menu_scene_delete:
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Delete scene");
        builder.setMessage(
            "Are you sure you want to delete " + selected_model.getTitle() + " scene?");
        builder.setPositiveButton(
            "Delete",
            (DialogInterface dialog, int id) -> {
              viewModel.deleteScene(selected_model);
            });

        builder.setNegativeButton("Cancel", (DialogInterface dialog, int id) -> {});

        builder.create().show();

        mode.finish();
        return true;
    }

    return false;
  }

  @Override
  public void onDestroyActionMode(ActionMode mode) {}

  private void onsetViewBinding() {
    this.rv = binding.rv;
    this.toolbar = binding.toolBar;
    this.fab = binding.fab;
    this.emptyView = binding.emptyView;
    this.appbar = binding.appbar;
  }

  private void onsetViews() {
    setContentView(binding.getRoot());
    toolbar.setTitle(toolbar_title + " Scenes");
    rv.setLayoutManager(lm);
    rv.setAdapter(adapter);
    rv.setEmptyView(emptyView);

    fab.setOnClickListener(
        v -> {
          Bundle bundle = new Bundle();
          bundle.putString("projectId", model.getProjectId());

          AddSceneBottomSheet bottomSheetDialog = new AddSceneBottomSheet();
          bottomSheetDialog.setArguments(bundle);
          bottomSheetDialog.show(getSupportFragmentManager(), null);
        });
  }

  private void onsetViewModels() {
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
  }
}
