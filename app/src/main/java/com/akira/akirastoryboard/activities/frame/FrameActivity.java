package com.akira.akirastoryboard.activities.frame;

import android.content.Intent;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.TextView;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.akira.akirastoryboard.activities.AkiraActivity;
import com.akira.akirastoryboard.activities.editor.EditorActivity;
import com.akira.akirastoryboard.databinding.ActivityFrameBinding;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.FrameAdapter;
import com.akira.akirastoryboard.widgets.recyclerview.AkiraRecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.akira.akirastoryboard.R;

public class FrameActivity extends AkiraActivity
    implements ActionMode.Callback, FrameAdapter.FrameItemClickListener {
  private ActivityFrameBinding binding;
  private LinearLayoutManager lm;
  private AkiraRecyclerView rv;
  private FrameAdapter adapter;
  private MaterialToolbar toolbar;
  private String toolbar_title = "";
  private FrameItemModel model;
  private final FrameActivityViewModel viewModel = new FrameActivityViewModel();
  private TextView emptyView;

  @Override
  protected void onset() {
    this.binding = ActivityFrameBinding.inflate(getLayoutInflater());
    this.lm = new LinearLayoutManager(this);
    this.adapter = AdapterFactory.getFrameAdapter(this);
    this.toolbar_title =
        getIntent().getExtras() != null
            ? getIntent().getExtras().getString("title")
            : getString(R.string.app_name);
    this.toolbar = binding.toolBar;
    this.rv = binding.rv;
    this.emptyView = binding.emptyView;
  }

  @Override
  protected void onsetViews() {
    setContentView(binding.getRoot());
    toolbar.setTitle(toolbar_title + " Frames");
    lm.setInitialPrefetchItemCount(5);
    rv.setLayoutManager(lm);
    rv.setAdapter(adapter);
    rv.setEmptyView(emptyView);
  }

  @Override
  protected void onsetViewModels() {
    viewModel
        .getList()
        .observe(
            this,
            list -> {
              adapter.submitList(list);
            });
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
		mode.finish();	
        Intent intent = new Intent(this, EditorActivity.class);
        startActivity(intent);

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
