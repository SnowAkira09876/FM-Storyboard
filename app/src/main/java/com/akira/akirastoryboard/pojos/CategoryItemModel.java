package com.akira.akirastoryboard.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryItemModel implements Parcelable {
  private String image;
  private String title;
  private String description;

  public static final Creator<CategoryItemModel> CREATOR =
      new Creator<CategoryItemModel>() {
        @Override
        public CategoryItemModel createFromParcel(Parcel in) {
          return new CategoryItemModel(in);
        }

        @Override
        public CategoryItemModel[] newArray(int size) {
          return new CategoryItemModel[size];
        }
      };

  public CategoryItemModel(String image, String title, String description) {
    this.image = image;
    this.title = title;
    this.description = description;
  }

  protected CategoryItemModel(Parcel in) {
    image = in.readString();
    title = in.readString();
    description = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(image);
    dest.writeString(title);
    dest.writeString(description);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
