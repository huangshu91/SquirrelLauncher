package com.tapcraft.squirrellaunch;

import java.util.ArrayList;

import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import com.tapcraft.entity.BlockObj;
import com.tapcraft.levels.World;
import com.tapcraft.util.Logger;

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
  
  public void attachButton(final Config.Block b, int numBlock) {
    float x = Config.HUD_PAD*(1+buttons.size()) + (buttons.size()*Config.BUTTON_SIZE) + Config.BUTTON_SIZE/2;
    float y = Config.CAMERA_HEIGHT - Config.HUD_PAD - Config.BUTTON_SIZE/2;
    
    Sprite newblock = new Sprite(x, y, ResourceManager.textureHashMap.get(Config.Block.map.get(b)), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager()) {
      BlockObj curBlock;
      @Override
      public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, 
          final float pTouchAreaLocalY) {
        
        switch(pSceneTouchEvent.getAction()) {
        case TouchEvent.ACTION_DOWN:
          SmoothCamera cam = cameraMan.getCamera();
          curBlock = parent.getBlockMan().createBlock(b, 
              cam.getCenterX(), cam.getCenterY());
          break;
        }
        
        return true;
      }
      
    };
    buttons.add(newblock);
    parent.getBlockMan().addBlockCount(b,  numBlock);
    newblock.setUserData(b);
    gameHud.attachChild(newblock);
    gameHud.registerTouchArea(newblock);
  }
  
  public void detachButton(Config.Block b) {
    
  }
}
