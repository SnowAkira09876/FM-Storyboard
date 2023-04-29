package com.akira.akirastoryboard.recyclerviews.diffutils;

import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.pojos.ProjectWithScenesModel;

public class ProjectDiffUtil extends DiffUtil.ItemCallback<ProjectWithScenesModel> {
  @Override
  public boolean areItemsTheSame(ProjectWithScenesModel oldItem, ProjectWithScenesModel newItem) {
    return oldItem.projectItemModel.getId() == newItem.projectItemModel.getId();
  }

  @Override
  public boolean areContentsTheSame(ProjectWithScenesModel oldItem, ProjectWithScenesModel newItem) {
    return oldItem.equals(newItem);
  }
}
