package com.akira.akirastoryboard.activities.frame;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FrameActivity extends AppCompatActivity
    implements FrameAdapter.FrameItemClickListener {
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

  @SuppressWarnings("deprecation")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
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
    Bundle bundle = new Bundle();
    bundle.putParcelable("frame", model);

    EditFrameBottomSheet bottomSheetDialog = new EditFrameBottomSheet();
    bottomSheetDialog.setArguments(bundle);
    bottomSheetDialog.show(getSupportFragmentManager(), null);
  }

  @Override
  public void onBackPressed() {
    Bundle bundle = new Bundle();
    sceneItemModel.setFrames(String.valueOf(adapter.getItemCount()) + " frames");
    bundle.putParcelable("updated_model", this.sceneItemModel);

    Intent resultIntent = new Intent();
    resultIntent.putExtras(bundle);

    setResult(RESULT_OK, resultIntent);
    super.onBackPressed();
  }

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
    rv.setToolbarCollapsedWhenEmpty(appbar);

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

    viewModel
        .getDeleteFrame()
        .observe(
            this,
            model -> {
              viewModel.deleteFrame(model);
            });
  }
}
