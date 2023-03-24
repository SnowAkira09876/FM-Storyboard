package com.akira.akirastoryboard.common.activity;

import java.lang.ref.WeakReference;

public abstract class BaseActivityPresenter<V extends BaseActivityView> {
  protected V view;

  public BaseActivityPresenter(V view) {
    this.view = view;
  }
    
  public static class Repo<V extends BaseActivityView> {
    private WeakReference<V> view;

    public Repo(V view) {
      this.view = new WeakReference<>(view);
    }
  }
}
