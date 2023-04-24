package com.akira.akirastoryboard.activities.editor;

import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.akira.akirastoryboard.activities.AkiraActivity;
import com.akira.akirastoryboard.bottomsheets.category.AddCategoryBottomSheet;
import com.akira.akirastoryboard.bottomsheets.category.EditCategoryBottomSheet;
import com.akira.akirastoryboard.bottomsheets.editor.AddImageEditorBottomSheet;
import com.akira.akirastoryboard.databinding.ActivityEditorBinding;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.EditorAdapter;
import com.akira.akirastoryboard.widgets.recyclerview.AkiraRecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditorActivity extends AkiraActivity implements EditorAdapter.EditorItemClickListener {
  private ActivityEditorBinding binding;
  private MaterialToolbar toolBar;
  private LinearLayoutManager lm;
  private AkiraRecyclerView rv;
  private EditorAdapter adapter;
  private final EditorActivityViewModel viewModel = new EditorActivityViewModel();
  private FloatingActionButton fab;
  private TextView emptyView;
  private AppBarLayout appbar;

  @Override
  public void onCategoryClick(int position, CategoryItemModel model) {
    new EditCategoryBottomSheet().show(getSupportFragmentManager(), null);
  }

  @Override
  public void onImageClick(int position, CategoryItemModel model) {
    new AddImageEditorBottomSheet().show(getSupportFragmentManager(), null);
  }

  @Override
  protected void onset() {
    this.binding = ActivityEditorBinding.inflate(getLayoutInflater());
    this.lm = new LinearLayoutManager(this);
    this.adapter = AdapterFactory.getEditorAdapter(this);

    this.toolBar = binding.toolBar;
    this.rv = binding.rv;
    this.fab = binding.fab;
    this.emptyView = binding.emptyView;
    this.appbar = binding.appbar;
  }

  @Override
  protected void onsetViews() {
    setContentView(binding.getRoot());

    toolBar.setTitle("Frame 1");
    rv.setLayoutManager(lm);
    rv.setAdapter(adapter);
    rv.setEmptyView(emptyView);
    rv.setToolbarCollapsedWhenEmpty(appbar);

    fab.setOnClickListener(
        v -> {
          new AddCategoryBottomSheet().show(getSupportFragmentManager(), null);
        });
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
}
