package com.akira.akirastoryboard.pojos;
import lombok.Data;

@Data
public class GroupedSceneItemModel {
  private String title, scenes, category;

  public GroupedSceneItemModel(String title, String scenes, String category) {
    this.title = title;
    this.scenes = scenes;
    this.category = category;
  }
}
