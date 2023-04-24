package com.akira.akirastoryboard.activities.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.akira.akirastoryboard.activities.main.MainActivityRepo;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class MainActivityViewModel extends ViewModel {
  private MainActivityRepo repo;
  private final MutableLiveData<ProjectItemModel> newProject = new MutableLiveData<>();
  private final MutableLiveData<ProjectItemModel> updateProject = new MutableLiveData<>();
  private final MutableLiveData<ProjectItemModel> deleteProject = new MutableLiveData<>();

  public MainActivityViewModel(AkiraRoomDatabase roomDatabase, ExecutorService executor) {
    this.repo = new MainActivityRepo(roomDatabase, executor);
  }

  public void addProject(ProjectItemModel model) {
    repo.addProject(model);
  }

  public void updateProject(ProjectItemModel model) {
    repo.updateProject(model);
  }
  
  public void deleteProject(ProjectItemModel model) {
    repo.deleteProject(model);
  }

  public LiveData<List<ProjectItemModel>> getProjects() {
    return repo.getProjects();
  }

  public void setNewProject(ProjectItemModel model) {
    this.newProject.setValue(model);
  }

  public LiveData<ProjectItemModel> getNewProject() {
    return this.newProject;
  }

  public MutableLiveData<ProjectItemModel> getUpdateProject() {
    return this.updateProject;
  }

  public void setUpdateProject(ProjectItemModel model) {
    this.updateProject.setValue(model);
  }

  public MutableLiveData<ProjectItemModel> getDeleteProject() {
    return this.deleteProject;
  }

  public void setDeleteProject(ProjectItemModel model) {
    this.deleteProject.setValue(model);
  }
}
