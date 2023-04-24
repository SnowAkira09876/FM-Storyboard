package com.akira.akirastoryboard.di;

import com.akira.akirastoryboard.activities.main.MainActivityVMFactory;
import com.akira.akirastoryboard.activities.scene.SceneActivityVMFactory;
import com.akira.akirastoryboard.bottomsheets.project.AddProjectBottomSheet;
import com.akira.akirastoryboard.bottomsheets.project.EditProjectBottomSheet;
import com.akira.akirastoryboard.di.modules.DataModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {

  MainActivityVMFactory getMainActivityVMFactory();
  
  SceneActivityVMFactory getSceneActivityVMFactory();
  
  void inject(AddProjectBottomSheet view);
  
  void inject(EditProjectBottomSheet view);
}
