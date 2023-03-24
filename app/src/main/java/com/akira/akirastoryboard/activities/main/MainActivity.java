package com.akira.akirastoryboard.activities.main;

import android.content.Intent;
import android.os.*;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.activities.scene.SceneActivity;
import com.akira.akirastoryboard.common.activity.BaseActivity;
import com.akira.akirastoryboard.databinding.ActivityMainBinding;
import com.akira.akirastoryboard.pojos.GroupedSceneItemModel;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.GroupedSceneAdapter;
import com.akira.akirastoryboard.recyclerviews.adapters.SceneAdapter;
import java.util.List;

public class MainActivity extends BaseActivity<MainActivityPresenter>
    implements MainActivityView, GroupedSceneAdapter.GroupedSceneItemClickListener {

  private ActivityMainBinding binding;
  private LinearLayoutManager lm;
  private RecyclerView rv;
  private GroupedSceneAdapter<GroupedSceneItemModel> adapter;

  @Override
  protected MainActivityPresenter createPresenter() {
    return new MainActivityPresenter(this);
  }

  @Override
  protected void injectDependencies() {}

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    this.lm = new LinearLayoutManager(this);
    this.adapter = AdapterFactory.getGroupedSceneAdapter(this);

    onsetViewBinding();
    onsetViews();

    presenter.getList();
  }

  private void onsetViewBinding() {
    this.rv = binding.rv;
  }

  private void onsetViews() {
    rv.setLayoutManager(lm);
    rv.setAdapter(adapter);
  }

  @Override
  public void onGroupClick(int position, GroupedSceneItemModel model) {
    Bundle bundle = new Bundle();
    bundle.putString("title", model.getTitle());
    startActivity(new Intent(this, SceneActivity.class).putExtras(bundle));
  }

  @Override
  public void setList(List<GroupedSceneItemModel> list) {
    adapter.submitList(list);
  }
}
