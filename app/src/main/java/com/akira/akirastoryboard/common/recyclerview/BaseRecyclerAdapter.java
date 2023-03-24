package com.akira.akirastoryboard.common.recyclerview;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {

  public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

  public abstract void onBindViewHolder(VH holder, int position);

  public abstract int getItemCount();
}
