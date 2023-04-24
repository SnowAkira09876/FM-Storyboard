package com.akira.akirastoryboard.widgets.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.akira.akirastoryboard.R;
import com.google.android.material.appbar.AppBarLayout;

public class AkiraRecyclerView extends RecyclerView {
  private View emptyView;
  private AppBarLayout appbar;

  private final AdapterDataObserver observer =
      new AdapterDataObserver() {
        @Override
        public void onChanged() {
          checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
          checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
          checkIfEmpty();
        }
      };

  public AkiraRecyclerView(Context context) {
    super(context);
  }

  public AkiraRecyclerView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public AkiraRecyclerView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  public void setAdapter(Adapter adapter) {
    final Adapter oldAdapter = getAdapter();
    if (oldAdapter != null) oldAdapter.unregisterAdapterDataObserver(observer);

    super.setAdapter(adapter);

    if (adapter != null) adapter.registerAdapterDataObserver(observer);

    checkIfEmpty();
  }

  public void setEmptyView(View emptyView) {
    this.emptyView = emptyView;
    checkIfEmpty();
  }

  public void setToolbarCollapsedWhenEmpty(AppBarLayout appbar) {
    this.appbar = appbar;
    checkIfEmpty();
  }

  private void checkIfEmpty() {
    if (emptyView != null && getAdapter() != null) {
      final boolean emptyViewVisible = getAdapter().getItemCount() == 0;
      emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
      setVisibility(emptyViewVisible ? GONE : VISIBLE);
    }

    if (appbar != null && getAdapter() != null) {
      final boolean emptyViewVisible = getAdapter().getItemCount() == 0;
      appbar.setExpanded(emptyViewVisible ? false : true);
    }
  }
}
