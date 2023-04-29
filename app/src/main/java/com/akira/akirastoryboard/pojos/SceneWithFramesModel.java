package com.akira.akirastoryboard.pojos;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;

public class SceneWithFramesModel implements Parcelable {
  @Embedded public SceneItemModel sceneItemModel;

  @Relation(parentColumn = "sceneId", entityColumn = "sceneId")
  public List<FrameItemModel> frames;

  public static final Creator<SceneWithFramesModel> CREATOR =
      new Creator<SceneWithFramesModel>() {
        @Override
        public SceneWithFramesModel createFromParcel(Parcel in) {
          return new SceneWithFramesModel(in);
        }

        @Override
        public SceneWithFramesModel[] newArray(int size) {
          return new SceneWithFramesModel[size];
        }
      };

  public SceneWithFramesModel() {}

  @SuppressWarnings("deprecation")
  protected SceneWithFramesModel(Parcel in) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
      sceneItemModel =
          in.readParcelable(SceneItemModel.class.getClassLoader(), SceneItemModel.class);
    else sceneItemModel = in.readParcelable(SceneItemModel.class.getClassLoader());

    frames = in.createTypedArrayList(FrameItemModel.CREATOR);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(sceneItemModel, flags);
    dest.writeTypedList(frames);
  }

  public SceneItemModel getSceneItemModel() {
    return this.sceneItemModel;
  }

  public void setSceneItemModel(SceneItemModel sceneItemModel) {
    this.sceneItemModel = sceneItemModel;
  }

  public List<FrameItemModel> getFrames() {
    return this.frames;
  }

  public void setFrames(List<FrameItemModel> frames) {
    this.frames = frames;
  }

  @Override
  public boolean equals(Object object) {
    return super.equals(object);
  }
}
