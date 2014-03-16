package com.tapcraft.squirrellaunch;

import java.util.EnumMap;

import org.andengine.extension.physics.box2d.PhysicsFactory;

import com.badlogic.gdx.physics.box2d.FixtureDef;


public interface Config {
  
  public enum GameStates {
    SPLASH, MENU, PLAY
  }
  
  public enum GameLevels {
    LEVEL_ONE, LEVEL_TWO
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
  
  public static String FON_GROBOLD = "fonts/GROBOLD.ttf";
  
  public static String PREPARE_ALPHA = "abcdefghijklmnopqrstuvwxyz";
  
  //CONSTANTS
  
  public static float[] IMPULSE = { 40.0f, 0 };
  
  //BUTTONS
  public static String BUTTON = "hud_wood.png";
  public static String LAUNCH = "cannonlaunch.png";
  
  //BLOCKS
  public static String[] BlockHud = { Config.BUTTON };
  public enum Block { 
    WOOD;
    
    public static final EnumMap<Block, String> map= new EnumMap<Block, String>(Block.class);
    static {
      int i = 0;
      for(Block b : Block.values()) {
        map.put(b, BlockHud[i]);
        i++;
      }
    }
  };
  
  //HUD 
  public static int HUD_PAD = 30;
  public static int BUTTON_SIZE = 110;
  
  //DEBUG
  
  public static boolean DEBUG = true;
  
  //SPRITE PATHS
  public static String GFX_PATH = "gfx/";
  public static String TEX_SPLASH = "splashscreen.png";
  public static String PLAYER_SPRITE = "squirrelplayer.png";
  public static String CANNON = "cannon.png";
  public static String CIRCLE = "sim_circle_anim.png";
  public static String ACORN = "acornshine.png";
  
  //CAMERA
  public static float CAM_VEL = 400;
  public static float CAM_ZOOM_VEL = 5;
  public static float MAX_ZOOM = 1.0f;
  public static float MIN_ZOOM = 0.5f;
  
  //PHYSICS
  public static String PLAYER_ID = "player";
  public static String ACORN_ID = "acorn";
}
