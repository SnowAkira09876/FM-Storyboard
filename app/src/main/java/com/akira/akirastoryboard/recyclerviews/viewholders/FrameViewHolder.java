package com.akira.akirastoryboard.recyclerviews.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemFrameBinding;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.akira.akirastoryboard.recyclerviews.adapters.FrameAdapter;

public class FrameViewHolder extends RecyclerView.ViewHolder {
  private ItemFrameBinding binding;

  public FrameViewHolder(ItemFrameBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void bind(FrameAdapter.FrameItemClickListener listener, FrameItemModel model) {
    binding.setModel(model);
    binding.executePendingBindings();

    itemView.setOnLongClickListener(
        v -> {
          listener.onItemLongClick(model);
          return true;
        });
  }
}
