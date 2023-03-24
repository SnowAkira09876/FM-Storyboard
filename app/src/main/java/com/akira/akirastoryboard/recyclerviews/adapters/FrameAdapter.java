package com.akira.akirastoryboard.recyclerviews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.common.recyclerview.BaseListAdapter;
import com.akira.akirastoryboard.databinding.ItemFrameBinding;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.akira.akirastoryboard.recyclerviews.viewholders.FrameViewHolder;

public class FrameAdapter<T extends FrameItemModel>
    extends BaseListAdapter<FrameItemModel, FrameViewHolder> {
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
    holder.bind(getItem(position), listener);
  }

  public interface FrameItemClickListener {
    void onFrameLongClick(FrameItemModel model);
  }
}
