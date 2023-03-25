package com.akira.akirastoryboard.activities.scene;

import androidx.lifecycle.MutableLiveData;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import java.util.ArrayList;
import java.util.List;

public class SceneActivityRepo {
  private final List<SceneItemModel> list = new ArrayList<>();

  public SceneActivityRepo() {}

  public MutableLiveData<List<SceneItemModel>> getList() {
    MutableLiveData<List<SceneItemModel>> mutableLiveData = new MutableLiveData<>();

    for (int i = 0; i < 10; i++) {
      SceneItemModel model = new SceneItemModel("Scene " + i, i + " minutes ago", "20 Frames");
      list.add(model);
    }
    mutableLiveData.setValue(list);

    return mutableLiveData;
  }
}
