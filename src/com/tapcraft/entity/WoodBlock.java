package com.tapcraft.entity;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.input.touch.TouchEvent;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.ResourceManager;

public class WoodBlock extends BlockObj {

  public WoodBlock(World par, float xc, float yc) {
    super(par, xc, yc);
    blockType = Config.Block.WOOD;

    sprite = new Sprite(xc, yc,
        ResourceManager.textureHashMap.get(Config.BLOCK_WOOD), GameEngine
            .getSharedInstance().getVertexBufferObjectManager()) {
      @Override
      public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
          final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
        switch (pSceneTouchEvent.getAction()) {
        case TouchEvent.ACTION_DOWN:
          parent.disableScroll();
          break;
        case TouchEvent.ACTION_MOVE:
          moveBlock(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
          break;
        case TouchEvent.ACTION_UP:
          parent.enableScroll();
          setActive();
          break;
        }
        return true;
      }
    };

    sprite.setScale(0.3f);
    parent.attachChild(sprite);
    parent.registerTouchArea(sprite);
  }

  public void setActive() {
    active = true;
    physBody = PhysicsFactory.createBoxBody(physWorld, sprite,
        BodyType.StaticBody, Config.WOOD_FIXDEF);
    simSprite = new Sprite(sprite.getX(), sprite.getY(), ResourceManager.textureHashMap.get(Config.BLOCK_WOOD), GameEngine
            .getSharedInstance().getVertexBufferObjectManager());
    simSprite.setScale(0.3f);
    simBody = PhysicsFactory.createBoxBody(simWorld, simSprite,
        BodyType.StaticBody, Config.WOOD_FIXDEF);
    
    parent.simulateTraj();
    parent.unregisterTouchArea(sprite);
  }

}
