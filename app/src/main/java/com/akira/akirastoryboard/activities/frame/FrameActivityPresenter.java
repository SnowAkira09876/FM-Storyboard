package com.akira.akirastoryboard.activities.frame;

import com.akira.akirastoryboard.activities.frame.FrameActivityRepo;
import com.akira.akirastoryboard.common.activity.BaseActivityPresenter;

public class FrameActivityPresenter extends BaseActivityPresenter<FrameActivityView> {
  private FrameActivityRepo repo;

  public FrameActivityPresenter(FrameActivityView view) {
    super(view);
    this.repo = new FrameActivityRepo(view);
  }

  public void getList() {
    repo.getList();
  }
}
