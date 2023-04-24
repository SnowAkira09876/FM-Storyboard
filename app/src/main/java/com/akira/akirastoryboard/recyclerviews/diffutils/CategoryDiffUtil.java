package com.akira.akirastoryboard.recyclerviews.diffutils;

import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.pojos.CategoryItemModel;

public class CategoryDiffUtil extends DiffUtil.ItemCallback<CategoryItemModel> {
  @Override
  public boolean areItemsTheSame(CategoryItemModel oldItem, CategoryItemModel newItem) {
    return oldItem.getTitle() == newItem.getTitle();
  }

  @Override
  public boolean areContentsTheSame(CategoryItemModel oldItem, CategoryItemModel newItem) {
    return oldItem.equals(newItem);
  }
}
