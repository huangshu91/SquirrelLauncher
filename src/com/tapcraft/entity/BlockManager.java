package com.tapcraft.entity;

import java.util.EnumMap;

import org.andengine.extension.physics.box2d.PhysicsWorld;

import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.CameraManager;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.util.Logger;

public class BlockManager {
  private World parent;
  private CameraManager cameraMan;
  private PhysicsWorld physWorld;
  private PhysicsWorld simWorld;
  
  private EnumMap<Config.Block, Integer> blocknum;
  
  public BlockManager(World p) {
    parent = p;
    cameraMan = p.getCameraMan();
    physWorld = p.getPhysWorld();
    simWorld = p.getSimWorld();
    blocknum = new EnumMap<Config.Block, Integer> (Config.Block.class);
  }
  
  public BlockObj createBlock(Config.Block b, float x, float y) {
    int numleft = blocknum.get(b);
    if (numleft == 0) return null;
    
    numleft--;
    blocknum.put(b, numleft);
    BlockObj ret = null;
    
    if (b == Config.Block.WOOD){
      ret = new WoodBlock(parent, x, y);
    }
    
    return ret;
  }
  
  public void addBlockCount(Config.Block b, int n) {
    blocknum.put(b, n);
  }
}
