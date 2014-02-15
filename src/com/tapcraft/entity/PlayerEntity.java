package com.tapcraft.entity;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.andengine.input.touch.TouchEvent;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.ResourceManager;

public class PlayerEntity {
  private Sprite sprite;
  
  private Body physBody;
  private World parent;
  private PhysicsWorld physWorld;
  
  public PlayerEntity() {
    
  }
  
  public PlayerEntity(World par, PhysicsWorld phy, float x, float y) {
    sprite = new Sprite(x, y, ResourceManager.textureHashMap.get(Config.PLAYER_SPRITE), GameEngine.getSharedInstance().getVertexBufferObjectManager()) {
      @Override
      public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, 
          final float pTouchAreaLocalY) {
        final Body myBod = (Body)getUserData();
        myBod.setAwake(false);
        myBod.setTransform(pSceneTouchEvent.getX()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 
            pSceneTouchEvent.getY()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, myBod.getAngle());
        
        // need this otherwise when touch is still, gravity(force) is applied
        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
          myBod.setAwake(true);
        }
        
        return true;
      }
    };
    parent = par;
    physWorld = parent.getPhysWorld();
    physBody = PhysicsFactory.createCircleBody(phy, sprite, BodyType.DynamicBody, Config.FIXTURE_DEF);
    physWorld.registerPhysicsConnector(new PhysicsConnector(sprite, physBody, true, true));
    sprite.setUserData(physBody);
    
    parent.registerTouchArea(sprite);
    parent.attachChild(sprite);
    
  }
}
