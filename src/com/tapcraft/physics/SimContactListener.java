package com.tapcraft.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.util.Logger;
import com.tapcraft.util.Physics;

public class SimContactListener implements ContactListener {
  private World parent;
  
  public SimContactListener(World w) {
    parent = w;
  }

  @Override
  public void beginContact(Contact contact) {
    if (contact.isTouching()) {
      if (Physics.checkContact(contact, Config.PLAYER_ID, Config.ACCEL_ID)) {
        //possibly reset right before resolve accel
        Body player = (contact.getFixtureA().getBody().getUserData().equals(Config.PLAYER_ID)) 
            ? contact.getFixtureA().getBody() : contact.getFixtureB().getBody();
        player.setLinearVelocity(player.getLinearVelocity().mul(Config.ACCEL_RATE));
      }
      
    }
  }

  @Override
  public void endContact(Contact contact) {
    // TODO Auto-generated method stub

  }

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {
    // TODO Auto-generated method stub

  }

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {
    // TODO Auto-generated method stub

  }

}
