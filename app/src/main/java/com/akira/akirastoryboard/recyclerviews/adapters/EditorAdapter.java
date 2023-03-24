package com.akira.akirastoryboard.recyclerviews.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.common.recyclerview.BaseListAdapter;
import com.akira.akirastoryboard.databinding.ItemEditorCategoryBinding;
import com.akira.akirastoryboard.databinding.ItemEditorImageBinding;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import com.akira.akirastoryboard.recyclerviews.viewholders.EditorCategoryViewHolder;
import com.akira.akirastoryboard.recyclerviews.viewholders.EditorImageViewHolder;

public class EditorAdapter extends BaseListAdapter<CategoryItemModel, RecyclerView.ViewHolder> {
  private static final int IMAGE_TYPE = 0;
  private static final int CATEGORY_TYPE = 1;

  public EditorAdapter(DiffUtil.ItemCallback<CategoryItemModel> diffUtil) {
    super(diffUtil);
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case IMAGE_TYPE:
        return new EditorImageViewHolder(
            ItemEditorImageBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));

      case CATEGORY_TYPE:
        return new EditorCategoryViewHolder(
            ItemEditorCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    return null;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    int viewType = getItemViewType(position);

    switch (viewType) {
      case IMAGE_TYPE:
        EditorImageViewHolder viewHolder0 = (EditorImageViewHolder) holder;
        CategoryItemModel model0 = getItem(position);
        break;

      case CATEGORY_TYPE:
        EditorCategoryViewHolder viewHolder1 = (EditorCategoryViewHolder) holder;
        CategoryItemModel model1 = getItem(position);

        viewHolder1.bind(model1);
        break;
    }
  }

  @Override
  public int getItemViewType(int position) {
    return position == 0 ? IMAGE_TYPE : CATEGORY_TYPE;
  }
}
