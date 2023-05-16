package com.akira.akirastoryboard.activities.frame;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.view.WindowCompat;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import android.view.View;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.akira.akirastoryboard.R;
import com.akira.akirastoryboard.StartApplication;
import com.akira.akirastoryboard.bottomsheets.frame.AddFrameBottomSheet;
import com.akira.akirastoryboard.bottomsheets.frame.EditFrameBottomSheet;
import com.akira.akirastoryboard.databinding.ActivityFrameBinding;
import com.akira.akirastoryboard.di.AppComponent;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.FrameAdapter;
import com.akira.akirastoryboard.widgets.recyclerview.AkiraRecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.platform.MaterialSharedAxis;

public class FrameActivity extends AppCompatActivity
    implements FrameAdapter.FrameItemClickListener, ActionMode.Callback {
  private ActivityFrameBinding binding;
  private LinearLayoutManager lm;
  private AkiraRecyclerView rv;
  private FrameAdapter adapter;
  private MaterialToolbar toolbar;
  private FrameActivityViewModel viewModel;
  private TextView emptyView;
  private FloatingActionButton fab;
  private FrameActivityVMFactory viewModelFactory;
  private AppComponent component;
  private SceneItemModel sceneItemModel;
  private AppBarLayout appbar;
  private FrameItemModel selected_model;

  @SuppressWarnings("deprecation")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
    setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
    MaterialContainerTransform transform = new MaterialContainerTransform();
    View root = findViewById(android.R.id.content);
    root.setBackgroundColor(SurfaceColors.SURFACE_0.getColor(this));
    root.setTransitionName(getString(R.string.transform_scene_to_frame));

    transform.addTarget(root);

    getWindow().setSharedElementEnterTransition(transform);
    super.onCreate(savedInstanceState);

    this.component = StartApplication.getAppComponent();
    this.viewModelFactory = component.getFrameActivityVMFactory();
    this.viewModel =
        new ViewModelProvider(this, viewModelFactory).get(FrameActivityViewModel.class);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
      this.sceneItemModel =
          getIntent().getExtras() != null
              ? getIntent().getExtras().getParcelable("scene", SceneItemModel.class)
              : null;
    else
      this.sceneItemModel =
          getIntent().getExtras() != null ? getIntent().getExtras().getParcelable("scene") : null;

    this.binding = ActivityFrameBinding.inflate(getLayoutInflater());
    this.lm = new LinearLayoutManager(this);
    this.adapter = AdapterFactory.getFrameAdapter(this);
    onsetViewBinding();
    onsetViews();
    onsetViewModels();
  }

  @Override
  public void onItemLongClick(FrameItemModel model) {
    this.selected_model = model;
    startSupportActionMode(this);
  }

  @Override
  public boolean onCreateActionMode(ActionMode mode, Menu menu) {
    mode.getMenuInflater().inflate(R.menu.contextual_menu_frame, menu);
    mode.setTitle("Frame " + selected_model.getNumber());
    return true;
  }

  @Override
  public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    return false;
  }

  @Override
  public boolean onActionItemClicked(ActionMode mode, MenuItem menu) {
    switch (menu.getItemId()) {
      case R.id.menu_frame_edit:
        Bundle bundle = new Bundle();
        bundle.putParcelable("frame", selected_model);

        EditFrameBottomSheet bottomSheetDialog = new EditFrameBottomSheet();
        bottomSheetDialog.setArguments(bundle);
        bottomSheetDialog.show(getSupportFragmentManager(), null);
        mode.finish();
        return true;

      case R.id.menu_frame_delete:
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Delete frame");
        builder.setMessage(
            "Are you sure you want to delete frame " + selected_model.getNumber() + "?");
        builder.setPositiveButton(
            "Delete",
            (DialogInterface dialog, int id) -> {
              viewModel.deleteFrame(selected_model);
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
    this.toolbar = binding.toolBar;
    this.rv = binding.rv;
    this.emptyView = binding.emptyView;
    this.fab = binding.fab;
    this.appbar = binding.appbar;
  }

  private void onsetViews() {
    setContentView(binding.getRoot());
    toolbar.setTitle(sceneItemModel.getTitle() + " Frames");

    rv.setLayoutManager(lm);
    rv.setAdapter(adapter);
    rv.setEmptyView(emptyView);

    fab.setOnClickListener(
        v -> {
          Bundle bundle = new Bundle();
          bundle.putString("sceneId", sceneItemModel.getSceneId());

          AddFrameBottomSheet addFrameBottomSheet = new AddFrameBottomSheet();
          addFrameBottomSheet.setArguments(bundle);
          addFrameBottomSheet.show(getSupportFragmentManager(), null);
        });
  }

  private void onsetViewModels() {
    viewModel
        .getFrames(sceneItemModel.getSceneId())
        .observe(
            this,
            list -> {
              adapter.submitList(list);
            });

    viewModel
        .getNewFrame()
        .observe(
            this,
            model -> {
              viewModel.addFrame(model);
            });

    viewModel
        .getUpdateFrame()
        .observe(
            this,
            model -> {
              viewModel.updateFrame(model);
            });
  }
}
