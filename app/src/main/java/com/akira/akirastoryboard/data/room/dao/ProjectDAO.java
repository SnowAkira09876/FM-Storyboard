package com.akira.akirastoryboard.data.room.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.akira.akirastoryboard.pojos.ProjectItemModel;
import java.util.List;

@Dao
public interface ProjectDAO {
	@Insert 
	void insert(ProjectItemModel model);
	
	@Update
	void update(ProjectItemModel model);
	
	@Delete
	void delete(ProjectItemModel model);
	
	@Query("SELECT * FROM ProjectsTable")
    List<ProjectItemModel> getProjects();
}
