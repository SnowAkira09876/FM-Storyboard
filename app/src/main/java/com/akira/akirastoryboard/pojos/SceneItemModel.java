package com.akira.akirastoryboard.pojos;
import lombok.Data;

@Data
public class SceneItemModel {
  private String title, time, frames;

  public SceneItemModel(String title, String time, String frames) {
    this.title = title;
    this.time = time;
    this.frames = frames;
  }
}
