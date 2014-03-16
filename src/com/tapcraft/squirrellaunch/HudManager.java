package com.tapcraft.squirrellaunch;

import java.util.ArrayList;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.sprite.Sprite;

import com.tapcraft.levels.World;

public class HudManager {
  
  private CameraManager cameraMan;
  private HUD           gameHud;
  private World         parent;
  private ArrayList<Sprite> buttons;
  
  public HudManager(World w) {
    parent = w;
    gameHud = new HUD();
    cameraMan = parent.getCameraMan();
    buttons = new ArrayList<Sprite> ();
  }
  
  public void initHud() {
    if (Config.DEBUG){
      Line l = ObjectFactory.createLine(0, Config.CAMERA_HEIGHT/2, Config.CAMERA_WIDTH, Config.CAMERA_HEIGHT/2);
      l.setColor(1.0f, 0.0f, 0.0f);
      gameHud.attachChild(l);
      l = ObjectFactory.createLine(0, Config.CAMERA_HEIGHT/4, Config.CAMERA_WIDTH, Config.CAMERA_HEIGHT/4);
      l.setColor(1.0f, 0.0f, 0.0f);
      gameHud.attachChild(l);
      l = ObjectFactory.createLine(0, 3*Config.CAMERA_HEIGHT/4, Config.CAMERA_WIDTH, 3*Config.CAMERA_HEIGHT/4);
      l.setColor(1.0f, 0.0f, 0.0f);
      gameHud.attachChild(l);
      l = ObjectFactory.createLine(Config.CAMERA_WIDTH/2, 0, Config.CAMERA_WIDTH/2, Config.CAMERA_HEIGHT);
      l.setColor(1.0f, 0.0f, 0.0f);
      gameHud.attachChild(l);
      l = ObjectFactory.createLine(Config.CAMERA_WIDTH/4, 0, Config.CAMERA_WIDTH/4, Config.CAMERA_HEIGHT);
      l.setColor(1.0f, 0.0f, 0.0f);
      gameHud.attachChild(l);
      l = ObjectFactory.createLine(3*Config.CAMERA_WIDTH/4, 0, 3*Config.CAMERA_WIDTH/4, Config.CAMERA_HEIGHT);
      l.setColor(1.0f, 0.0f, 0.0f);
      gameHud.attachChild(l);
    }
    
    cameraMan.getCamera().setHUD(gameHud);
  }
  
  public HUD getHud() {
    return gameHud;
  }
  
  public void attachButton(Sprite s) {
    
  }
  public void attachButton(Config.Block b) {
    
  }
}
