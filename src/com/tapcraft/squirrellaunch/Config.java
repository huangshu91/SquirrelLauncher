package com.tapcraft.squirrellaunch;

import org.andengine.extension.physics.box2d.PhysicsFactory;

import com.badlogic.gdx.physics.box2d.FixtureDef;


public interface Config {
  
  public enum GameStates {
    SPLASH, MENU, PLAY
  }
  
  // Test this resolution because there is some stretching
  public static final int CAMERA_WIDTH = 800;
  public static final int CAMERA_HEIGHT = 480;
  
  public static final int FPS = 60;
  
  public static final String TAG = "TapCraft";
  
  public static final FixtureDef FIXTURE_DEF = 
      PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);
  
  public static final float WALK_MODIFIER = 3.0f;
  public static final int PIXEL_METER_RATIO = 32;
  
  public static String GFX_PATH = "gfx/";
  public static String TEX_SPLASH = "splashscreen.png";
  
  public static String FON_GROBOLD = "fonts/GROBOLD.ttf";
  
  public static String PREPARE_ALPHA = "abcdefghijklmnopqrstuvwxyz";
}
