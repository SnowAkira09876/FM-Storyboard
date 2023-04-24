package com.akira.akirastoryboard.activities.main;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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
    CompletableFuture.supplyAsync(() -> roomDatabase.getProjectDAO().getProjects())
        .thenAcceptAsync(
            result -> {
              mutableLiveData.postValue(result);
            },
            executor);
    return mutableLiveData;
  }

  public void addProject(ProjectItemModel model) {
    CompletableFuture.runAsync(
            () -> {
              roomDatabase.getProjectDAO().insert(model);
            },
            executor)
        .thenRun(
            () -> {
              getProjects();
            });
  }

  public void updateProject(ProjectItemModel model) {
    CompletableFuture.runAsync(
            () -> {
              roomDatabase.getProjectDAO().update(model);
            },
            executor)
        .thenRun(
            () -> {
              getProjects();
            });
  }

  public void deleteProject(ProjectItemModel model) {
    CompletableFuture.runAsync(
            () -> {
              roomDatabase.getProjectDAO().delete(model);
            },
            executor)
        .thenRun(
            () -> {
              getProjects();
            });
  }
}
