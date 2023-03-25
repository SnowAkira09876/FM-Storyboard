package com.akira.akirastoryboard.activities.scene;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.akira.akirastoryboard.activities.scene.SceneActivityRepo;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import java.util.List;

public class SceneActivityViewModel extends ViewModel{
  private SceneActivityRepo repo;

  public SceneActivityViewModel() {
    this.repo = new SceneActivityRepo();
  }

  public LiveData<List<SceneItemModel>> getList() {
    return repo.getList();
  }
}
