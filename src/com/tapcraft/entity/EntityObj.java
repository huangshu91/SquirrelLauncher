package com.tapcraft.entity;

import org.andengine.extension.physics.box2d.PhysicsWorld;

import com.tapcraft.levels.World;

public abstract class EntityObj {
  protected World parent;
  protected PhysicsWorld physWorld;
  protected PhysicsWorld simWorld;
  
  protected boolean active;
  protected float x, y;
  
  public EntityObj(World par, float xc, float yc) {
    x = xc;
    y = yc;
    active = true;
    parent = par;
    physWorld = parent.getPhysWorld();
    simWorld = parent.getSimWorld();
  }
  
  public float getX() {
    return x;
  }
  
  public float getY() {
    return y;
  }
}
