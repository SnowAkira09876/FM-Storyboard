package com.akira.akirastoryboard.activities.frame;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.activities.editor.EditorActivity;
import com.akira.akirastoryboard.common.activity.BaseActivity;
import com.akira.akirastoryboard.databinding.ActivityFrameBinding;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.FrameAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.List;
import com.akira.akirastoryboard.R;

public class FrameActivity extends BaseActivity<FrameActivityPresenter>
    implements FrameActivityView, ActionMode.Callback, FrameAdapter.FrameItemClickListener {
  private ActivityFrameBinding binding;
  private LinearLayoutManager lm;
  private RecyclerView rv;
  private FrameAdapter<FrameItemModel> adapter;
  private MaterialToolbar toolbar;
  private String toolbar_title = "";
  private FrameItemModel model;
  private ActivityResultLauncher<Intent> launcher =
      registerForActivityResult(
          new ActivityResultContracts.StartActivityForResult(),
          result -> {
            if (result.getResultCode() == RESULT_OK) {
              Intent data = result.getData();
              FrameItemModel receivedModel =
                  getParcelableExtraEachApi(data, "frame", FrameItemModel.class);
            }
          });

  @Override
  protected FrameActivityPresenter createPresenter() {
    return new FrameActivityPresenter(this);
  }

  @Override
  protected void injectDependencies() {}

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.binding = ActivityFrameBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    this.lm = new LinearLayoutManager(this);
    this.adapter = AdapterFactory.getFrameAdapter(this);
    this.toolbar_title =
        getIntent().getExtras() != null
            ? getIntent().getExtras().getString("title")
            : getString(R.string.app_name);

    onsetViewBinding();
    onsetViews();

    presenter.getList();
  }

  @Override
  public void setList(List<FrameItemModel> list) {
    adapter.submitList(list);
  }

  private void onsetViewBinding() {
    this.toolbar = binding.toolBar;
    this.rv = binding.rv;
  }

  private void onsetViews() {
    toolbar.setTitle(toolbar_title + " Frames");
    lm.setInitialPrefetchItemCount(5);
    rv.setLayoutManager(lm);
    rv.setAdapter(adapter);
  }

  @Override
  public boolean onCreateActionMode(ActionMode mode, Menu menu) {
    mode.getMenuInflater().inflate(R.menu.frame_contextual_toolbar_menu, menu);
    mode.setTitle("Edit");
    mode.setSubtitle("Frame 1");
    return true;
  }

  @Override
  public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    return false;
  }

  @Override
  public boolean onActionItemClicked(ActionMode mode, MenuItem menu) {
    switch (menu.getItemId()) {
      case R.id.contextual_edit:
        Intent intent = new Intent(this, EditorActivity.class);
        intent.putExtra("frame", model);
        launcher.launch(intent);
        return true;
    }
    return false;
  }

  @Override
  public void onDestroyActionMode(ActionMode mode) {}

  @Override
  public void onFrameLongClick(FrameItemModel model) {
    this.model = model;
    startSupportActionMode(this);
  }
}
