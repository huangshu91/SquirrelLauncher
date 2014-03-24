package com.tapcraft.util;

import com.badlogic.gdx.physics.box2d.Contact;

public final class Physics {
  
  public static boolean checkContact(Contact c, String id1, String id2) {
    String objA = (String) c.getFixtureA().getBody().getUserData();
    String objB = (String) c.getFixtureB().getBody().getUserData();
    
    if (objA == null || objB == null) return false;
    
    if (objA.equals(id1)) {
      if (objB.equals(id2)) return true;
      return false;
    }
    else if (objA.equals(id2)){
      if (objB.equals(id1)) return true;
      return false;
    }
    
    return false;
  }
}
