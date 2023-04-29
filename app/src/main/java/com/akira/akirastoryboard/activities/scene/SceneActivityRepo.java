package com.akira.akirastoryboard.activities.scene;

import androidx.lifecycle.LiveData;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.akira.akirastoryboard.pojos.SceneWithFramesModel;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class SceneActivityRepo {
  private AkiraRoomDatabase roomDatabase;
  private ExecutorService executor;

  public SceneActivityRepo(AkiraRoomDatabase roomDatabase, ExecutorService executor) {
    this.roomDatabase = roomDatabase;
    this.executor = executor;
  }

  public LiveData<List<SceneWithFramesModel>> getScenes(String projectId) {
    return roomDatabase.getSceneDAO().getScenesWithFrames(projectId);
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
