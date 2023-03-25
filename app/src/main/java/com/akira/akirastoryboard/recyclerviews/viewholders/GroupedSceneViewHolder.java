package com.akira.akirastoryboard.recyclerviews.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemGroupedSceneBinding;
import com.akira.akirastoryboard.pojos.GroupedSceneItemModel;
import com.akira.akirastoryboard.recyclerviews.adapters.GroupedSceneAdapter;
import com.squareup.picasso.Picasso;
import com.akira.akirastoryboard.R;

public class GroupedSceneViewHolder extends RecyclerView.ViewHolder {
  private ItemGroupedSceneBinding binding;

  public GroupedSceneViewHolder(ItemGroupedSceneBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void bind(
      GroupedSceneItemModel model,
      GroupedSceneAdapter.GroupedSceneItemClickListener listener,
      int position) {
	binding.setModel(model);	
	binding.executePendingBindings();	
    Picasso.get().load(R.drawable.sample).into(binding.groupedSceneImage);
    itemView.setOnClickListener(
        v -> {
          listener.onGroupClick(position, model);
        });
  }
}
