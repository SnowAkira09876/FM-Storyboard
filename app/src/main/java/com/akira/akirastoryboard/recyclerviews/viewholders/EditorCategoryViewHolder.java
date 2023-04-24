package com.akira.akirastoryboard.recyclerviews.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemEditorCategoryBinding;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import com.akira.akirastoryboard.recyclerviews.adapters.EditorAdapter;

public class EditorCategoryViewHolder extends RecyclerView.ViewHolder {
  private ItemEditorCategoryBinding binding;

  public EditorCategoryViewHolder(ItemEditorCategoryBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void bind(CategoryItemModel model, EditorAdapter.EditorItemClickListener listener, int position) {
	binding.setModel(model);
	binding.executePendingBindings();	
		
	itemView.setOnClickListener(v->{
		listener.onCategoryClick(position, model);
	});	
  }
}
