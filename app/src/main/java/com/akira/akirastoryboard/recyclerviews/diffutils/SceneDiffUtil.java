package com.akira.akirastoryboard.recyclerviews.diffutils;

import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import java.util.Objects;

public class SceneDiffUtil extends DiffUtil.ItemCallback<SceneItemModel> {
  @Override
  public boolean areItemsTheSame(SceneItemModel oldItem, SceneItemModel newItem) {
    return oldItem.getTitle() == newItem.getTitle();
  }

  @Override
  public boolean areContentsTheSame(SceneItemModel oldItem, SceneItemModel newItem) {
    return Objects.equals(oldItem, newItem);
  }
}
