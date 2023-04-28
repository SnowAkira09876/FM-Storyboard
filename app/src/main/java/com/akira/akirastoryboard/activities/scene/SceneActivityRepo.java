package com.akira.akirastoryboard.activities.scene;

import androidx.lifecycle.MutableLiveData;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
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
    Callable<List<SceneItemModel>> callable = () -> roomDatabase.getSceneDAO().getScenes(projectId);

    executor.submit(
        () -> {
          List<SceneItemModel> scenes = roomDatabase.runInTransaction(callable);

          mutableLiveData.postValue(scenes);
        });

    return mutableLiveData;
  }

  public void addScene(SceneItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getSceneDAO().insert(model);
          getScenes(model.getProjectId());
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }

  public void updateScene(SceneItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getSceneDAO().update(model);
          getScenes(model.getProjectId());
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }

  public void deleteScene(SceneItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getSceneDAO().delete(model);
          getScenes(model.getProjectId());
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }
}
