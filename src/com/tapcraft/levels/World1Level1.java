package com.tapcraft.levels;

import org.andengine.entity.scene.background.Background;

import com.tapcraft.entity.Cannon;
import com.tapcraft.entity.GoldenAcorn;
import com.tapcraft.physics.WinContactListener;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.HudManager;

public class World1Level1 extends World{
  
  public static int WIDTH = 2048;
  public static int HEIGHT = 600;
  
  public World1Level1() {
    super(WIDTH, HEIGHT);
    this.setBackground(new Background(0.4f, 0.4f, 0.4f));
    
    this.setOnSceneTouchListener(parent.cameraMan);
    
    mCamera.setBounds(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
    mCamera.setBoundsEnabled(true);
    cameraMan.setWorld(this);
    
    hudMan = new HudManager(this);
    hudMan.initHud();
    setupHud();
    
    blockMan = new BlockManager(this);
    
    initPhysWorld();
    initBounds();
    
    cannon = new Cannon(this, 250,200);
    acorn = new GoldenAcorn(this, 1600, 300);
    physWorld.setContactListener(new WinContactListener(this));
    
    cannonActive = true;
  }
  
  private void setupHud() {
    hudMan.attachButton(Config.Block.WOOD);
  }

}
