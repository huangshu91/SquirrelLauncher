package com.tapcraft.physics;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.util.Logger;

public class WinContactListener implements ContactListener {
  private World parent;
  
  private TimerHandler resetTimer;
  private boolean hitGround;

  public WinContactListener(World w) {
    parent = w;
    resetTimer = new TimerHandler(2f, true, new ITimerCallback() {
      @Override
      public void onTimePassed(TimerHandler pTimerHandler) {
        parent.unregisterUpdateHandler(resetTimer);
        parent.resetCond();
        hitGround = false;
      } });
    
    hitGround = false;
  }
  
  @Override
  public void beginContact(Contact contact) {
    if (contact.isTouching()) {
      if (checkWin(contact)) {
        parent.beatWorld();
      }
      else if (checkReset(contact)) {
        hitGround = true;
        parent.registerUpdateHandler(resetTimer);
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
    
    if (objA == null || objB == null) return false;
    
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
  
  public boolean checkReset(Contact contact) {
    if (hitGround) return false;
    
    String objA = (String) contact.getFixtureA().getBody().getUserData();
    String objB = (String) contact.getFixtureB().getBody().getUserData();
    
    if (objA == null || objB == null) return false;
    
    if (objA.equals(Config.RESET_ID)) {
      if (objB.equals(Config.PLAYER_ID)) return true;
      return false;
    }
    else if (objA.equals(Config.PLAYER_ID)){
      if (objB.equals(Config.RESET_ID)) return true;
      return false;
    }
    
    return false;
  }
}
