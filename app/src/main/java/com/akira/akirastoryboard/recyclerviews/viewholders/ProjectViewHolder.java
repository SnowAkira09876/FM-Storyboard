package com.akira.akirastoryboard.recyclerviews.viewholders;

import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemProjectBinding;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import com.akira.akirastoryboard.recyclerviews.adapters.ProjectAdapter;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;
import java.io.File;

public class ProjectViewHolder extends RecyclerView.ViewHolder {
  private ItemProjectBinding binding;
  private ShapeableImageView iv_cover;

  public ProjectViewHolder(ItemProjectBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
    this.iv_cover = binding.projectImage;
  }

  public void bind(
      ProjectItemModel model, ProjectAdapter.ProjectItemClickListener listener, int position) {
    binding.setModel(model);
    binding.executePendingBindings();

    Picasso.get().load(Uri.fromFile(new File(model.getImagePath()))).into(iv_cover);

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
