package com.tapcraft.entity;

import java.util.ArrayList;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;

import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.CameraManager;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.ResourceManager;

public final class ParallaxObject {
  ArrayList<Sprite> sprites;
  Entity            stitch;
  
  World parent;
  CameraManager cameraMan;
  
  float prevx;
  float height;
  float parafactor;
  
  //rate (0.0 - 1.0)
  //0 means no parallax effect (foreground). 1 means full parallax effect (moving with camera)
  //as rate increases, the perceived movement is decreased.
  //further objects have higher rates, closer objects have lower rates
  public ParallaxObject(World w, float y, float rate, float pad, String tex) {
    parent = w;
    cameraMan = parent.getCameraMan();
    height = y;
    prevx = cameraMan.getX();
    parafactor = rate;

    stitch = new Entity() {
      @Override
      protected void onManagedUpdate(float pSecondsElapsed) {
        float curx = cameraMan.getX();
        if (prevx == curx) return;
        
        this.setX(this.getX() + (curx-prevx)*parafactor);
        prevx = curx;
      }
    };
    
    float width = w.getWorldWidth();
    float startx = 0;
    Sprite temp = null;
    do {
      temp = new Sprite(startx, height, ResourceManager.textureHashMap.get(tex), 
          GameEngine.getSharedInstance().getVertexBufferObjectManager());
      startx += temp.getWidth()*(pad+1);
      stitch.attachChild(temp);
    } while (temp.getX() <= width);
    
  }
  
  public void adjustHeight(int h) {
    stitch.setY(stitch.getY()+h);
  }
  
  public void attach() {
    parent.attachChild(stitch);
  }
}
