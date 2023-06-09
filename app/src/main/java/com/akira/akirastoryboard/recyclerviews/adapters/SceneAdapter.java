package com.akira.akirastoryboard.recyclerviews.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.databinding.ItemSceneBinding;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.akira.akirastoryboard.pojos.SceneWithFramesModel;
import com.akira.akirastoryboard.recyclerviews.BaseListAdapter;
import com.akira.akirastoryboard.recyclerviews.viewholders.SceneViewHolder;

public class SceneAdapter extends BaseListAdapter<SceneWithFramesModel, SceneViewHolder> {
  private SceneItemClickListener listener;

  public SceneAdapter(
      DiffUtil.ItemCallback<SceneWithFramesModel> diffUtil, SceneItemClickListener listener) {
    super(diffUtil);
    this.listener = listener;
  }

  @Override
  public SceneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new SceneViewHolder(
        ItemSceneBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(SceneViewHolder holder, int position) {
    holder.bind(getItem(position), listener, position);
  }

  public interface SceneItemClickListener {
    void onSceneClick(int position, SceneItemModel model, View view);

    void onSceneLongClick(int position, SceneItemModel model);
  }
}
