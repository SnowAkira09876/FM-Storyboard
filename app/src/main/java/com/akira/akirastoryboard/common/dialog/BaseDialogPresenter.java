package com.akira.akirastoryboard.common.dialog;

public class BaseDialogPresenter<V extends BaseDialogView> {
  private V view;

  public BaseDialogPresenter(V view) {
    this.view = view;
  }
}
