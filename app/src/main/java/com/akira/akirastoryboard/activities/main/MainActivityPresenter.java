package com.akira.akirastoryboard.activities.main;

import com.akira.akirastoryboard.common.activity.BaseActivityPresenter;

public class MainActivityPresenter extends BaseActivityPresenter<MainActivityView> {
  private MainActivityRepo repo;

  public MainActivityPresenter(MainActivityView view) {
    super(view);
    repo = new MainActivityRepo(view);
  }

  public void getList() {
    repo.getList();
  }
}
