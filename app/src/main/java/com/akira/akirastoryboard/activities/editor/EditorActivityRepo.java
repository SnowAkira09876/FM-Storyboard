package com.akira.akirastoryboard.activities.editor;

import com.akira.akirastoryboard.activities.editor.EditorActivityView;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class EditorActivityRepo {
  private WeakReference<EditorActivityView> view;
  private final List<CategoryItemModel> categories = new ArrayList<>();

  public EditorActivityRepo(EditorActivityView view) {
    this.view = new WeakReference<>(view);
  }

  public void getList() {
    for (int i = 0; i < 10; i++) {
      CategoryItemModel category0 = new CategoryItemModel("Image", "Title " + i, "Description " + i);
      categories.add(category0);
    }

    view.get().setList(categories);
  }
}
