package com.akira.akirastoryboard.activities.frame;

import com.akira.akirastoryboard.activities.frame.FrameActivityView;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class FrameActivityRepo {
  private WeakReference<FrameActivityView> view;
  private final List<FrameItemModel> list = new ArrayList<>();
  private final List<CategoryItemModel> categories = new ArrayList<>();

  public FrameActivityRepo(FrameActivityView view) {
    this.view = new WeakReference<>(view);
  }

  public void getList() {
    for (int i = 0; i < 10; i++) {
      CategoryItemModel category0 =
          new CategoryItemModel("Image", "Title " + i, "Description " + i);
      categories.add(category0);

      FrameItemModel model = new FrameItemModel("", categories);
      list.add(model);
    }

    view.get().setList(list);
  }
}
