package com.tapcraft.entity;

import org.andengine.extension.physics.box2d.PhysicsWorld;

import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.CameraManager;

public class BlockManager {
  
  private World parent;
  private CameraManager cameraMan;
  private PhysicsWorld physWorld;
  private PhysicsWorld simWorld;
  
  public BlockManager(World p) {
    parent = p;
    cameraMan = p.getCameraMan();
    physWorld = p.getPhysWorld();
    simWorld = p.getSimWorld();
  }
}
