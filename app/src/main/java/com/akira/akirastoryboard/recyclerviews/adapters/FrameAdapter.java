package com.akira.akirastoryboard.recyclerviews.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.recyclerviews.BaseListAdapter;
import com.akira.akirastoryboard.databinding.ItemFrameBinding;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.akira.akirastoryboard.recyclerviews.viewholders.FrameViewHolder;

public class FrameAdapter extends BaseListAdapter<FrameItemModel, FrameViewHolder> {
  private FrameItemClickListener listener;
  private final RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();

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
    holder.bind(getItem(position), listener, pool);
  }

  public interface FrameItemClickListener {
    void onFrameLongClick(FrameItemModel model, View startView);
  }
}
