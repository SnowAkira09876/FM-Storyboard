package com.akira.akirastoryboard.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import java.util.List;

@Dao
public interface FrameDAO {
  @Insert
  void insert(FrameItemModel model);

  @Update
  void update(FrameItemModel model);

  @Delete
  void delete(FrameItemModel model);

  @Query("SELECT * FROM FramesTable WHERE sceneId = :sceneId ORDER BY number ASC")
  List<FrameItemModel> getFrames(String sceneId);
}
