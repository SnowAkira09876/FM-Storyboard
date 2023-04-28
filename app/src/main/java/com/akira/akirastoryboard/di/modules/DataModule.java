package com.akira.akirastoryboard.di.modules;

import android.content.Context;
import androidx.room.Room;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.inject.Singleton;

@Module
public class DataModule {
  private Context context;

  public DataModule(Context context) {
    this.context = context;
  }

  @Provides
  public Context getContext() {
    return context;
  }

  @Singleton
  @Provides
  public AkiraRoomDatabase provideAkiraRoomDatabase() {
    return Room.databaseBuilder(context, AkiraRoomDatabase.class, "AkiraDatabase").build();
  }

  @Singleton
  @Provides
  public ExecutorService provideExecutorService() {
    return Executors.newSingleThreadExecutor();
  }
}
