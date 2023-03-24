package com.akira.akirastoryboard.activities.editor;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.activities.editor.EditorActivityPresenter;
import com.akira.akirastoryboard.activities.editor.EditorActivityView;
import com.akira.akirastoryboard.common.activity.BaseActivity;
import com.akira.akirastoryboard.databinding.ActivityEditorBinding;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.EditorAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;
import java.util.List;

public class EditorActivity extends BaseActivity<EditorActivityPresenter>
    implements EditorActivityView {
  private ActivityEditorBinding binding;
  private MaterialToolbar toolBar;
  private LinearLayoutManager lm;
  private RecyclerView rv;
  private EditorAdapter adapter;

  @Override
  protected EditorActivityPresenter createPresenter() {
    return new EditorActivityPresenter(this);
  }

  @Override
  protected void injectDependencies() {}

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.binding = ActivityEditorBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    FrameItemModel model =
        getIntent().getExtras() != null
            ? getParcelableExtraEachApi(getIntent(), "frame", FrameItemModel.class)
            : new FrameItemModel("", new ArrayList<CategoryItemModel>());

    this.lm = new LinearLayoutManager(this);
    this.adapter = AdapterFactory.getEditorAdapter();
    // Intent intent = new Intent();
    // intent.putExtra("frame", model);
    // setResult(RESULT_OK, intent);
    // finish();

    onsetViewBinding();
    onsetViews();
    presenter.getList();
  }

  @Override
  public void setList(List<CategoryItemModel> list) {
    adapter.submitList(list);
  }

  private void onsetViewBinding() {
    this.toolBar = binding.toolBar;
    this.rv = binding.rv;
  }

  private void onsetViews() {

    toolBar.setTitle("Frame 1");
    rv.setLayoutManager(lm);
    rv.setAdapter(adapter);
  }
}
