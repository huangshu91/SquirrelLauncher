package com.tapcraft.squirrellaunch;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;


public class GameScene extends Scene{
  private GameEngine parent;
  private BoundCamera camera;
  
  public GameScene() {
    parent = GameEngine.getSharedInstance();
    setBackground(new Background(0.8f, 0.8f, 0.2f));
    
    camera = parent.mCamera;
  }
  
  public void loadLevel(int gl) {
    
  }
}
