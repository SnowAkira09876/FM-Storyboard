package com.akira.akirastoryboard.recyclerviews.viewholders;

import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemEditorImageBinding;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.akira.akirastoryboard.recyclerviews.adapters.EditorAdapter;

public class EditorImageViewHolder extends RecyclerView.ViewHolder {
  private ImageView image;

  public EditorImageViewHolder(ItemEditorImageBinding binding) {
    super(binding.getRoot());
    this.image = binding.ivFrame;
  }

  public void bind(
      CategoryItemModel model, EditorAdapter.EditorItemClickListener listener, int position) {
    itemView.setOnClickListener(
        v -> {
          listener.onImageClick(position, model);
        });
  }
}
