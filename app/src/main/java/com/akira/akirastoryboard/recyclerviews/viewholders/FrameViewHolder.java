package com.akira.akirastoryboard.recyclerviews.viewholders;

import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.databinding.ItemFrameBinding;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.akira.akirastoryboard.recyclerviews.AdapterFactory;
import com.akira.akirastoryboard.recyclerviews.adapters.CategoryAdapter;
import com.akira.akirastoryboard.recyclerviews.adapters.FrameAdapter;
import com.squareup.picasso.Picasso;
import com.akira.akirastoryboard.R;

public class FrameViewHolder extends RecyclerView.ViewHolder {
  private ImageView image;
  private RecyclerView rv;
  private LinearLayoutManager lm;
  private CategoryAdapter adapter;

  public FrameViewHolder(ItemFrameBinding binding) {
    super(binding.getRoot());
    this.lm = new LinearLayoutManager(itemView.getContext());
    ;
    this.adapter = AdapterFactory.getCategoryAdapter();

    this.image = binding.ivImage;
    this.rv = binding.rv;
  }

  public void bind(FrameItemModel model, FrameAdapter.FrameItemClickListener listener, RecyclerView.RecycledViewPool pool) {
    Picasso.get().load(R.drawable.sample).into(image);
		
	rv.setRecycledViewPool(pool);	
    rv.setLayoutManager(lm);
    rv.setAdapter(adapter);

    adapter.submitList(model.getCategories());

    image.setOnLongClickListener(
        v -> {
          listener.onFrameLongClick(model, itemView);
          return true;
        });
  }
}
