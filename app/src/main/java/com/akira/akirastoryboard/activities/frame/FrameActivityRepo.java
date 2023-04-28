package com.akira.akirastoryboard.activities.frame;

import androidx.lifecycle.MutableLiveData;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class FrameActivityRepo {
  private final MutableLiveData<List<FrameItemModel>> mutableLiveData = new MutableLiveData<>();
  private AkiraRoomDatabase roomDatabase;
  private ExecutorService executor;

  public FrameActivityRepo(AkiraRoomDatabase roomDatabase, ExecutorService executor) {
    this.roomDatabase = roomDatabase;
    this.executor = executor;
  }

  public MutableLiveData<List<FrameItemModel>> getFrames(String sceneId) {
    Callable<List<FrameItemModel>> callable = () -> roomDatabase.getFrameDAO().getFrames();
    List<FrameItemModel> list = new ArrayList<>();

    executor.submit(
        () -> {
          List<FrameItemModel> frames = roomDatabase.runInTransaction(callable);
          for (FrameItemModel model : frames) {
            if (model.getSceneId().equals(sceneId)) list.add(model);
          }

          mutableLiveData.postValue(list);
        });

    return mutableLiveData;
  }

  public void addFrame(FrameItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getFrameDAO().insert(model);
          getFrames(model.getSceneId());
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }

  public void updateFrame(FrameItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getFrameDAO().update(model);
          getFrames(model.getSceneId());
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }

  public void deleteFrame(FrameItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getFrameDAO().delete(model);
          getFrames(model.getSceneId());
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }
}
