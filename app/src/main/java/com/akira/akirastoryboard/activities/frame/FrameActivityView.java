package com.akira.akirastoryboard.activities.frame;

import com.akira.akirastoryboard.common.activity.BaseActivityView;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import java.util.List;

public interface FrameActivityView extends BaseActivityView {

  void setList(List<FrameItemModel> list);
}
