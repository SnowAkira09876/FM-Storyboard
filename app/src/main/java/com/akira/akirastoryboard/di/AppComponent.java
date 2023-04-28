package com.akira.akirastoryboard.di;

import com.akira.akirastoryboard.activities.frame.FrameActivityVMFactory;
import com.akira.akirastoryboard.activities.main.MainActivityVMFactory;
import com.akira.akirastoryboard.activities.scene.SceneActivityVMFactory;
import com.akira.akirastoryboard.di.modules.DataModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {

  MainActivityVMFactory getMainActivityVMFactory();

  SceneActivityVMFactory getSceneActivityVMFactory();

  FrameActivityVMFactory getFrameActivityVMFactory();
}
