package com.tapcraft.levels;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.ObjectFactory;

public class World extends Scene {
  protected GameEngine parent;
  protected PhysicsWorld physWorld;
  
  protected Entity bounds;
  
  public World() {
    parent = GameEngine.getSharedInstance();

    bounds = new Entity();

    setTouchAreaBindingOnActionDownEnabled(true);
  }
  
  public PhysicsWorld getPhysWorld() {
    return physWorld;
  }
  
  
  public void initBounds(int WORLD_WIDTH, int WORLD_HEIGHT) {
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
