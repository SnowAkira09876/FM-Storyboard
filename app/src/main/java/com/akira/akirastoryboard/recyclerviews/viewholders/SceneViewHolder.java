package com.akira.akirastoryboard.recyclerviews.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemSceneBinding;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.akira.akirastoryboard.recyclerviews.adapters.SceneAdapter;
import com.squareup.picasso.Picasso;
import com.akira.akirastoryboard.R;

public class SceneViewHolder extends RecyclerView.ViewHolder {
  private ItemSceneBinding binding;

  public SceneViewHolder(ItemSceneBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void bind(
      SceneItemModel model, SceneAdapter.SceneItemClickListener listener, int position) {
    Picasso.get().load(R.drawable.sample).into(binding.sceneImage);
    binding.setModel(model);
    binding.executePendingBindings();

    itemView.setOnClickListener(
        v -> {
          listener.onSceneClick(position, model);
        });
  }
}
