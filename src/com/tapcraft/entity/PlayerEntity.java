package com.tapcraft.entity;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.andengine.input.touch.TouchEvent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.ResourceManager;

public class PlayerEntity extends EntityObj{
  private Sprite sprite;
  
  private Body physBody;
  
  PhysicsConnector pcon;
  
  public IUpdateHandler checkReset;
  
  public PlayerEntity(World w, float x, float y) {
    super(w, x, y);
    sprite = new Sprite(x, y, ResourceManager.textureHashMap.get(Config.PLAYER_SPRITE), GameEngine.getSharedInstance().getVertexBufferObjectManager()) {
      @Override
      public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, 
          final float pTouchAreaLocalY) {
        
        final Body myBod = physBody;
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
    
    physBody = PhysicsFactory.createCircleBody(physWorld, sprite, 
        BodyType.DynamicBody, Config.FIXTURE_DEF);
    pcon = new PhysicsConnector(sprite, physBody, true, true);
    physWorld.registerPhysicsConnector(pcon);
    physBody.setUserData(Config.PLAYER_ID);
    
    parent.registerTouchArea(sprite);
    parent.attachChild(sprite);
    
    /*
    checkReset = new IUpdateHandler() {

      @Override
      public void onUpdate(float pSecondsElapsed) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void reset() {
        // TODO Auto-generated method stub
        
      }
      
    };
    */
  }
  
  public void launch(Vector2 impulse) {
    physBody.applyLinearImpulse(impulse, physBody.getWorldCenter());
    parent.getCamera().setChaseEntity(sprite);
    parent.toggleHud();
    
    //parent.registerUpdateHandler(checkReset);
  }
  
  public void detachSelf() {
    parent.unregisterTouchArea(sprite);
    parent.detachChild(sprite);
    physWorld.unregisterPhysicsConnector(pcon);
    physBody.setActive(false);
    physWorld.destroyBody(physBody);
    parent.setPlayer(null);
  }
  
  public Body getBody() {
    return physBody;
  }
  
}
