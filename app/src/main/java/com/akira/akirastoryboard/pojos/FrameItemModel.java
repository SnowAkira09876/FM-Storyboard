package com.akira.akirastoryboard.pojos;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "FramesTable")
public class FrameItemModel implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  private int id;

  private String sceneId, imagePath, info;

  public static final Creator<FrameItemModel> CREATOR =
      new Creator<FrameItemModel>() {
        @Override
        public FrameItemModel createFromParcel(Parcel in) {
          return new FrameItemModel(in);
        }

        @Override
        public FrameItemModel[] newArray(int size) {
          return new FrameItemModel[size];
        }
      };

  public FrameItemModel() {}

  @SuppressWarnings("deprecation")
  protected FrameItemModel(Parcel in) {
    this.id = in.readInt();
    this.sceneId = in.readString();
    this.imagePath = in.readString();
    this.info = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(sceneId);
    dest.writeString(imagePath);
    dest.writeString(info);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSceneId() {
    return this.sceneId;
  }

  public void setSceneId(String sceneId) {
    this.sceneId = sceneId;
  }

  public String getImagePath() {
    return this.imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public String getInfo() {
    return this.info;
  }

  public void setInfo(String info) {
    this.info = info;
  }
}
