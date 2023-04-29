package com.akira.akirastoryboard.activities.frame;

import androidx.lifecycle.LiveData;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class FrameActivityRepo {
  private AkiraRoomDatabase roomDatabase;
  private ExecutorService executor;

  public FrameActivityRepo(AkiraRoomDatabase roomDatabase, ExecutorService executor) {
    this.roomDatabase = roomDatabase;
    this.executor = executor;
  }

  public LiveData<List<FrameItemModel>> getFrames(String sceneId) {
    return roomDatabase.getFrameDAO().getFrames(sceneId);
  }

  public void addFrame(FrameItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getFrameDAO().insert(model);
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }

  public void updateFrame(FrameItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getFrameDAO().update(model);
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }

  public void deleteFrame(FrameItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getFrameDAO().delete(model);
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }
}
