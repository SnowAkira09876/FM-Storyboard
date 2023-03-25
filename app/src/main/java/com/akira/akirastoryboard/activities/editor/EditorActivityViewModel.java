package com.akira.akirastoryboard.activities.editor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.akira.akirastoryboard.pojos.CategoryItemModel;
import java.util.List;

public class EditorActivityViewModel extends ViewModel{
  private EditorActivityRepo repo;

  public EditorActivityViewModel() {
    this.repo = new EditorActivityRepo();
  }

  public LiveData<List<CategoryItemModel>> getList() {
    return repo.getList();
  }
}
