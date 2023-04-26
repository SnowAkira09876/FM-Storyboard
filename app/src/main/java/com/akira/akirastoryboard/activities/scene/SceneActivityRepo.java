package com.akira.akirastoryboard.activities.scene;

import androidx.lifecycle.MutableLiveData;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class SceneActivityRepo {
  private final MutableLiveData<List<SceneItemModel>> mutableLiveData = new MutableLiveData<>();
  private AkiraRoomDatabase roomDatabase;
  private ExecutorService executor;

  public SceneActivityRepo(AkiraRoomDatabase roomDatabase, ExecutorService executor) {
    this.roomDatabase = roomDatabase;
    this.executor = executor;
  }

  public MutableLiveData<List<SceneItemModel>> getScenes(String projectId) {
    List<SceneItemModel> list = new ArrayList<>();
    
    CompletableFuture.supplyAsync(() -> roomDatabase.getSceneDAO().getScenes())
        .thenAcceptAsync(
            result -> {
              for (SceneItemModel model : result) {
                if (model.getProjectId().equals(projectId)) list.add(model);
              }

              mutableLiveData.postValue(list);
            },
            executor);
    return mutableLiveData;
  }

  public void addScene(SceneItemModel model) {
    CompletableFuture.runAsync(
            () -> {
              roomDatabase.getSceneDAO().insert(model);
            },
            executor)
        .thenRun(
            () -> {
              getScenes(model.getProjectId());
            });
  }

  public void updateScene(SceneItemModel model) {
    CompletableFuture.runAsync(
            () -> {
              roomDatabase.getSceneDAO().update(model);
            },
            executor)
        .thenRun(
            () -> {
              getScenes(model.getProjectId());
            });
  }

  public void deleteScene(SceneItemModel model) {
    CompletableFuture.runAsync(
            () -> {
              roomDatabase.getSceneDAO().delete(model);
            },
            executor)
        .thenRun(
            () -> {
              getScenes(model.getProjectId());
            });
  }
}
