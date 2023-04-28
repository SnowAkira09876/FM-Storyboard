package com.akira.akirastoryboard.recyclerviews.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.recyclerviews.BaseListAdapter;
import com.akira.akirastoryboard.databinding.ItemFrameBinding;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.akira.akirastoryboard.recyclerviews.viewholders.FrameViewHolder;

public class FrameAdapter extends BaseListAdapter<FrameItemModel, FrameViewHolder> {
  private FrameItemClickListener listener;

  public FrameAdapter(
      DiffUtil.ItemCallback<FrameItemModel> diffUtil, FrameItemClickListener listener) {
    super(diffUtil);
    this.listener = listener;
  }

  @Override
  public FrameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new FrameViewHolder(
        ItemFrameBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(FrameViewHolder holder, int position) {
    holder.bind(listener, getItem(position));
  }

  @Override
  public void onRowMoved(int fromPosition, int toPosition) {}

  @Override
  public void onRowSelected(RecyclerView.ViewHolder viewHolder) {}

  @Override
  public void onRowClear(RecyclerView.ViewHolder viewHolder) {}

  public interface FrameItemClickListener {
    void onItemLongClick(FrameItemModel model);
  }
}
