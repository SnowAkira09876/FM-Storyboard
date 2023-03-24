package com.akira.akirastoryboard.activities.scene;
import com.akira.akirastoryboard.common.activity.BaseActivityPresenter;

public class SceneActivityPresenter extends BaseActivityPresenter<SceneActivityView> {
	private SceneActivityRepo repo;

  public SceneActivityPresenter(SceneActivityView view) {
    super(view);
    repo = new SceneActivityRepo(view);
  }

  public void getList() {
    repo.getList();
  }
}
