package com.akira.akirastoryboard.activities.frame;

import androidx.lifecycle.MutableLiveData;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import java.util.ArrayList;
import java.util.List;

public class FrameActivityRepo {
  private final List<FrameItemModel> list = new ArrayList<>();
  private final List<CategoryItemModel> categories = new ArrayList<>();

  public FrameActivityRepo() {}

  public MutableLiveData<List<FrameItemModel>> getList() {
    MutableLiveData<List<FrameItemModel>> mutableLiveData = new MutableLiveData<>();

    for (int i = 0; i < 10; i++) {
      CategoryItemModel category0 =
          new CategoryItemModel("Image", "Title " + i, "Description " + i);
      categories.add(category0);

      FrameItemModel model = new FrameItemModel("", categories);
      list.add(model);
    }
    mutableLiveData.setValue(list);

    return mutableLiveData;
  }
}
