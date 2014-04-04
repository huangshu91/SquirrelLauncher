package com.tapcraft.levels;

import java.util.ArrayList;

import com.tapcraft.entity.ParallaxObject;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.util.Logger;

public class BackgroundManager {
  World parent;
  
  Config.World curType;
  ArrayList<ParallaxObject> pobj;
  
  public BackgroundManager(World w) {
    parent = w;
    pobj = new ArrayList<ParallaxObject>();
  }
  
  public void loadWorld(Config.World type) {
    if (type == Config.World.GRASS) {
      loadGrassWorld();
    }
  }
  
  public Config.World getCurrentType() {
    return curType;
  }
  
  private void loadGrassWorld() {
    ParallaxObject mount_back = new ParallaxObject(parent, Config.MOUNTAIN_OFFSET, Config.PARA_BACK, 0, Config.PARALLAX_MOUNT_BACK);
    mount_back.attach();
    
    ParallaxObject cloud_back = new ParallaxObject(parent, parent.getWorldHeight(), Config.PARA_BACK, 0, Config.PARALLAX_CLOUD_BACK);
    cloud_back.attach();
    
    ParallaxObject forest_mid = new ParallaxObject(parent, Config.FOREST_OFFSET, Config.PARA_MID, 0, Config.PARALLAX_FOREST_MID);
    forest_mid.attach();
    
    ParallaxObject grass_fore = new ParallaxObject(parent, Config.GRASS_OFFSET, Config.PARA_FORE, 0, Config.PARALLAX_GRASS_FORE);
    grass_fore.attach();
    
    pobj.add(mount_back);
    pobj.add(cloud_back);
    pobj.add(forest_mid);
    pobj.add(grass_fore);
  }
}
