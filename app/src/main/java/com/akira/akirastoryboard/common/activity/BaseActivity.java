package com.akira.akirastoryboard.common.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<Presenter extends BaseActivityPresenter>
    extends AppCompatActivity implements BaseActivityView {

  protected Presenter presenter;

  @NonNull
  protected abstract Presenter createPresenter();

  protected abstract void injectDependencies();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    injectDependencies();
    super.onCreate(savedInstanceState);
    presenter = createPresenter();
  }

  @SuppressWarnings("deprecation")
  protected <T extends Parcelable> T getParcelableExtraEachApi(
      Intent intent, String key, Class<T> classType) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
      return intent.getParcelableExtra(key, classType);
    else return intent.getParcelableExtra(key);
  }
}
