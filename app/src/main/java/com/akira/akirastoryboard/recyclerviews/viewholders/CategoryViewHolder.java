package com.akira.akirastoryboard.recyclerviews.viewholders;

import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemCategoryBinding;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import com.akira.akirastoryboard.recyclerviews.adapters.FrameAdapter;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
  private TextView title, desc;

  public CategoryViewHolder(ItemCategoryBinding binding) {
    super(binding.getRoot());
    this.title = binding.categoryTitle;
    this.desc = binding.categoryDescription;
  }

  public void bind(CategoryItemModel model) {
    title.setText(model.getTitle());
    desc.setText(model.getDescription());
  }
}
