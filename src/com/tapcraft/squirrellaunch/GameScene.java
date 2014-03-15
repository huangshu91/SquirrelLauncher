package com.tapcraft.squirrellaunch;

import org.andengine.engine.camera.SmoothCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;


public class GameScene extends Scene{
  private GameEngine parent;
  private SmoothCamera camera;
  
  public GameScene() {
    parent = GameEngine.getSharedInstance();
    setBackground(new Background(0.8f, 0.8f, 0.2f));
    
    camera = parent.cameraMan.getCamera();
  }
  
  public void loadLevel(int gl) {
    
  }
}
