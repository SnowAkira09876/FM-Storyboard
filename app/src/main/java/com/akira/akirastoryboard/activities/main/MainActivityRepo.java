package com.akira.akirastoryboard.activities.main;

import androidx.lifecycle.MutableLiveData;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class MainActivityRepo {
  private final MutableLiveData<List<ProjectItemModel>> mutableLiveData = new MutableLiveData<>();
  private AkiraRoomDatabase roomDatabase;
  private ExecutorService executor;

  public MainActivityRepo(AkiraRoomDatabase roomDatabase, ExecutorService executor) {
    this.roomDatabase = roomDatabase;
    this.executor = executor;
  }

  public MutableLiveData<List<ProjectItemModel>> getProjects() {
    Callable<List<ProjectItemModel>> callable = () -> roomDatabase.getProjectDAO().getProjects();

    executor.submit(
        () -> {
          List<ProjectItemModel> projects = roomDatabase.runInTransaction(callable);
          mutableLiveData.postValue(projects);
        });

    return mutableLiveData;
  }

  public void addProject(ProjectItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getProjectDAO().insert(model);
          getProjects();
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }

  public void updateProject(ProjectItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getProjectDAO().update(model);
          getProjects();
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }

  public void deleteProject(ProjectItemModel model) {
    Runnable run =
        () -> {
          roomDatabase.getProjectDAO().delete(model);
          getProjects();
        };

    executor.execute(() -> roomDatabase.runInTransaction(run));
  }
}
