package com.akira.akirastoryboard.activities.scene;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.akira.akirastoryboard.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

  @Override
  public void onSceneClick(int position, SceneItemModel model) {
    Bundle bundle = new Bundle();
    bundle.putString("title", model.getTitle());
    startActivity(new Intent(this, FrameActivity.class).putExtras(bundle));
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
          bundle.putInt("projectId", model.getId());
          AddSceneBottomSheet bottomSheetDialog = new AddSceneBottomSheet();
          bottomSheetDialog.setArguments(bundle);
          bottomSheetDialog.show(getSupportFragmentManager(), null);
        });
  }

  @Override
  protected void onsetViewModels() {
    viewModel
        .getScenes(model.getId())
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
              viewModel.deleteScene(model);
            });
  }

  @Override
  public void onBackPressed() {
    Bundle bundle = new Bundle();
    model.setScenes(String.valueOf(adapter.getItemCount()) + " scenes");
    bundle.putParcelable("updated_model", model);

    Intent resultIntent = new Intent();
    resultIntent.putExtras(bundle);

    setResult(RESULT_OK, resultIntent);
    super.onBackPressed();
  }
}
