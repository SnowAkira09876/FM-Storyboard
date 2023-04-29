package com.akira.akirastoryboard.activities.main;

import androidx.lifecycle.LiveData;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import com.akira.akirastoryboard.pojos.ProjectWithScenesModel;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class MainActivityRepo {
  private AkiraRoomDatabase roomDatabase;
  private ExecutorService executor;

  public MainActivityRepo(AkiraRoomDatabase roomDatabase, ExecutorService executor) {
    this.roomDatabase = roomDatabase;
    this.executor = executor;
  }

  public LiveData<List<ProjectWithScenesModel>> getProjects() {
    return roomDatabase.getProjectDAO().getProjectsWithScenes();
  }

  public void addProject(ProjectItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getProjectDAO().insert(model);
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }

  public void updateProject(ProjectItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getProjectDAO().update(model);
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }

  public void deleteProject(ProjectItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getProjectDAO().delete(model);
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }
}
