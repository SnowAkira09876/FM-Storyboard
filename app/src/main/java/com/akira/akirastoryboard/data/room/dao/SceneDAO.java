package com.akira.akirastoryboard.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import java.util.List;

@Dao
public interface SceneDAO {
  @Insert
  void insert(SceneItemModel model);

  @Update
  void update(SceneItemModel model);

  @Delete
  void delete(SceneItemModel model);

  @Query("SELECT * FROM ScenesTable WHERE projectId = :projectId ORDER BY number ASC")
  List<SceneItemModel> getScenes(String projectId);
}
