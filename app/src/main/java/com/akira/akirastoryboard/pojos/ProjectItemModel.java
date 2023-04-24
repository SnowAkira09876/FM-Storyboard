package com.akira.akirastoryboard.pojos;

import android.net.Uri;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ProjectsTable")
public class ProjectItemModel implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  private int id;

  private String title, scenes, genres, imagePath;

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

  @SuppressWarnings("deprecation")
  protected ProjectItemModel(Parcel in) {
    id = in.readInt();
    title = in.readString();
    scenes = in.readString();
    genres = in.readString();
    imagePath = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(title);
    dest.writeString(scenes);
    dest.writeString(genres);
    dest.writeString(imagePath);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getGenres() {
    return this.genres;
  }

  public void setGenres(String genres) {
    this.genres = genres;
  }

  public String getImagePath() {
    return this.imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
}
