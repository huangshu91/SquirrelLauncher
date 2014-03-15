package com.tapcraft.entity;


import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.ObjectFactory;

public class GoldenAcorn extends EntityObj {
  private AnimatedSprite sprite;
  
  private Body physBody;
  
  public GoldenAcorn (World w, float x, float y) {
    super(w, x, y);
    sprite = ObjectFactory.createAnimSprite(x, y, Config.ACORN);
    sprite.setPosition(x, y);
    sprite.animate(150);
    
    physWorld = parent.getPhysWorld();
    
    physBody = PhysicsFactory.createCircleBody(physWorld, sprite, BodyType.StaticBody, Config.FIXTURE_DEF);
    physBody.setUserData(Config.ACORN_ID);
    physWorld.registerPhysicsConnector(new PhysicsConnector(sprite, physBody, true, true));
    
    parent.attachChild(sprite);
  }
}
