package com.akira.akirastoryboard.activities.frame;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.activities.editor.EditorActivity;
import com.akira.akirastoryboard.databinding.ActivityFrameBinding;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.FrameAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.akira.akirastoryboard.R;

public class FrameActivity extends AppCompatActivity
    implements ActionMode.Callback, FrameAdapter.FrameItemClickListener {
  private ActivityFrameBinding binding;
  private LinearLayoutManager lm;
  private RecyclerView rv;
  private FrameAdapter adapter;
  private MaterialToolbar toolbar;
  private String toolbar_title = "";
  private FrameItemModel model;
  private View startView;
  private final FrameActivityViewModel viewModel = new FrameActivityViewModel();

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
    onsetViewModels();
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

  private void onsetViewModels() {
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
        Intent intent = new Intent(this, EditorActivity.class);
        startActivity(intent);
        mode.finish();
        return true;
    }
    return false;
  }

  @Override
  public void onDestroyActionMode(ActionMode mode) {}

  @Override
  public void onFrameLongClick(FrameItemModel model, View startView) {
    this.model = model;
    this.startView = startView;
    startSupportActionMode(this);
  }
}
