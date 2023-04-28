package com.akira.akirastoryboard.recyclerviews;

import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.recyclerviews.callbacks.ItemMoveCallback;

public abstract class BaseListAdapter<T, VH extends RecyclerView.ViewHolder>
    extends ListAdapter<T, VH> {

  public BaseListAdapter(DiffUtil.ItemCallback<T> diff) {
    super(diff);
  }

  public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

  public abstract void onBindViewHolder(VH holder, int position);

  public abstract void onRowMoved(int fromPosition, int toPosition);

  public abstract void onRowSelected(RecyclerView.ViewHolder viewHolder);

  public abstract void onRowClear(RecyclerView.ViewHolder viewHolder);
}
