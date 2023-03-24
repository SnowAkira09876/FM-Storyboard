package com.akira.akirastoryboard.activities.scene;

import com.akira.akirastoryboard.activities.scene.SceneActivityView;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SceneActivityRepo {
  private WeakReference<SceneActivityView> view;
  private final List<SceneItemModel> list = new ArrayList<>();

  public SceneActivityRepo(SceneActivityView view) {
    this.view = new WeakReference<>(view);
  }

  public void getList() {
    for (int i = 0; i < 10; i++) {
      SceneItemModel model =
          new SceneItemModel(
              "Scene " + i,
              i + " minutes ago",
              "20 Frames");
      list.add(model);
    }

    view.get().setList(list);
  }
}
