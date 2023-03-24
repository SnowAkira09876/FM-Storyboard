package com.akira.akirastoryboard.recyclerviews.viewholders;

import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemEditorCategoryBinding;
import com.akira.akirastoryboard.pojos.CategoryItemModel;

public class EditorCategoryViewHolder extends RecyclerView.ViewHolder {
  private TextView title, desc;

  public EditorCategoryViewHolder(ItemEditorCategoryBinding binding) {
    super(binding.getRoot());
    this.title = binding.categoryTitle;
    this.desc = binding.categoryDescription;
  }

  public void bind(CategoryItemModel model) {
    title.setText(model.getTitle());
    desc.setText(model.getDescription());
  }
}
