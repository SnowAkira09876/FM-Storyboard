package com.akira.akirastoryboard.activities.scene;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.activities.frame.FrameActivity;
import com.akira.akirastoryboard.databinding.ActivitySceneBinding;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.SceneAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.akira.akirastoryboard.R;

public class SceneActivity extends AppCompatActivity
    implements SceneAdapter.SceneItemClickListener {
  private ActivitySceneBinding binding;
  private LinearLayoutManager lm;
  private RecyclerView rv;
  private SceneAdapter adapter;
  private MaterialToolbar toolbar;
  private String toolbar_title = "";
  private final SceneActivityViewModel viewModel = new SceneActivityViewModel();

  @Override
  public void onSceneClick(int position, SceneItemModel model) {
    Bundle bundle = new Bundle();
    bundle.putString("title", model.getTitle());
    startActivity(new Intent(this, FrameActivity.class).putExtras(bundle));
  }

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
    onsetViewModels();
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

  private void onsetViewModels() {
    viewModel
        .getList()
        .observe(
            this,
            list -> {
              adapter.submitList(list);
            });
  }
}
