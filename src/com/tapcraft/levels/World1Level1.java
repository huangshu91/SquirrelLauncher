package com.tapcraft.levels;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;

import android.hardware.SensorManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.tapcraft.entity.Cannon;
import com.tapcraft.entity.PlayerEntity;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.ObjectFactory;

public class World1Level1 extends World{
  private BoundCamera mCamera;
  
  public static int WORLD_WIDTH = 1024;
  public static int WORLD_HEIGHT = 600;
  
  public World1Level1() {
    super();
    parent = GameEngine.getSharedInstance();
    this.setBackground(new Background(0.4f, 0.4f, 0.4f));
    parent.setCurrentScene(this);
    mCamera = parent.mCamera;
    
    bounds = new Entity();
    
    mCamera.setBounds(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
    
    Text flavor = ObjectFactory.createText(Config.CAMERA_WIDTH/2, Config.CAMERA_HEIGHT/2, Config.FON_GROBOLD, "testing");
    this.attachChild(flavor);
    //mCamera.setBoundsEnabled(true);
    
    initPhysWorld();
    initBounds(WORLD_WIDTH, WORLD_HEIGHT);
    
    cannon = new Cannon(this, 250,200);
    //player = new PlayerEntity(this, Config.CAMERA_WIDTH/2, Config.CAMERA_HEIGHT/2);
    
    cannonActive = true;
  }
  
  private void initPhysWorld() {
    physWorld = new FixedStepPhysicsWorld(Config.FPS, new Vector2(0, -SensorManager.GRAVITY_EARTH), false);

    registerUpdateHandler(physWorld);
  }
  
}
