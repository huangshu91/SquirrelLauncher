package com.tapcraft.levels;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

import com.tapcraft.entity.EntityObj;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.ResourceManager;

public class LevelEndBoard extends EntityObj {
  private Sprite board;
  private Sprite acorn1;
  private Sprite acorn2;
  private Sprite acorn3;
  
  private Text score;
  
  public LevelEndBoard(World par, float xc, float yc) {
    super(par, xc, yc);
    
    board = new Sprite(Config.CAMERA_WIDTH/2, Config.CAMERA_HEIGHT/2, ResourceManager.textureHashMap.get(Config.MENU_LEVELEND),
        GameEngine.getSharedInstance().getVertexBufferObjectManager());
    board.setScale(0.6f);
    
    acorn1 = new Sprite(0, 0, ResourceManager.textureHashMap.get(Config.ACORN_MOLD), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager());
    
    acorn2 = new Sprite(50, 0, ResourceManager.textureHashMap.get(Config.ACORN_MOLD), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager());
    
    acorn2 = new Sprite(100, 0, ResourceManager.textureHashMap.get(Config.ACORN_MOLD), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager());
    
    board.attachChild(acorn1);
    board.attachChild(acorn2);
    board.attachChild(acorn3);
    
    parent.getHudMan().attachEntity(board);
  }

}
