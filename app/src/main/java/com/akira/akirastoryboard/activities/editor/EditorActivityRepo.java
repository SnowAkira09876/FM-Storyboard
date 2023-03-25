package com.akira.akirastoryboard.activities.editor;

import androidx.lifecycle.MutableLiveData;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import java.util.ArrayList;
import java.util.List;

public class EditorActivityRepo {
  private final List<CategoryItemModel> list = new ArrayList<>();

  public EditorActivityRepo() {}

  public MutableLiveData<List<CategoryItemModel>> getList() {
    MutableLiveData<List<CategoryItemModel>> mutableLiveData = new MutableLiveData<>();

    for (int i = 0; i < 10; i++) {
      CategoryItemModel model =
          new CategoryItemModel("Image " + i, "Category " + i, "Description " + i);
      list.add(model);
    }
    mutableLiveData.setValue(list);

    return mutableLiveData;
  }
}
