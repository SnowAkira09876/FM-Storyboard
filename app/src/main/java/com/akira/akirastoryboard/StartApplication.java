package com.akira.akirastoryboard;

import androidx.multidex.MultiDexApplication;
import com.akira.akirastoryboard.di.AppComponent;
import com.akira.akirastoryboard.di.DaggerAppComponent;
import com.akira.akirastoryboard.di.modules.DataModule;

public class StartApplication extends MultiDexApplication {
  private static AppComponent component;

  @Override
  public void onCreate() {
    super.onCreate();
    component =
        DaggerAppComponent.builder().dataModule(new DataModule(getApplicationContext())).build();
  }

  public static AppComponent getAppComponent() {
    return component;
  }
}
