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

  public static String GFX_PATH = "gfx/";
  
  public static final FixtureDef FIXTURE_DEF = 
      PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);
  
  public static final float WALK_MODIFIER = 3.0f;
  public static final int PIXEL_METER_RATIO = 32;
  
  public static String FON_GROBOLD = "fonts/GROBOLD.ttf";
  
  public static String PREPARE_ALPHA = "abcdefghijklmnopqrstuvwxyz";
  
  //CONSTANTS
  
  public static float[] IMPULSE = { 40.0f, 0 };
  
  //BUTTONS
  public static String BUTTON_WOOD = "hud_wood.png";
  public static String BUTTON_BOUNCE = "hud_bounce.png";
  public static String BUTTON_ACCEL = "hud_accel.png";
  public static String BUTTON_LAUNCH = "hud_launch.png";
  public static String BUTTON_UNDO = "button_undo.png";
  public static String BUTTON_CLEAR = "button_clear.png";
  
  //BLOCKS
  public static String BLOCK_WOOD = "blockwood.png";
  public static String BLOCK_BOUNCE = "blockbounce.png";
  public static String BLOCK_ACCEL = "blockaccel.png";
  
  public static String BLOCK_HALO = "blockhalo.png";
  
  public static final FixtureDef WOOD_FIXDEF = 
      PhysicsFactory.createFixtureDef(0, 0.3f, 0f);
  public static final FixtureDef BOUNCE_FIXDEF = 
      PhysicsFactory.createFixtureDef(0, 1.0f, 0f);
  public static float ACCEL_RATE = 1.4f;
  
  public static int NUMBLOCK = 9;
  public static String[] BlockHud = { Config.BUTTON_WOOD, Config.BUTTON_BOUNCE,
    Config.BUTTON_ACCEL };
  public enum Block { 
    WOOD, BOUNCE, ACCEL;
    
    public static final EnumMap<Block, String> map= new EnumMap<Block, String>(Block.class);
    static {
      int i = 0;
      for(Block b : Block.values()) {
        map.put(b, BlockHud[i]);
        i++;
      }
    }
  }
  
  //HUD 
  public static int HUD_PAD = 10;
  public static int BUTTON_SIZE = 90;
  public static int BUTTON_S_SIZE = 60;
  
  //DEBUG
  public static boolean DEBUG = true;
  
  //SPRITE
  public static String TEX_SPLASH = "splashscreen.png";
  public static String PLAYER_SPRITE = "squirrelplayer.png";
  public static String CANNON = "cannon.png";
  public static String CIRCLE = "sim_circle_anim.png";
  public static String ACORN = "acornshine.png";
  
  public static String ACORN_STATIC = "acorn_gold.png";
  public static String ACORN_MOLD = "acorn_mold.png";
  
  //CAMERA
  public static float CAM_VEL = 600;
  public static float CAM_ZOOM_VEL = 5;
  public static float MAX_ZOOM = 1.0f;
  public static float MIN_ZOOM = 0.5f;
  
  //PHYSICS
  public static String PLAYER_ID = "player";
  public static String ACORN_ID = "acorn";
  public static String RESET_ID = "ground";
  public static String ACCEL_ID = "accel";
}
