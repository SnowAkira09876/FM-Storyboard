package com.akira.akirastoryboard.activities.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.akira.akirastoryboard.activities.main.MainActivityViewModel;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;

public class MainActivityVMFactory implements ViewModelProvider.Factory {
  private AkiraRoomDatabase roomDatabase;
  private ExecutorService executor;

  @Inject
  public MainActivityVMFactory(AkiraRoomDatabase roomDatabase, ExecutorService executor) {
    this.roomDatabase = roomDatabase;
    this.executor = executor;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
      return modelClass.cast(new MainActivityViewModel(roomDatabase, executor));
    }
    throw new IllegalArgumentException("Unknown ViewModel class");
  }
}
