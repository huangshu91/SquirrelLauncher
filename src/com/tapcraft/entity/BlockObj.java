package com.tapcraft.entity;

import org.andengine.entity.sprite.Sprite;

import com.badlogic.gdx.physics.box2d.Body;
import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.Config;

public abstract class BlockObj extends EntityObj {
  protected Sprite sprite;
  protected Sprite simSprite;
  
  protected Body physBody;
  protected Body simBody;
  
  protected Config.Block blockType;
  
  public BlockObj(World par, float xc, float yc) {
    super(par, xc, yc);
    active = false;
  }
  
  public void moveBlock(float x, float y) {
    if (active) return;
    
    sprite.setPosition(x, y);
  }
  
  public abstract void setActive();

  public void removeBlock() {
    parent.unregisterTouchArea(sprite);
    parent.detachChild(sprite);
    
    if (active) {
      physBody.setActive(false);
      simBody.setActive(false);
      physWorld.destroyBody(physBody);
      simWorld.destroyBody(simBody);
      parent.simulateTraj();
    }
  }
  
  public Config.Block getType() {
    return blockType;
  }
}
