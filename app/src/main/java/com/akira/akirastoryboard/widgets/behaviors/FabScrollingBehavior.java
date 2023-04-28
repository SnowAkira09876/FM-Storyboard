package com.akira.akirastoryboard.widgets.behaviors;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FabScrollingBehavior extends FloatingActionButton.Behavior {
  public FabScrollingBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onStartNestedScroll(
      @NonNull CoordinatorLayout coordinatorLayout,
      @NonNull FloatingActionButton child,
      @NonNull View directTargetChild,
      @NonNull View target,
      int axes,
      int type) {
    return axes == View.SCROLL_AXIS_VERTICAL;
  }

  @Override
  public void onNestedScroll(
      CoordinatorLayout coordinatorLayout,
      @NonNull FloatingActionButton child,
      @NonNull View target,
      int dxConsumed,
      int dyConsumed,
      int dxUnconsumed,
      int dyUnconsumed,
      int type,
      @NonNull int[] consumed) {
    if (dyConsumed > 0) {
      child.hide(
          new FloatingActionButton.OnVisibilityChangedListener() {
            @Override
            public void onHidden(FloatingActionButton fab) {
              super.onHidden(fab);
              fab.setVisibility(View.INVISIBLE);
            }
          });
    } else if (dyConsumed < 0) {
      child.show();
    }
  }
}
