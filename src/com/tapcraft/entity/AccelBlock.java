package com.tapcraft.entity;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.input.touch.TouchEvent;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.ResourceManager;

public class AccelBlock extends BlockObj {

  public AccelBlock(World par, float xc, float yc) {
    super(par, xc, yc);
    blockType = Config.Block.ACCEL;

    sprite = new Sprite(xc, yc,
        ResourceManager.textureHashMap.get(Config.BLOCK_ACCEL), GameEngine
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
    
    simSprite = new Sprite(sprite.getWidth()/2, sprite.getHeight()/2,
        ResourceManager.textureHashMap.get(Config.BLOCK_HALO), GameEngine
            .getSharedInstance().getVertexBufferObjectManager());

    sprite.attachChild(simSprite);
    parent.attachChild(sprite);
    parent.registerTouchArea(sprite);
  }

  @Override
  public void setActive() {
    active = true;
    
    sprite.detachChild(simSprite);
    FixtureDef accelDef = Config.WOOD_FIXDEF;
    accelDef.isSensor = true;
    
    physBody = PhysicsFactory.createBoxBody(physWorld, sprite,
        BodyType.StaticBody, accelDef);
    physBody.setUserData(Config.ACCEL_ID);
    simSprite = new Sprite(sprite.getX(), sprite.getY(), ResourceManager.textureHashMap.get(Config.BLOCK_ACCEL), GameEngine
            .getSharedInstance().getVertexBufferObjectManager());
    simSprite.setScale(0.3f);
    simBody = PhysicsFactory.createBoxBody(simWorld, simSprite,
        BodyType.StaticBody, accelDef);
    simBody.setUserData(Config.ACCEL_ID);
    
    parent.simulateTraj();
    parent.unregisterTouchArea(sprite);
  }

}
