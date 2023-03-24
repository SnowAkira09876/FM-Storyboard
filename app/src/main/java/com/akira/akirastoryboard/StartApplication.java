package com.akira.akirastoryboard;

import android.app.Application;
import com.itsaky.androidide.logsender.LogSender;

public class StartApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    LogSender.startLogging(this);
  }
}
