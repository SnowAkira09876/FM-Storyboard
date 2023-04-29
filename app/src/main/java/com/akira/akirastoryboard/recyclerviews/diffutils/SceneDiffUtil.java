package com.akira.akirastoryboard.recyclerviews.diffutils;

import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.pojos.SceneWithFramesModel;

public class SceneDiffUtil extends DiffUtil.ItemCallback<SceneWithFramesModel> {
  @Override
  public boolean areItemsTheSame(SceneWithFramesModel oldItem, SceneWithFramesModel newItem) {
    return oldItem.sceneItemModel.getId() == newItem.sceneItemModel.getId();
  }

  @Override
  public boolean areContentsTheSame(SceneWithFramesModel oldItem, SceneWithFramesModel newItem) {
    return oldItem.equals(newItem);
  }
}
