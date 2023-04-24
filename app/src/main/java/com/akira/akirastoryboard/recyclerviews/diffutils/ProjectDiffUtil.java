package com.akira.akirastoryboard.recyclerviews.diffutils;

import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.pojos.ProjectItemModel;

public class ProjectDiffUtil extends DiffUtil.ItemCallback<ProjectItemModel> {
  @Override
  public boolean areItemsTheSame(ProjectItemModel oldItem, ProjectItemModel newItem) {
    return oldItem.getId() == newItem.getId();
  }

  @Override
  public boolean areContentsTheSame(ProjectItemModel oldItem, ProjectItemModel newItem) {
    return oldItem.equals(newItem);
  }
}
