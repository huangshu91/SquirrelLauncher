package com.tapcraft.physics;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.Config;

public class WinContactListener implements ContactListener {
  World parent;

  public WinContactListener(World w) {
    parent = w;
  }
  
  @Override
  public void beginContact(Contact contact) {
    if (contact.isTouching()) {
      if (checkWin(contact)) {
        parent.beatWorld();
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
  
  public boolean checkWin(Contact contact) {
    String objA = (String) contact.getFixtureA().getBody().getUserData();
    String objB = (String) contact.getFixtureB().getBody().getUserData();
    if (objA == null || objB == null) {
      return false;
    }
    
    if (objA.equals(Config.ACORN_ID)) {
      if (objB.equals(Config.PLAYER_ID)) return true;
      return false;
    }
    else if (objA.equals(Config.PLAYER_ID)){
      if (objB.equals(Config.ACORN_ID)) return true;
      return false;
    }
    
    return false;
  }
}
