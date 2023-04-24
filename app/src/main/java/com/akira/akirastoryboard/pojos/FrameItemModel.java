package com.akira.akirastoryboard.pojos;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import java.util.ArrayList;
import java.util.List;

public class FrameItemModel implements Parcelable {
  private String image;
  private List<CategoryItemModel> categories;
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

  public FrameItemModel(String image, List<CategoryItemModel> categories) {
    this.image = image;
    this.categories = categories;
  }

  @SuppressWarnings("deprecation")
  protected FrameItemModel(Parcel in) {
    image = in.readString();
    categories = new ArrayList<>();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
      in.readList(categories, CategoryItemModel.class.getClassLoader(), CategoryItemModel.class);
    else in.readList(categories, CategoryItemModel.class.getClassLoader());
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(image);
    dest.writeList(categories);
  }

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public List<CategoryItemModel> getCategories() {
    return this.categories;
  }

  public void setCategories(List<CategoryItemModel> categories) {
    this.categories = categories;
  }
}
