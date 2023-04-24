package com.akira.akirastoryboard.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.akira.akirastoryboard.data.room.dao.ProjectDAO;
import com.akira.akirastoryboard.data.room.dao.SceneDAO;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import com.akira.akirastoryboard.pojos.SceneItemModel;

@Database(
    entities = {ProjectItemModel.class, SceneItemModel.class},
    version = 1)
public abstract class AkiraRoomDatabase extends RoomDatabase {

  public abstract ProjectDAO getProjectDAO();

  public abstract SceneDAO getSceneDAO();
}
