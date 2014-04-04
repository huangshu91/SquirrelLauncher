package com.tapcraft.squirrellaunch;

import java.util.EnumMap;

import org.andengine.extension.physics.box2d.PhysicsFactory;

import com.badlogic.gdx.physics.box2d.FixtureDef;


public interface Config {
  
  public enum GameStates {
    SPLASH, MENU, PLAY
  }
  
  public enum World {
    GRASS
  }
  
  // Test this resolution because there is some stretching
  public static final int CAMERA_WIDTH = 800;
  public static final int CAMERA_HEIGHT = 480;
  
  public static final int FPS = 60;
  
  public static final String TAG = "TapCraft";

  public static final String GFX_PATH = "gfx/";
  
  //FONTS
  public static final String FON_GROBOLD = "fonts/GROBOLD.ttf";
  public static final String FON_HUD = "fonts/GROBOLD.ttf";
  
  public static final String PREPARE_ALPHA = "abcdefghijklmnopqrstuvwxyz";
  public static final String PREPARE_HUD = "x0123456789abcdefghijklmnopqrstuvwxyz";
  
  //CONSTANTS
  public static float[] IMPULSE = { 40.0f, 0 };

  public static final FixtureDef FIXTURE_DEF = 
      PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);
  
  public static final float WALK_MODIFIER = 3.0f;
  public static final int PIXEL_METER_RATIO = 32;
  
  //BUTTONS
  public static final String BUTTON_WOOD = "hud_wood.png";
  public static final String BUTTON_BOUNCE = "hud_bounce.png";
  public static final String BUTTON_ACCEL = "hud_accel.png";
  public static final String BUTTON_LAUNCH = "hud_launch.png";
  public static final String BUTTON_UNDO = "button_undo.png";
  public static final String BUTTON_CLEAR = "button_clear.png";
  
  //BLOCKS
  public static final String BLOCK_WOOD = "blockwood.png";
  public static final String BLOCK_BOUNCE = "blockbounce.png";
  public static final String BLOCK_ACCEL = "blockaccel.png";
  
  public static final String BLOCK_HALO = "blockhalo.png";
  
  public static final FixtureDef WOOD_FIXDEF = 
      PhysicsFactory.createFixtureDef(0, 0.3f, 0f);
  public static final FixtureDef BOUNCE_FIXDEF = 
      PhysicsFactory.createFixtureDef(0, 1.0f, 0f);
  public static float ACCEL_RATE = 1.4f;
  
  public static final int NUMBLOCK = 9;
  public static final String[] BlockHud = { Config.BUTTON_WOOD, Config.BUTTON_BOUNCE,
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
  public static final int HUD_PAD = 10;
  public static final int BUTTON_SIZE = 60;
  public static final int BUTTON_S_SIZE = 60;
  public static final float DOWN_SCALE = 1.1f;
  
  //DEBUG
  public static final boolean DEBUG = true;
  public static final String DEBUG_NOT = "DEBUG_MODE";
  
  //SPRITE
  public static final String TEX_SPLASH = "splashscreen.png";
  public static final String PLAYER_SPRITE = "squirrelplayer.png";
  public static final String CANNON = "cannon.png";
  public static final String CIRCLE = "sim_circle_anim.png";
  public static final String ACORN = "acornshine.png";
  public static final String DUST = "dustball.png";
  public static final String STAR = "star.png";
  
  public static final String ACORN_STATIC = "acorn_gold.png";
  public static final String ACORN_MOLD = "acorn_mold.png";
  
  //MENU
  public static final String MENU_LEVELEND = "menu_levelend.png";
  
  //CAMERA
  public static final float CAM_VEL = 600;
  public static final float CAM_ZOOM_VEL = 5;
  public static final float MAX_ZOOM = 1.0f;
  public static final float MIN_ZOOM = 0.5f;
  
  //PHYSICS
  public static final String PLAYER_ID = "player";
  public static final String ACORN_ID = "acorn";
  public static final String RESET_ID = "ground";
  public static final String ACCEL_ID = "accel";
  
  //PLAYER
  public static final int NUM_LIVES = 3;
  
  //PARALLAX_BG
  public static final String PARALLAX_GRASS_FORE = "parallax_grass_fore.png";
  public static final int GRASS_OFFSET = -79;
  public static final String PARALLAX_FOREST_MID = "parallax_forest_mid.png";
  public static final int FOREST_OFFSET = 90;
  public static final String PARALLAX_MOUNT_BACK = "parallax_mountain_back.png";
  public static final int MOUNTAIN_OFFSET = 120;
  public static final String PARALLAX_CLOUD_BACK = "parallax_cloud_back.png";
  public static final int CLOUD_OFFSET = 0;
  
  public static final float PARA_FORE = 0.0f;
  public static final float PARA_MID = 0.3f;
  public static final float PARA_BACK = 0.6f;
  
  //TEXTURE PACKER
  public static final String TEXPACK_BASE = "gfx_pack/";
  public static final String TEXPACK_BUTTON = "gfx_pack/buttons.xml";
  
  public static final int HUD_ACCEL_ID = 0;
  public static final int HUD_BOUNCE_ID = 1;
  public static final int HUD_LAUNCH_ID = 2;
  public static final int HUD_WOOD_ID = 3;
  
  public static final String TEXPACK_PARALLAX_GRASS = "gfx_pack/parallax_grass.xml";
  
  public static final int PARALLAX_CLOUD_BACK_ID = 0;
  public static final int PARALLAX_FOREST_MID_ID = 1;
  public static final int PARALLAX_GRASS_FORE_ID = 2;
  public static final int PARALLAX_MOUNTAIN_BACK_ID = 3;
  
}
