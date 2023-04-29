package com.akira.akirastoryboard.recyclerviews.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemSceneBinding;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.akira.akirastoryboard.pojos.SceneWithFramesModel;
import com.akira.akirastoryboard.recyclerviews.adapters.SceneAdapter;

public class SceneViewHolder extends RecyclerView.ViewHolder {
  private ItemSceneBinding binding;

  public SceneViewHolder(ItemSceneBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void bind(
      SceneWithFramesModel model, SceneAdapter.SceneItemClickListener listener, int position) {
    binding.setModel(model.sceneItemModel);
    binding.setListSize(model.frames.size());
    binding.executePendingBindings();

    itemView.setOnClickListener(
        v -> {
          listener.onSceneClick(position, model.sceneItemModel);
        });

    itemView.setOnLongClickListener(
        (v) -> {
          listener.onSceneLongClick(position, model.sceneItemModel);
          return true;
        });
  }
}
