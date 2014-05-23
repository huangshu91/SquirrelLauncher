package com.tapcraft.levels;

import java.util.ArrayList;

import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.ResourceManager;

public class LevelManager {
  private World parent;
  
  private ArrayList<IEntity> effects;
  
  public LevelManager(World w) {
    parent = w;
    
    effects = new ArrayList<IEntity>();
  }
  
  public void finishWorld(float score) {
    Text fin = parent.getEffectMan().createDropBounceText(Config.CLEAR_TEXT, true, false, 0f, 2f);
    Rectangle dim = parent.getEffectMan().createDimScreen(true, 0.5f, 0f, 0f);
    
    effects.add(fin);
    effects.add(dim);
    
    parent.getHudMan().attachEntity(dim);
    parent.getHudMan().attachEntity(fin);
    
    Sprite endboard = new Sprite(Config.CAMERA_WIDTH/2, Config.CAMERA_HEIGHT/2, ResourceManager.textureHashMap.get(Config.MENU_LEVELEND), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager());
    
  }
  
  public void unload() {
    GameEngine.getSharedInstance().runOnUpdateThread(new Runnable() {
      @Override
      public void run() {
        for (IEntity e : effects) 
          e.detachSelf();
      }
    });
  }
}
