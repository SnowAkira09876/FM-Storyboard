package com.akira.akirastoryboard.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import com.akira.akirastoryboard.pojos.ProjectWithScenesModel;
import java.util.List;

@Dao
public interface ProjectDAO {
  @Insert
  void insert(ProjectItemModel model);

  @Update
  void update(ProjectItemModel model);

  @Delete
  void delete(ProjectItemModel model);

  @Query("SELECT * FROM ProjectsTable ORDER BY number ASC")
  LiveData<List<ProjectItemModel>> getProjects();

  @Transaction
  @Query("SELECT * FROM ProjectsTable ORDER BY number ASC")
  LiveData<List<ProjectWithScenesModel>> getProjectsWithScenes();
}
