package com.tapcraft.squirrellaunch;

import java.util.ArrayList;
import java.util.EnumMap;

import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import com.tapcraft.entity.BlockObj;
import com.tapcraft.levels.World;

public final class HudManager {
  
  private CameraManager cameraMan;
  private HUD           gameHud;
  private World         parent;
  private ArrayList<Sprite> buttons;
  private ArrayList<Text>   blockleft;
  
  private EnumMap<Config.Block, Integer> blockind;
  
  private boolean active;
  
  public HudManager(World w) {
    parent = w;
    active = true;
    gameHud = new HUD();
    cameraMan = parent.getCameraMan();
    buttons = new ArrayList<Sprite>();
    blockleft = new ArrayList<Text>();
    blockind = new EnumMap<Config.Block, Integer> (Config.Block.class);
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
      private Config.Block type = b;
      
      @Override
      public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, 
          final float pTouchAreaLocalY) {
        if (!active) return false;
        switch(pSceneTouchEvent.getAction()) {
        case TouchEvent.ACTION_DOWN:
          SmoothCamera cam = cameraMan.getCamera();
          parent.getBlockMan().createBlock(b, cam.getCenterX(), cam.getCenterY());
          Text child = (Text) this.getFirstChild();
          child.setText("x"+parent.getBlockMan().getBlockCount(type));
          break;
        }
        
        return true;
      }
    };
    
    Text butText = new Text(newblock.getWidth() - Config.HUD_PAD, Config.HUD_PAD, ResourceManager.fontHashMap.get(Config.FON_HUD), 
        "x"+numBlock, 3, GameEngine.getSharedInstance().getVertexBufferObjectManager());
    //butText.setText("x"+numBlock);
    butText.setUserData(b);
    newblock.attachChild(butText);
    blockind.put(b, buttons.size());
    blockleft.add(butText);
    buttons.add(newblock);
    parent.getBlockMan().addBlockCount(b,  numBlock);
    newblock.setUserData(b);
    gameHud.attachChild(newblock);
    gameHud.registerTouchArea(newblock);
  }
  
  public void toggleActive() {
    active = !active;
  }
  
  public void attachUndo() {
    float x = Config.CAMERA_WIDTH - Config.HUD_PAD - Config.BUTTON_S_SIZE/2;
    float y = Config.HUD_PAD + Config.BUTTON_S_SIZE/2;
    
    Sprite but_undo = new Sprite(x, y, ResourceManager.textureHashMap.get(Config.BUTTON_UNDO),
        GameEngine.getSharedInstance().getVertexBufferObjectManager()) {
      @Override
      public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, 
          final float pTouchAreaLocalY) {
        if (!active) return false;
        switch(pSceneTouchEvent.getAction()) {
        case TouchEvent.ACTION_UP:
          BlockObj last = parent.getBlockMan().undoBlock();
          if (last != null) {
            Text num = null;
            for (Text t: blockleft) {
              Config.Block type = (Config.Block)t.getUserData(); 
              if (type == last.getType()) {
                num = t;
                break;
              }
            }
            num.setText("x"+parent.getBlockMan().getBlockCount(last.getType()));
          }
          break;
        }
        return true;
      }
    };
    
    gameHud.attachChild(but_undo);
    gameHud.registerTouchArea(but_undo);
  }
  
  public void attachClear() {
    float x = Config.CAMERA_WIDTH - Config.BUTTON_S_SIZE - 2*Config.HUD_PAD - Config.BUTTON_S_SIZE/2;
    float y = Config.HUD_PAD + Config.BUTTON_S_SIZE/2;
    
    Sprite but_clear = new Sprite(x, y, ResourceManager.textureHashMap.get(Config.BUTTON_CLEAR),
        GameEngine.getSharedInstance().getVertexBufferObjectManager()) {
      @Override
      public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, 
          final float pTouchAreaLocalY) {
        if (!active) return false;
        switch(pSceneTouchEvent.getAction()) {
        case TouchEvent.ACTION_UP:
          parent.clearWorld();
          for (Text t: blockleft) {
            int num = parent.getBlockMan().getBlockCount((Config.Block)t.getUserData());
            t.setText("x"+num);
          }
          break;
        }
        return true;
      }
    };
    
    gameHud.attachChild(but_clear);
    gameHud.registerTouchArea(but_clear);
  }
}
