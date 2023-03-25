package com.akira.akirastoryboard.activities.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.akira.akirastoryboard.activities.main.MainActivityRepo;
import com.akira.akirastoryboard.pojos.GroupedSceneItemModel;
import java.util.List;

public class MainActivityViewModel extends ViewModel{
  private MainActivityRepo repo;

  public MainActivityViewModel() {
    this.repo = new MainActivityRepo();
  }

  public LiveData<List<GroupedSceneItemModel>> getList() {
    return repo.getList();
  }
}
