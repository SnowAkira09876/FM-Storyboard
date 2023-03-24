package com.akira.akirastoryboard.recyclerviews;

import androidx.recyclerview.widget.DiffUtil;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import com.akira.akirastoryboard.pojos.GroupedSceneItemModel;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.akira.akirastoryboard.recyclerviews.adapters.CategoryAdapter;
import com.akira.akirastoryboard.recyclerviews.adapters.EditorAdapter;
import com.akira.akirastoryboard.recyclerviews.adapters.FrameAdapter;
import com.akira.akirastoryboard.recyclerviews.adapters.GroupedSceneAdapter;
import com.akira.akirastoryboard.recyclerviews.adapters.SceneAdapter;
import java.util.Objects;

public class AdapterFactory {
  private static DiffUtil.ItemCallback<SceneItemModel> sceneDiffUtil =
      new DiffUtil.ItemCallback<SceneItemModel>() {
        @Override
        public boolean areItemsTheSame(SceneItemModel oldItem, SceneItemModel newItem) {
          return oldItem.getTitle() == newItem.getTitle();
        }

        @Override
        public boolean areContentsTheSame(SceneItemModel oldItem, SceneItemModel newItem) {
          return Objects.equals(oldItem, newItem);
        }
      };

  private static DiffUtil.ItemCallback<GroupedSceneItemModel> groupedSceneDiffUtil =
      new DiffUtil.ItemCallback<GroupedSceneItemModel>() {
        @Override
        public boolean areItemsTheSame(
            GroupedSceneItemModel oldItem, GroupedSceneItemModel newItem) {
          return oldItem.getTitle() == newItem.getTitle();
        }

        @Override
        public boolean areContentsTheSame(
            GroupedSceneItemModel oldItem, GroupedSceneItemModel newItem) {
          return Objects.equals(oldItem, newItem);
        }
      };

  private static DiffUtil.ItemCallback<FrameItemModel> frameDiffUtil =
      new DiffUtil.ItemCallback<FrameItemModel>() {
        @Override
        public boolean areItemsTheSame(FrameItemModel oldItem, FrameItemModel newItem) {
          return oldItem.getCategories() == newItem.getCategories();
        }

        @Override
        public boolean areContentsTheSame(FrameItemModel oldItem, FrameItemModel newItem) {
          return Objects.equals(oldItem, newItem);
        }
      };

  private static DiffUtil.ItemCallback<CategoryItemModel> categoryDiffUtil =
      new DiffUtil.ItemCallback<CategoryItemModel>() {
        @Override
        public boolean areItemsTheSame(CategoryItemModel oldItem, CategoryItemModel newItem) {
          return oldItem.getTitle() == newItem.getTitle();
        }

        @Override
        public boolean areContentsTheSame(CategoryItemModel oldItem, CategoryItemModel newItem) {
          return Objects.equals(oldItem, newItem);
        }
      };

  public static GroupedSceneAdapter getGroupedSceneAdapter(
      GroupedSceneAdapter.GroupedSceneItemClickListener listener) {
    return new GroupedSceneAdapter(groupedSceneDiffUtil, listener);
  }

  public static SceneAdapter getSceneAdapter(SceneAdapter.SceneItemClickListener listener) {
    return new SceneAdapter(sceneDiffUtil, listener);
  }

  public static FrameAdapter getFrameAdapter(FrameAdapter.FrameItemClickListener listener) {
    return new FrameAdapter(frameDiffUtil, listener);
  }

  public static CategoryAdapter getCategoryAdapter() {
    return new CategoryAdapter(categoryDiffUtil);
  }

  public static EditorAdapter getEditorAdapter() {
    return new EditorAdapter(categoryDiffUtil);
  }
}
