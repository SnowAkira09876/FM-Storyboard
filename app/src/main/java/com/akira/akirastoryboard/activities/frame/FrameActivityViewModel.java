package com.akira.akirastoryboard.activities.frame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.akira.akirastoryboard.data.room.AkiraRoomDatabase;
import com.akira.akirastoryboard.pojos.FrameItemModel;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class FrameActivityViewModel extends ViewModel {
  private FrameActivityRepo repo;
  private AkiraRoomDatabase roomDatabase;
  private ExecutorService executor;
  private final MutableLiveData<FrameItemModel> newFrame = new MutableLiveData<>();
  private final MutableLiveData<FrameItemModel> updateFrame = new MutableLiveData<>();

  public FrameActivityViewModel(AkiraRoomDatabase roomDatabase, ExecutorService executor) {
    this.roomDatabase = roomDatabase;
    this.executor = executor;
    this.repo = new FrameActivityRepo(roomDatabase, executor);
  }

  public LiveData<List<FrameItemModel>> getFrames(String sceneId) {
    return repo.getFrames(sceneId);
  }

  public void addFrame(FrameItemModel model) {
    repo.addFrame(model);
  }

  public void updateFrame(FrameItemModel model) {
    repo.updateFrame(model);
  }

  public void deleteFrame(FrameItemModel model) {
    repo.deleteFrame(model);
  }

  // BottomSheet ViewModels
  public void setNewFrame(FrameItemModel model) {
    this.newFrame.setValue(model);
  }

  public LiveData<FrameItemModel> getNewFrame() {
    return this.newFrame;
  }

  public void setUpdateFrame(FrameItemModel model) {
    this.updateFrame.setValue(model);
  }

  public LiveData<FrameItemModel> getUpdateFrame() {
    return this.updateFrame;
  }
}
