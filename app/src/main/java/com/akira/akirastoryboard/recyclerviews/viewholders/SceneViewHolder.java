package com.akira.akirastoryboard.recyclerviews.viewholders;

import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemSceneBinding;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.akira.akirastoryboard.recyclerviews.adapters.SceneAdapter;

public class SceneViewHolder extends RecyclerView.ViewHolder {
  private TextView title, time, frames;

  public SceneViewHolder(ItemSceneBinding binding) {
    super(binding.getRoot());
    this.title = binding.sceneTitle;
    this.time = binding.sceneTime;
    this.frames = binding.sceneFrames;
  }

  public void bind(
      SceneItemModel model, SceneAdapter.SceneItemClickListener listener, int position) {
    title.setText(model.getTitle());
    time.setText(model.getTime());
    frames.setText(model.getFrames());

    itemView.setOnClickListener(
        v -> {
          listener.onSceneClick(position, model);
        });
  }
}
