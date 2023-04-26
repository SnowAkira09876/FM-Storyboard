package com.akira.akirastoryboard.pojos;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ScenesTable")
public class SceneItemModel implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  private int id;

  private String projectId, sceneId, title, frames, info;

  public SceneItemModel() {}

  public static final Creator<SceneItemModel> CREATOR =
      new Creator<SceneItemModel>() {
        @Override
        public SceneItemModel createFromParcel(Parcel in) {
          return new SceneItemModel(in);
        }

        @Override
        public SceneItemModel[] newArray(int size) {
          return new SceneItemModel[size];
        }
      };

  protected SceneItemModel(Parcel in) {
    id = in.readInt();
    projectId = in.readString();
    sceneId = in.readString();
    title = in.readString();
    frames = in.readString();
    info = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(projectId);
    dest.writeString(sceneId);
    dest.writeString(title);
    dest.writeString(frames);
    dest.writeString(info);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getProjectId() {
    return this.projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getSceneId() {
    return this.sceneId;
  }

  public void setSceneId(String sceneId) {
    this.sceneId = sceneId;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFrames() {
    return this.frames;
  }

  public void setFrames(String frames) {
    this.frames = frames;
  }

  public String getInfo() {
    return this.info;
  }

  public void setInfo(String info) {
    this.info = info;
  }
}
