package com.akira.akirastoryboard.recyclerviews.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.common.recyclerview.BaseListAdapter;
import com.akira.akirastoryboard.databinding.ItemCategoryBinding;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import com.akira.akirastoryboard.recyclerviews.viewholders.CategoryViewHolder;

public class CategoryAdapter extends BaseListAdapter<CategoryItemModel, CategoryViewHolder> {

  public CategoryAdapter(DiffUtil.ItemCallback<CategoryItemModel> diffUtil) {
    super(diffUtil);
  }

  @Override
  public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new CategoryViewHolder(
        ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(CategoryViewHolder holder, int position) {
    holder.bind(getItem(position));
  }
}
