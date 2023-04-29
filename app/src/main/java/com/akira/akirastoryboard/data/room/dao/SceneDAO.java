package com.akira.akirastoryboard.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import com.akira.akirastoryboard.pojos.SceneItemModel;
import com.akira.akirastoryboard.pojos.SceneWithFramesModel;
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
  LiveData<List<SceneItemModel>> getScenes(String projectId);

  @Transaction
  @Query("SELECT * FROM ScenesTable WHERE projectId = :projectId ORDER BY number ASC")
  LiveData<List<SceneWithFramesModel>> getScenesWithFrames(String projectId);
}
