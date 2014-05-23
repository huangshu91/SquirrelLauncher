package com.tapcraft.levels;

import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;

import android.hardware.SensorManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tapcraft.entity.BlockManager;
import com.tapcraft.entity.Cannon;
import com.tapcraft.entity.GoldenAcorn;
import com.tapcraft.entity.PlayerEntity;
import com.tapcraft.squirrellaunch.CameraManager;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.EffectManager;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.HudManager;
import com.tapcraft.squirrellaunch.ObjectFactory;
import com.tapcraft.util.Logger;

public class World extends Scene {
  protected GameEngine parent;
  protected PhysicsWorld physWorld;
  protected PhysicsWorld simWorld;
  
  protected Entity bounds;
  protected Entity debug;
  
  protected LevelManager levelMan;
  protected BackgroundManager backMan;
  protected BlockManager blockMan;
  protected HudManager hudMan;
  protected HUD gameHud;
  protected CameraManager cameraMan;
  protected SmoothCamera mCamera;
  protected EffectManager effectMan;
  
  protected PlayerEntity player;
  protected Cannon cannon;
  protected GoldenAcorn acorn;

  protected boolean cannonActive;
  public boolean touchLocked;
  public boolean levelClear;
  
  protected int WORLD_WIDTH;
  protected int WORLD_HEIGHT;
  
  public World(int w, int h) {
    parent = GameEngine.getSharedInstance();
    parent.setCurrentScene(this);
    cameraMan = parent.cameraMan;
    mCamera = cameraMan.getCamera();
    effectMan = new EffectManager(this);
    levelMan = new LevelManager(this);
    backMan = new BackgroundManager(this);
    WORLD_WIDTH = w;
    WORLD_HEIGHT = h;
    touchLocked = false;
    levelClear = false;
    
    bounds = new Entity();

    setTouchAreaBindingOnActionDownEnabled(true);
  }
  
  public int getWorldWidth() {
    return WORLD_WIDTH;
  }
  
  public int getWorldHeight() {
    return WORLD_HEIGHT;
  }
  
  public EffectManager getEffectMan() {
    return effectMan;
  }
  
  public BlockManager getBlockMan() {
    return blockMan;
  }
  
  public HudManager getHudMan() {
    return hudMan;
  }
  
  public PhysicsWorld getPhysWorld() {
    return physWorld;
  }
  
  public PlayerEntity getPlayer() {
    return player;
  }
  
  public PhysicsWorld getSimWorld() {
    return simWorld;
  }
  
  public CameraManager getCameraMan() {
    return cameraMan;
  }
  
  public SmoothCamera getCamera() {
    return mCamera;
  }
  
  public void initBounds() {
    Rectangle top = ObjectFactory.createRect(WORLD_WIDTH/2, WORLD_HEIGHT+1, WORLD_WIDTH, 2);
    Rectangle bot = ObjectFactory.createRect(WORLD_WIDTH/2, -1, WORLD_WIDTH, 2);
    Rectangle lef = ObjectFactory.createRect(0-1, WORLD_HEIGHT/2, 2, WORLD_HEIGHT);
    Rectangle rig = ObjectFactory.createRect(WORLD_WIDTH+1, WORLD_HEIGHT/2, 2, WORLD_HEIGHT);
    
    FixtureDef wallDef = PhysicsFactory.createFixtureDef(0, 0.5f, 0.5f);
    FixtureDef groundDef = PhysicsFactory.createFixtureDef(0, 0.5f, 1.0f);
    PhysicsFactory.createBoxBody(physWorld, top, BodyType.StaticBody, wallDef);
    Body ground = PhysicsFactory.createBoxBody(physWorld, bot, BodyType.StaticBody, groundDef);
    ground.setUserData(Config.RESET_ID);
    PhysicsFactory.createBoxBody(physWorld, rig, BodyType.StaticBody, wallDef);
    PhysicsFactory.createBoxBody(physWorld, lef, BodyType.StaticBody, wallDef);
    PhysicsFactory.createBoxBody(simWorld, top, BodyType.StaticBody, wallDef);
    PhysicsFactory.createBoxBody(simWorld, bot, BodyType.StaticBody, groundDef);
    PhysicsFactory.createBoxBody(simWorld, rig, BodyType.StaticBody, wallDef);
    PhysicsFactory.createBoxBody(simWorld, lef, BodyType.StaticBody, wallDef);
    
    bounds.attachChild(top);
    bounds.attachChild(bot);
    bounds.attachChild(lef);
    bounds.attachChild(rig);
    
    this.attachChild(bounds);
  }
  
  public void setPlayer(PlayerEntity pe) {
    player = pe;
  }
  
  public void resetCond() {
    if (cannon != null) {
      cannon.reactivate();
      player.detachSelf();
      toggleHud();
    }
  }
  
  public void toggleHud() {
    if (hudMan != null) hudMan.toggleActive();
  }
  
  public void simulateTraj() {
    if (cannon != null) cannon.simulate();
  }
  
  public void initPhysWorld() {
    physWorld = new FixedStepPhysicsWorld(Config.FPS, new Vector2(0, -SensorManager.GRAVITY_EARTH), false);
    simWorld = new FixedStepPhysicsWorld(Config.FPS, new Vector2(0, -SensorManager.GRAVITY_EARTH), false);
    
    registerUpdateHandler(physWorld);
  }
  
  public void clearWorld() {
    //reset number of squirrels left
    
    //reset blocks until all gone
    while(blockMan.undoBlock() != null) { }
    mCamera.setCenter(cannon.getX(), cannon.getY());
  }
  
  public void camStart() {
    if (cannon != null)
      cannon.locked = true;
  }
  
  public void camEnd() {
    if (cannon != null)
      cannon.locked = false;
  }
  
  public void disableScroll() {
    this.setOnSceneTouchListener(null);
  }
  
  public void enableScroll() {
    this.setOnSceneTouchListener(cameraMan);
  }
  
  public void beatWorld() {
    if (!levelClear) {
      levelClear = true;
      hudMan.setDebugText(Config.CLEAR_TEXT);
      levelMan.finishWorld(1000f);
    }
  }
}
