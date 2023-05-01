package com.akira.akirastoryboard.recyclerviews.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.databinding.ItemProjectBinding;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import com.akira.akirastoryboard.pojos.ProjectWithScenesModel;
import com.akira.akirastoryboard.recyclerviews.BaseListAdapter;
import com.akira.akirastoryboard.recyclerviews.viewholders.ProjectViewHolder;

public class ProjectAdapter extends BaseListAdapter<ProjectWithScenesModel, ProjectViewHolder> {
  private ProjectItemClickListener listener;

  public ProjectAdapter(
      DiffUtil.ItemCallback<ProjectWithScenesModel> diffUtil, ProjectItemClickListener listener) {
    super(diffUtil);
    this.listener = listener;
  }

  @Override
  public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ProjectViewHolder(
        ItemProjectBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(ProjectViewHolder holder, int position) {
    holder.bind(getItem(position), listener, position);
  }

  public interface ProjectItemClickListener {
    void onProjectClick(int position, ProjectItemModel model, View view);

    void onProjectLongClick(int position, ProjectItemModel model);
  }
}
