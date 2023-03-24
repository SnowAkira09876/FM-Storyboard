package com.akira.akirastoryboard.activities.editor;

import com.akira.akirastoryboard.common.activity.BaseActivityPresenter;

public class EditorActivityPresenter extends BaseActivityPresenter<EditorActivityView> {
  private EditorActivityRepo repo;

  public EditorActivityPresenter(EditorActivityView view) {
    super(view);
    this.repo = new EditorActivityRepo(view);
  }

  public void getList() {
    repo.getList();
  }
}
