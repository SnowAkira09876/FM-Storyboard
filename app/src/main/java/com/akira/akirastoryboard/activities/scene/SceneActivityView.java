package com.akira.akirastoryboard.activities.scene;

import com.akira.akirastoryboard.common.activity.BaseActivityView;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import java.util.List;

public interface SceneActivityView extends BaseActivityView {
  void setList(List<SceneItemModel> list);
}
