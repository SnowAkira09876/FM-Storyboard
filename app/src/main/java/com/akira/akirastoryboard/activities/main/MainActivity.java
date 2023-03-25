package com.akira.akirastoryboard.activities.main;

import android.content.Intent;
import android.os.*;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.activities.scene.SceneActivity;
import com.akira.akirastoryboard.databinding.ActivityMainBinding;
import com.akira.akirastoryboard.pojos.GroupedSceneItemModel;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.GroupedSceneAdapter;

public class MainActivity extends AppCompatActivity
    implements GroupedSceneAdapter.GroupedSceneItemClickListener {

  private ActivityMainBinding binding;
  private LinearLayoutManager lm;
  private RecyclerView rv;
  private GroupedSceneAdapter adapter;
  private final MainActivityViewModel viewModel = new MainActivityViewModel();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    this.lm = new LinearLayoutManager(this);
    this.adapter = AdapterFactory.getGroupedSceneAdapter(this);

    onsetViewBinding();
    onsetViews();
    onsetViewModels();
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
