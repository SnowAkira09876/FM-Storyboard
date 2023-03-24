package com.akira.akirastoryboard.recyclerviews.viewholders;

import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemGroupedSceneBinding;
import com.akira.akirastoryboard.databinding.ItemSceneBinding;
import com.akira.akirastoryboard.pojos.GroupedSceneItemModel;
import com.akira.akirastoryboard.recyclerviews.adapters.GroupedSceneAdapter;
import com.akira.akirastoryboard.recyclerviews.adapters.SceneAdapter;

public class GroupedSceneViewHolder extends RecyclerView.ViewHolder {
  private TextView title, scenes, category;

  public GroupedSceneViewHolder(ItemGroupedSceneBinding binding) {
    super(binding.getRoot());
    this.title = binding.groupedSceneTitle;
    this.scenes = binding.groupedSceneScenes;
    this.category = binding.groupedSceneCategory;
  }

  public void bind(
      GroupedSceneItemModel model,
      GroupedSceneAdapter.GroupedSceneItemClickListener listener,
      int position) {
    title.setText(model.getTitle());
    scenes.setText(model.getScenes());
    category.setText(model.getCategory());

    itemView.setOnClickListener(
        v -> {
          listener.onGroupClick(position, model);
        });
  }
}
