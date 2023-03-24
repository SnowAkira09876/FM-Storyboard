package com.akira.akirastoryboard.activities.editor;

import com.akira.akirastoryboard.activities.editor.EditorActivityView;
import java.lang.ref.WeakReference;

public class EditorActivityRepo {
  private WeakReference<EditorActivityView> view;

  public EditorActivityRepo(EditorActivityView view) {
    this.view = new WeakReference<>(view);
  }
}
