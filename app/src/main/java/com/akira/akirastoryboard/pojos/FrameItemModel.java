package com.akira.akirastoryboard.pojos;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "FramesTable")
public class FrameItemModel implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  private int id;

  private int number;

  private String sceneId, imagePath, info;

  private boolean isCentered;

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

  protected FrameItemModel(Parcel in) {
    id = in.readInt();
    number = in.readInt();
    sceneId = in.readString();
    imagePath = in.readString();
    info = in.readString();
    isCentered = (in.readInt() != 0);
    ;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeInt(number);
    dest.writeString(sceneId);
    dest.writeString(imagePath);
    dest.writeString(info);
    dest.writeInt(isCentered ? 1 : 0);
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

  public boolean getIsCentered() {
    return this.isCentered;
  }

  public void setIsCentered(boolean isCentered) {
    this.isCentered = isCentered;
  }

  @Override
  public boolean equals(Object object) {
    return super.equals(object);
  }
}
