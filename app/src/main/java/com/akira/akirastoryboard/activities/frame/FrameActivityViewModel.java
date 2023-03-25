package com.akira.akirastoryboard.activities.frame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import java.util.List;

public class FrameActivityViewModel extends ViewModel{
  private FrameActivityRepo repo;

  public FrameActivityViewModel() {
    this.repo = new FrameActivityRepo();
  }

  public LiveData<List<FrameItemModel>> getList() {
    return repo.getList();
  }
}
