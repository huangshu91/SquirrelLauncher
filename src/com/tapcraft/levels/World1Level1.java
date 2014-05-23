package com.tapcraft.levels;

import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.modifier.ease.EaseBounceOut;

import com.tapcraft.entity.BlockManager;
import com.tapcraft.entity.Cannon;
import com.tapcraft.entity.GoldenAcorn;
import com.tapcraft.physics.SimContactListener;
import com.tapcraft.physics.WorldListener;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.HudManager;
import com.tapcraft.squirrellaunch.ResourceManager;

public class World1Level1 extends World{
  
  public static int WIDTH = 2048;
  public static int HEIGHT = 600;
  
  public World1Level1() {
    super(WIDTH, HEIGHT);
    this.setBackground(new Background(0.6f, 0.9f, 1f));
    
    this.setOnSceneTouchListener(parent.cameraMan);
    
    mCamera.setBounds(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
    mCamera.setBoundsEnabled(true);
    cameraMan.setWorld(this);
    
    blockMan = new BlockManager(this);
    
    hudMan = new HudManager(this);
    hudMan.initHud();
    setupHud();
    
    initPhysWorld();
    initBounds();
    
    backMan.loadWorld(Config.World.GRASS);
    
    cannon = new Cannon(this, 250,200);
    //acorn = new GoldenAcorn(this, 1600, 300);
    acorn = new GoldenAcorn(this, 1000, 300);
    physWorld.setContactListener(new WorldListener(this));
    simWorld.setContactListener(new SimContactListener(this));
    
    Sprite temp = new Sprite(Config.CAMERA_WIDTH/2, Config.CAMERA_HEIGHT/2, ResourceManager.textureHashMap.get(Config.MENU_LEVELEND), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager());
    temp.setScale(0.6f);
    //temp.registerEntityModifier(new MoveYModifier(3, 700, Config.CAMERA_HEIGHT/2, EaseBounceOut.getInstance()));
    
    Sprite temp2 = new Sprite(Config.CAMERA_WIDTH/2, 700, ResourceManager.textureHashMap.get(Config.PARALLAX_CLOUD_BACK), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager());
    
    //this.attachChild(temp2);
    hudMan.attachEntity(temp);
    //test
    cannonActive = true;
  }
  
  private void setupHud() {
    hudMan.attachButton(Config.Block.WOOD, Config.NUMBLOCK);
    hudMan.attachButton(Config.Block.BOUNCE, Config.NUMBLOCK);
    hudMan.attachButton(Config.Block.ACCEL, Config.NUMBLOCK);
    hudMan.attachUndo();
    hudMan.attachClear();
  }

}
