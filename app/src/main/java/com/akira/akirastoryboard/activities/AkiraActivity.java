package com.akira.akirastoryboard.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public abstract class AkiraActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onset();
    onsetViews();
    onsetViewModels();
  }

  protected abstract void onset();

  protected abstract void onsetViews();

  protected abstract void onsetViewModels();
}
