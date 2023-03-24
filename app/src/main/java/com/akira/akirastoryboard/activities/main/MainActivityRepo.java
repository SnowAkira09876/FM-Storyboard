package com.akira.akirastoryboard.activities.main;

import com.akira.akirastoryboard.pojos.GroupedSceneItemModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivityRepo {
  private WeakReference<MainActivityView> view;
  private final List<GroupedSceneItemModel> list = new ArrayList<>();

  public MainActivityRepo(MainActivityView view) {
    this.view = new WeakReference<>(view);
  }

  public void getList() {
    for (int i = 0; i < 10; i++) {
      GroupedSceneItemModel model =
          new GroupedSceneItemModel(
              "Group " + i,
              "12 scenes",
              "Science fiction");
      list.add(model);
    }

    view.get().setList(list);
  }
}
