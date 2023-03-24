package com.akira.akirastoryboard.activities.scene;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.activities.frame.FrameActivity;
import com.akira.akirastoryboard.common.activity.BaseActivity;
import com.akira.akirastoryboard.databinding.ActivitySceneBinding;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.SceneAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.List;
import com.akira.akirastoryboard.R;

public class SceneActivity extends BaseActivity<SceneActivityPresenter>
    implements SceneActivityView, SceneAdapter.SceneItemClickListener {
  private ActivitySceneBinding binding;
  private LinearLayoutManager lm;
  private RecyclerView rv;
  private SceneAdapter adapter;
  private MaterialToolbar toolbar;
  private String toolbar_title = "";

  @Override
  public void onSceneClick(int position, SceneItemModel model) {
    Bundle bundle = new Bundle();
    bundle.putString("title", model.getTitle());
    startActivity(new Intent(this, FrameActivity.class).putExtras(bundle));
  }

  @Override
  public void setList(List<SceneItemModel> list) {
    adapter.submitList(list);
  }

  @Override
  protected SceneActivityPresenter createPresenter() {
    return new SceneActivityPresenter(this);
  }

  @Override
  protected void injectDependencies() {}

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.binding = ActivitySceneBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    this.lm = new LinearLayoutManager(this);
    this.adapter = AdapterFactory.getSceneAdapter(this);
    this.toolbar_title =
        getIntent().getExtras() != null
            ? getIntent().getExtras().getString("title")
            : getString(R.string.app_name);

    onsetViewBinding();
    onsetViews();

    presenter.getList();
  }

  private void onsetViewBinding() {
    this.rv = binding.rv;
    this.toolbar = binding.toolBar;
  }

  private void onsetViews() {
    toolbar.setTitle(toolbar_title + " Scenes");
    rv.setLayoutManager(lm);
    rv.setAdapter(adapter);
  }
}
