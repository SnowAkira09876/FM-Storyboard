package com.akira.akirastoryboard.recyclerviews.callbacks;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.recyclerviews.BaseListAdapter;

public class ItemMoveCallback extends ItemTouchHelper.Callback {

  private final BaseListAdapter mAdapter;

  public ItemMoveCallback(BaseListAdapter mAdapter) {
    this.mAdapter = mAdapter;
  }

  @Override
  public boolean isLongPressDragEnabled() {
    return false;
  }

  @Override
  public boolean isItemViewSwipeEnabled() {
    return false;
  }

  @Override
  public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {}

  @Override
  public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
    return makeMovementFlags(dragFlags, 0);
  }

  @Override
  public boolean onMove(
      RecyclerView recyclerView,
      RecyclerView.ViewHolder viewHolder,
      RecyclerView.ViewHolder target) {
    mAdapter.onRowMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    return true;
  }

  @Override
  public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
    if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) mAdapter.onRowSelected(viewHolder);

    super.onSelectedChanged(viewHolder, actionState);
  }

  @Override
  public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    super.clearView(recyclerView, viewHolder);
    mAdapter.onRowClear(viewHolder);
  }
}
