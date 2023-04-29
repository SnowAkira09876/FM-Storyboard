package com.akira.akirastoryboard.pojos;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;

public class ProjectWithScenesModel implements Parcelable {
  @Embedded public ProjectItemModel projectItemModel;

  @Relation(parentColumn = "projectId", entityColumn = "projectId")
  public List<SceneItemModel> scenes;

  public static final Creator<ProjectWithScenesModel> CREATOR =
      new Creator<ProjectWithScenesModel>() {
        @Override
        public ProjectWithScenesModel createFromParcel(Parcel in) {
          return new ProjectWithScenesModel(in);
        }

        @Override
        public ProjectWithScenesModel[] newArray(int size) {
          return new ProjectWithScenesModel[size];
        }
      };

  public ProjectWithScenesModel() {}

  @SuppressWarnings("deprecation")
  protected ProjectWithScenesModel(Parcel in) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
      projectItemModel =
          in.readParcelable(ProjectItemModel.class.getClassLoader(), ProjectItemModel.class);
    else projectItemModel = in.readParcelable(ProjectItemModel.class.getClassLoader());

    scenes = in.createTypedArrayList(SceneItemModel.CREATOR);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(projectItemModel, flags);
    dest.writeTypedList(scenes);
  }

  public ProjectItemModel getProjectItemModel() {
    return this.projectItemModel;
  }

  public void setProjectItemModel(ProjectItemModel projectItemModel) {
    this.projectItemModel = projectItemModel;
  }

  public List<SceneItemModel> getScenes() {
    return this.scenes;
  }

  public void setScenes(List<SceneItemModel> scenes) {
    this.scenes = scenes;
  }

  @Override
  public boolean equals(Object object) {
    return super.equals(object);
  }
}
