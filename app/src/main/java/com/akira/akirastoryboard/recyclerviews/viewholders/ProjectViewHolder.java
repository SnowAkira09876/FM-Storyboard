package com.akira.akirastoryboard.recyclerviews.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemProjectBinding;
import com.akira.akirastoryboard.pojos.ProjectWithScenesModel;
import com.akira.akirastoryboard.recyclerviews.adapters.ProjectAdapter;

public class ProjectViewHolder extends RecyclerView.ViewHolder {
  private ItemProjectBinding binding;

  public ProjectViewHolder(ItemProjectBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void bind(
      ProjectWithScenesModel model,
      ProjectAdapter.ProjectItemClickListener listener,
      int position) {
    binding.setModel(model.projectItemModel);
    binding.setListSize(model.scenes.size());
    binding.executePendingBindings();

    itemView.setOnClickListener(
        v -> {
          listener.onProjectClick(position, model.projectItemModel);
        });

    itemView.setOnLongClickListener(
        (v) -> {
          listener.onProjectLongClick(position, model.projectItemModel);
          return true;
        });
  }
}
