package com.akira.akirastoryboard.recyclerviews;

import com.akira.akirastoryboard.recyclerviews.adapters.CategoryAdapter;
import com.akira.akirastoryboard.recyclerviews.adapters.EditorAdapter;
import com.akira.akirastoryboard.recyclerviews.adapters.FrameAdapter;
import com.akira.akirastoryboard.recyclerviews.adapters.GroupedSceneAdapter;
import com.akira.akirastoryboard.recyclerviews.adapters.SceneAdapter;
import com.akira.akirastoryboard.recyclerviews.diffutils.CategoryDiffUtil;
import com.akira.akirastoryboard.recyclerviews.diffutils.FrameDiffUtil;
import com.akira.akirastoryboard.recyclerviews.diffutils.GroupedSceneDiffUtil;
import com.akira.akirastoryboard.recyclerviews.diffutils.SceneDiffUtil;

public class AdapterFactory {

  public static GroupedSceneAdapter getGroupedSceneAdapter(
      GroupedSceneAdapter.GroupedSceneItemClickListener listener) {
    return new GroupedSceneAdapter(new GroupedSceneDiffUtil(), listener);
  }

  public static SceneAdapter getSceneAdapter(SceneAdapter.SceneItemClickListener listener) {
    return new SceneAdapter(new SceneDiffUtil(), listener);
  }

  public static FrameAdapter getFrameAdapter(FrameAdapter.FrameItemClickListener listener) {
    return new FrameAdapter(new FrameDiffUtil(), listener);
  }

  public static CategoryAdapter getCategoryAdapter() {
    return new CategoryAdapter(new CategoryDiffUtil());
  }

  public static EditorAdapter getEditorAdapter() {
    return new EditorAdapter(new CategoryDiffUtil());
  }
}
