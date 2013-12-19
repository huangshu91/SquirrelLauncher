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
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.ObjectFactory;

public class World1Level1 extends Scene{
  private GameEngine parent;
  private BoundCamera mCamera;
  
  private PhysicsWorld physWorld;
  
  public static int WORLD_WIDTH = 1024;
  public static int WORLD_HEIGHT = 600;
  
  private Entity bounds;
  
  public World1Level1() {
    parent = GameEngine.getSharedInstance();
    this.setBackground(new Background(0.4f, 0.4f, 0.4f));
    parent.setCurrentScene(this);
    mCamera = parent.mCamera;
    
    bounds = new Entity();
    
    mCamera.setBounds(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
    
    Text flavor = ObjectFactory.createText(Config.CAMERA_WIDTH/2, Config.CAMERA_HEIGHT/2, Config.FON_GROBOLD, "testing");
    this.attachChild(flavor);
    //mCamera.setBoundsEnabled(true);
    
    Rectangle test = ObjectFactory.createRect(0, Config.CAMERA_HEIGHT/2, 30, 30);
    test.setColor(1.0f, 1.0f, 1.0f);
    this.attachChild(test);
    
    initPhysWorld();
    initBounds();
  }
  
  private void initPhysWorld() {
    physWorld = new FixedStepPhysicsWorld(Config.FPS, new Vector2(0, -SensorManager.GRAVITY_EARTH), false);

    registerUpdateHandler(physWorld);
  }
  
  private void initBounds() {
    Rectangle top = ObjectFactory.createRect(WORLD_WIDTH/2, WORLD_HEIGHT+1, WORLD_WIDTH, 2);
    Rectangle bot = ObjectFactory.createRect(WORLD_WIDTH/2, 0-1, WORLD_WIDTH, 2);
    Rectangle lef = ObjectFactory.createRect(0-1, WORLD_HEIGHT/2, 2, WORLD_HEIGHT);
    Rectangle rig = ObjectFactory.createRect(WORLD_WIDTH+1, WORLD_HEIGHT/2, 2, WORLD_HEIGHT);
    
    FixtureDef wallDef = PhysicsFactory.createFixtureDef(0, 0.5f, 0.5f);
    PhysicsFactory.createBoxBody(physWorld, top, BodyType.StaticBody, wallDef);
    PhysicsFactory.createBoxBody(physWorld, bot, BodyType.StaticBody, wallDef);
    PhysicsFactory.createBoxBody(physWorld, rig, BodyType.StaticBody, wallDef);
    PhysicsFactory.createBoxBody(physWorld, lef, BodyType.StaticBody, wallDef);
    
    bounds.attachChild(top);
    bounds.attachChild(bot);
    bounds.attachChild(lef);
    bounds.attachChild(rig);
    
    this.attachChild(bounds);
  }
}
