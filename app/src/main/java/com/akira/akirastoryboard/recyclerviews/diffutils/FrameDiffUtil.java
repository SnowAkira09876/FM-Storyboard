package com.akira.akirastoryboard.recyclerviews.diffutils;

import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import java.util.Objects;

public class FrameDiffUtil extends DiffUtil.ItemCallback<FrameItemModel> {
  @Override
  public boolean areItemsTheSame(FrameItemModel oldItem, FrameItemModel newItem) {
    return oldItem.getCategories() == newItem.getCategories();
  }

  @Override
  public boolean areContentsTheSame(FrameItemModel oldItem, FrameItemModel newItem) {
    return Objects.equals(oldItem, newItem);
  }
}
