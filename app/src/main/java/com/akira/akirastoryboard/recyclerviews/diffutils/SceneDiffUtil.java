package com.akira.akirastoryboard.recyclerviews.diffutils;

import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.pojos.SceneItemModel;

public class SceneDiffUtil extends DiffUtil.ItemCallback<SceneItemModel> {
  @Override
  public boolean areItemsTheSame(SceneItemModel oldItem, SceneItemModel newItem) {
    return oldItem.getId() == newItem.getId();
  }

  @Override
  public boolean areContentsTheSame(SceneItemModel oldItem, SceneItemModel newItem) {
    return oldItem.equals(newItem);
  }
}
