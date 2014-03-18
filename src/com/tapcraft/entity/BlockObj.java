package com.tapcraft.entity;

import org.andengine.entity.sprite.Sprite;

import com.badlogic.gdx.physics.box2d.Body;
import com.tapcraft.levels.World;

public class BlockObj extends EntityObj {
  protected Sprite sprite;
  protected Sprite simSprite;
  
  protected Body physBody;
  protected Body simBody;
  
  public BlockObj(World par, float xc, float yc) {
    super(par, xc, yc);
    active = false;
  }
  
  public void moveBlock(float x, float y) {
    if (active) return;
    
    sprite.setPosition(x, y);
  }
  
  public void setActive() {
    
  }

}
