package com.akira.akirastoryboard.activities.editor;

import com.akira.akirastoryboard.common.activity.BaseActivityView;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import java.util.List;

public interface EditorActivityView extends BaseActivityView {

  void setList(List<CategoryItemModel> list);
}
