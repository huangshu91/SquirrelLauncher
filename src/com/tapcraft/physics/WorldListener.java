package com.tapcraft.physics;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.util.Logger;
import com.tapcraft.util.Physics;

public class WorldListener implements ContactListener {
  private World parent;
  
  private TimerHandler resetTimer;
  private boolean hitGround;

  public WorldListener(World w) {
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
      if (Physics.checkContact(contact, Config.PLAYER_ID, Config.ACORN_ID)) {
        parent.beatWorld();
      }
      else if (Physics.checkContact(contact, Config.PLAYER_ID, Config.RESET_ID)) {
        parent.getEffectMan().createDustCloud(parent.getPlayer().getX(), parent.getPlayer().getY(), 0.6f);
        
        if (hitGround) return;
        hitGround = true;
        parent.registerUpdateHandler(resetTimer);
      }
      else if (Physics.checkContact(contact, Config.PLAYER_ID, Config.ACCEL_ID)) {
        //possibly reset right before resolve accel
        if (parent.getPlayer() == null) return;
        Body player = parent.getPlayer().getBody();
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
