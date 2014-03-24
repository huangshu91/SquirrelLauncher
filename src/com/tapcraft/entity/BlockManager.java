package com.tapcraft.entity;

import java.util.EnumMap;
import java.util.Stack;

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
  
  private Stack<BlockObj> blockhist;
  private int histNum;
  
  public BlockManager(World p) {
    parent = p;
    cameraMan = p.getCameraMan();
    physWorld = p.getPhysWorld();
    simWorld = p.getSimWorld();
    blocknum = new EnumMap<Config.Block, Integer> (Config.Block.class);
    blockhist = new Stack<BlockObj> ();
  }
  
  public BlockObj createBlock(Config.Block b, float x, float y) {
    int numleft = blocknum.get(b);
    if (numleft == 0) return null;
    
    numleft--;
    blocknum.put(b, numleft);
    BlockObj ret = null;
    
    if (b == Config.Block.WOOD) {
      ret = new WoodBlock(parent, x, y);
      blockhist.push(ret);
    } else if (b == Config.Block.BOUNCE) {
      ret = new BounceBlock(parent, x, y);
      blockhist.push(ret);
    } else if (b == Config.Block.ACCEL){
      ret = new AccelBlock(parent, x, y);
      blockhist.push(ret);
    }
    
    return ret;
  }
  
  public boolean undoBlock() {
    if (blockhist.size() == 0) return false;
    
    BlockObj last = blockhist.pop();
    last.removeBlock();
    int numleft = blocknum.get(last.getType());
    numleft++;
    blocknum.put(last.getType(), numleft);
    return true;
  }
  
  public void addBlockCount(Config.Block b, int n) {
    blocknum.put(b, n);
  }
  
}
