package com.tapcraft.squirrellaunch;

import java.io.IOException;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.ui.activity.BaseGameActivity;

import com.tapcraft.levels.World1Level1;
import com.tapcraft.util.Logger;

public class GameEngine extends BaseGameActivity {
  public CameraManager cameraMan;
  
  public static GameEngine instance;
  public Scene currentScene;
  
  public EngineOptions onCreateEngineOptions() {
    instance = this;
    
    cameraMan = new CameraManager();
    EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, 
        new FillResolutionPolicy(), cameraMan.getCamera());
    engineOptions.getTouchOptions().setNeedsMultiTouch(true);
    //engineOptions.getAudioOptions().setNeedsMusic(true);
    return engineOptions;
  }
  
  
  public Engine onCreateEngine(final EngineOptions pEngineOptions) {
    return new LimitedFPSEngine(pEngineOptions, Config.FPS);
  }
  
  public static GameEngine getSharedInstance() {
    return instance;
  }
  
  public void setCurrentScene(Scene newScene) {
    currentScene = newScene;
    getEngine().setScene(currentScene);
  }

  @Override
  public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
    ResourceManager.loadResources();
    pOnCreateResourcesCallback.onCreateResourcesFinished();
  }

  @Override
  public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {
    mEngine.registerUpdateHandler(new FPSLogger());

    currentScene = new World1Level1();//new SplashScene();
    
    pOnCreateSceneCallback.onCreateSceneFinished(this.currentScene);
  }

  public void loadResources() {
    
  }

  @Override
  public void onPopulateScene(Scene pScene,
      OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
    
    pOnPopulateSceneCallback.onPopulateSceneFinished();
  }


}