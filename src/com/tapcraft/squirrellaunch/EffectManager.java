package com.tapcraft.squirrellaunch;

import org.andengine.entity.sprite.Sprite;

import com.tapcraft.levels.World;

public final class EffectManager {
  World parent;
  
  public static float DUST_SCALE = 0.5f;
  public static int DUST_RADIUS = 60;
  
  public EffectManager(World w) {
    parent = w;
  }
  
  public void createDustCloud(float x, float y, float s) {
    float modscale = DUST_SCALE*s;
    for (int i = 0; i < 8; i++) {
      float xoff = (float) ((Math.random()*DUST_RADIUS)-DUST_RADIUS/2);
      float yoff = (float) ((Math.random()*DUST_RADIUS)-DUST_RADIUS/2);
      float scale = (float) ((Math.random()*(modscale/2))+modscale/2);
      float rot = (float) (Math.random()*360);
      float fadetime = (float) ((Math.random()*2.5f));
      Sprite dust = new Sprite(x+xoff, y+yoff, ResourceManager.textureHashMap.get(Config.DUST), 
          GameEngine.getSharedInstance().getVertexBufferObjectManager());
      dust.registerEntityModifier(AniManager.fadeRemove(fadetime, 1, 0));
      dust.setScale(scale);
      dust.setRotation(rot);
      parent.attachChild(dust);
    }
  }
}
