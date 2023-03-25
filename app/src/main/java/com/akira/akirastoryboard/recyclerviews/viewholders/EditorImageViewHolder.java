package com.akira.akirastoryboard.recyclerviews.viewholders;

import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemEditorImageBinding;

public class EditorImageViewHolder extends RecyclerView.ViewHolder {
  private ImageView image;

  public EditorImageViewHolder(ItemEditorImageBinding binding) {
    super(binding.getRoot());
    this.image = binding.iv;
  }
}
