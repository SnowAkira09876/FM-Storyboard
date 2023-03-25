package com.akira.akirastoryboard.recyclerviews.diffutils;

import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.pojos.GroupedSceneItemModel;
import java.util.Objects;

public class GroupedSceneDiffUtil extends DiffUtil.ItemCallback<GroupedSceneItemModel> {
  @Override
  public boolean areItemsTheSame(GroupedSceneItemModel oldItem, GroupedSceneItemModel newItem) {
    return oldItem.getTitle() == newItem.getTitle();
  }

  @Override
  public boolean areContentsTheSame(GroupedSceneItemModel oldItem, GroupedSceneItemModel newItem) {
    return Objects.equals(oldItem, newItem);
  }
}
