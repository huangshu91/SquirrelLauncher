package com.tapcraft.entity;


import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.ObjectFactory;
import com.tapcraft.squirrellaunch.ResourceManager;

public class GoldenAcorn extends EntityObj {
  private AnimatedSprite sprite;
  
  private Body physBody;
  
  public GoldenAcorn (World w, float x, float y) {
    super(w, x, y);
    sprite = new AnimatedSprite(x, y, (ITiledTextureRegion)ResourceManager.textureHashMap.get(Config.ACORN), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager()) {
      @Override
      public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, 
      final float pTouchAreaLocalY) {
        if (!Config.DEBUG) return false; 
        physBody.setTransform(pSceneTouchEvent.getX()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 
            pSceneTouchEvent.getY()/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, physBody.getAngle());
        
        return true;
      }
    };
    sprite.setPosition(x, y);
    sprite.animate(150);
    
    physWorld = parent.getPhysWorld();
    
    physBody = PhysicsFactory.createCircleBody(physWorld, sprite, BodyType.StaticBody, Config.FIXTURE_DEF);
    physBody.setUserData(Config.ACORN_ID);
    physWorld.registerPhysicsConnector(new PhysicsConnector(sprite, physBody, true, true));
    
    parent.attachChild(sprite);
  }
}
