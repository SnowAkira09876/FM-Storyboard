package com.akira.akirastoryboard.activities.main;

import androidx.lifecycle.MutableLiveData;
import com.akira.akirastoryboard.pojos.GroupedSceneItemModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivityRepo {
  private final List<GroupedSceneItemModel> list = new ArrayList<>();

  public MainActivityRepo() {}

  public MutableLiveData<List<GroupedSceneItemModel>> getList() {
    MutableLiveData<List<GroupedSceneItemModel>> mutableLiveData = new MutableLiveData<>();
    for (int i = 0; i < 10; i++) {
      GroupedSceneItemModel model =
          new GroupedSceneItemModel("Group " + i, "12 scenes", "Science fiction");
      list.add(model);
    }
    mutableLiveData.setValue(list);

    return mutableLiveData;
  }
}
