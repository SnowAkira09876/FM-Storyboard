package com.akira.akirastoryboard.activities.frame;

import androidx.lifecycle.MutableLiveData;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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
    List<FrameItemModel> list = new ArrayList<>();

    CompletableFuture.supplyAsync(() -> roomDatabase.getFrameDAO().getFrames())
        .thenAcceptAsync(
            result -> {
              for (FrameItemModel model : result) {
                if (model.getSceneId().equals(sceneId)) list.add(model);
              }

              mutableLiveData.postValue(list);
            },
            executor);
    return mutableLiveData;
  }

  public void addFrame(FrameItemModel model) {
    CompletableFuture.runAsync(
            () -> {
              roomDatabase.getFrameDAO().insert(model);
            },
            executor)
        .thenRun(
            () -> {
              getFrames(model.getSceneId());
            });
  }

  public void updateFrame(FrameItemModel model) {
    CompletableFuture.runAsync(
            () -> {
              roomDatabase.getFrameDAO().update(model);
            },
            executor)
        .thenRun(
            () -> {
              getFrames(model.getSceneId());
            });
  }
  
  public void deleteFrame(FrameItemModel model) {
    CompletableFuture.runAsync(
            () -> {
              roomDatabase.getFrameDAO().delete(model);
            },
            executor)
        .thenRun(
            () -> {
              getFrames(model.getSceneId());
            });
  }
}
