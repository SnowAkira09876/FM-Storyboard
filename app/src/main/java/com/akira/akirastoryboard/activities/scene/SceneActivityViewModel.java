package com.akira.akirastoryboard.activities.scene;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.akira.akirastoryboard.activities.scene.SceneActivityRepo;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class SceneActivityViewModel extends ViewModel {
  private SceneActivityRepo repo;
  private final MutableLiveData<SceneItemModel> newScene = new MutableLiveData<>();
  private final MutableLiveData<SceneItemModel> updateScene = new MutableLiveData<>();
  private final MutableLiveData<SceneItemModel> deleteScene = new MutableLiveData<>();

  public SceneActivityViewModel(AkiraRoomDatabase roomDatabase, ExecutorService executor) {
    this.repo = new SceneActivityRepo(roomDatabase, executor);
  }

  public void addScene(SceneItemModel model) {
    repo.addScene(model);
  }

  public void updateScene(SceneItemModel model) {
    repo.updateScene(model);
  }
  
  public void deleteScene(SceneItemModel model) {
    repo.deleteScene(model);
  }

  public LiveData<List<SceneItemModel>> getScenes(int projectId) {
    return repo.getScenes(projectId);
  }

  public void setNewScene(SceneItemModel model) {
    this.newScene.setValue(model);
  }

  public LiveData<SceneItemModel> getNewScene() {
    return this.newScene;
  }

  public MutableLiveData<SceneItemModel> getUpdateScene() {
    return this.updateScene;
  }

  public void setUpdateScene(SceneItemModel model) {
    this.updateScene.setValue(model);
  }

  public MutableLiveData<SceneItemModel> getDeleteScene() {
    return this.deleteScene;
  }

  public void setDeleteScene(SceneItemModel model) {
    this.deleteScene.setValue(model);
  }
}
