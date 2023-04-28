package com.akira.akirastoryboard.recyclerviews.viewholders;

import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemProjectBinding;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import com.akira.akirastoryboard.recyclerviews.adapters.ProjectAdapter;
import com.google.android.material.card.MaterialCardView;

public class ProjectViewHolder extends RecyclerView.ViewHolder {
  private ItemProjectBinding binding;
  private MaterialCardView card;

  public ProjectViewHolder(ItemProjectBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
    this.card = binding.projectCard;
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

    binding.projectImage.setOnTouchListener(
        (View v, MotionEvent event) -> {
          if (event.getAction() == MotionEvent.ACTION_DOWN) listener.requestDrag(this);

          return true;
        });
  }

  public void onRowSelected() {
    card.setDragged(true);
  }

  public void onRowClear() {
    card.setDragged(false);
  }
}
