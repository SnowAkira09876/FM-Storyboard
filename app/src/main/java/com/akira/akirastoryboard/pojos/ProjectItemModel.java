package com.akira.akirastoryboard.pojos;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ProjectsTable")
public class ProjectItemModel implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  private int id;

  private int number;

  private String projectId, title, scenes, info, imagePath;

  public ProjectItemModel() {}

  public static final Creator<ProjectItemModel> CREATOR =
      new Creator<ProjectItemModel>() {
        @Override
        public ProjectItemModel createFromParcel(Parcel in) {
          return new ProjectItemModel(in);
        }

        @Override
        public ProjectItemModel[] newArray(int size) {
          return new ProjectItemModel[size];
        }
      };

  protected ProjectItemModel(Parcel in) {
    id = in.readInt();
    number = in.readInt();
    projectId = in.readString();
    title = in.readString();
    scenes = in.readString();
    info = in.readString();
    imagePath = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeInt(number);
    dest.writeString(projectId);
    dest.writeString(title);
    dest.writeString(scenes);
    dest.writeString(info);
    dest.writeString(imagePath);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getNumber() {
    return this.number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getProjectId() {
    return this.projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getScenes() {
    return this.scenes;
  }

  public void setScenes(String scenes) {
    this.scenes = scenes;
  }

  public String getInfo() {
    return this.info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getImagePath() {
    return this.imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
}
