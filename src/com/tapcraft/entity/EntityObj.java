package com.tapcraft.entity;

import org.andengine.extension.physics.box2d.PhysicsWorld;

import com.tapcraft.levels.World;

public class EntityObj {
  protected World parent;
  protected PhysicsWorld physWorld;
  
  boolean active;
  float x, y;
  
  public EntityObj(World par, float xc, float yc) {
    x = xc;
    y = yc;
    active = true;
    parent = par;
    physWorld = parent.getPhysWorld();
  }
}