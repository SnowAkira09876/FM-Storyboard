package com.akira.akirastoryboard.pojos;

import android.os.Parcel;
import android.os.Parcelable;
import lombok.Data;

@Data
public class CategoryItemModel implements Parcelable {
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

  public CategoryItemModel(String title, String description) {
    this.title = title;
    this.description = description;
  }

  protected CategoryItemModel(Parcel in) {
    title = in.readString();
    description = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeString(description);
  }

  @Override
  public int describeContents() {
    return 0;
  }
}
