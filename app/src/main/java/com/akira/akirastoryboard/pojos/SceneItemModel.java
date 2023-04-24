package com.akira.akirastoryboard.pojos;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ScenesTable")
public class SceneItemModel implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  private int id;

  private int projectId;
  private String title, story;

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
    projectId = in.readInt();
    title = in.readString();
    story = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeInt(projectId);
    dest.writeString(title);
    dest.writeString(story);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getProjectId() {
    return this.projectId;
  }

  public void setProjectId(int projectId) {
    this.projectId = projectId;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getStory() {
    return this.story;
  }

  public void setStory(String story) {
    this.story = story;
  }
}
