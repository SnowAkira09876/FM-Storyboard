package com.akira.akirastoryboard.activities.main;

import com.akira.akirastoryboard.common.activity.BaseActivityView;
import com.akira.akirastoryboard.pojos.GroupedSceneItemModel;
import java.util.List;

public interface MainActivityView extends BaseActivityView {
  void setList(List<GroupedSceneItemModel> list);
}
