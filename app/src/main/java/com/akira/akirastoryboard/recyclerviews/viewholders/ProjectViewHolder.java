package com.akira.akirastoryboard.recyclerviews.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemProjectBinding;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import com.akira.akirastoryboard.recyclerviews.adapters.ProjectAdapter;

public class ProjectViewHolder extends RecyclerView.ViewHolder {
  private ItemProjectBinding binding;

  public ProjectViewHolder(ItemProjectBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void bind(
      ProjectItemModel model, ProjectAdapter.ProjectItemClickListener listener, int position) {
    binding.setModel(model);
    binding.executePendingBindings();

    itemView.setOnClickListener(
        v -> {
          listener.onProjectClick(position, model);
        });

    itemView.setOnLongClickListener(
        (v) -> {
          listener.onProjectLongClick(position, model);
          return true;
        });
  }
}
