package com.akira.akirastoryboard.common.recyclerview;

import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseListAdapter<T, VH extends RecyclerView.ViewHolder>
    extends ListAdapter<T, VH> {

  public BaseListAdapter(DiffUtil.ItemCallback<T> diff) {
    super(diff);
  }

  public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

  public abstract void onBindViewHolder(VH holder, int position);
}
