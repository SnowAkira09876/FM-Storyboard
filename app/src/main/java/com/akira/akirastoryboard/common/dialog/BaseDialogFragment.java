package com.akira.akirastoryboard.common.dialog;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public abstract class BaseDialogFragment<Presenter extends BaseDialogPresenter>
    extends DialogFragment {

  protected Presenter presenter;
  
  protected abstract void onsetViewBinding();

  protected abstract Dialog onsetDialog();

  protected abstract void onsetViews();

  protected abstract void injectDependencies();

  @NonNull protected abstract Presenter createPresenter();

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    injectDependencies();

    this.presenter = createPresenter();
  }

  @Override
  public Dialog onCreateDialog(Bundle bundle) {
    onsetViewBinding();
    onsetViews();
    return onsetDialog();
  }
}
