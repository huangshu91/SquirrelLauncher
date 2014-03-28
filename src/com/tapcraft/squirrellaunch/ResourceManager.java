package com.tapcraft.squirrellaunch;

import java.util.HashMap;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.adt.color.Color;

public final class ResourceManager {
  public static HashMap<String, ITextureRegion> textureHashMap = new HashMap<String, ITextureRegion>();
  public static HashMap<String, Font>           fontHashMap = new HashMap<String, Font>();
  
  
  //sometimes need to add textures outside of the general batch loading
  public static void addTexture(ITextureRegion tex, String key) {
    textureHashMap.put(key, tex);
  }
  
  public static void addFont(Font fon, String key) {
    fontHashMap.put(key,  fon);
  }
  
  public static void loadResources() {
    GameEngine parent = GameEngine.getSharedInstance();
    BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(Config.GFX_PATH);
    
    //load splash screen
    BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(Config.GFX_PATH);
    BuildableBitmapTextureAtlas buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 820, 500, TextureOptions.BILINEAR);
    ITextureRegion splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.TEX_SPLASH);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.TEX_SPLASH);
    
    //load menu level end
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 660, 630, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.MENU_LEVELEND);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.MENU_LEVELEND);
    
    //load dustball
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 180, 170, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.DUST);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.DUST);
    
    //load cannon launch button
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 100, 100, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.BUTTON_LAUNCH);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.BUTTON_LAUNCH);
    
    //load player sprite
    buildableTextureAtlas = new BuildableBitmapTextureAtlas(parent.getTextureManager(), 360, 360, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.PLAYER_SPRITE);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.PLAYER_SPRITE);
    
    //load wood block
    buildableTextureAtlas = new BuildableBitmapTextureAtlas(parent.getTextureManager(), 280, 280, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.BLOCK_WOOD);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.BLOCK_WOOD);
    
    //load bounce block
    buildableTextureAtlas = new BuildableBitmapTextureAtlas(parent.getTextureManager(), 280, 280, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.BLOCK_BOUNCE);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.BLOCK_BOUNCE);
    
    //load accel block
    buildableTextureAtlas = new BuildableBitmapTextureAtlas(parent.getTextureManager(), 280, 280, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.BLOCK_ACCEL);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.BLOCK_ACCEL);
    
    //load block halo
    buildableTextureAtlas = new BuildableBitmapTextureAtlas(parent.getTextureManager(), 290, 290, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.BLOCK_HALO);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.BLOCK_HALO);
    
    //load simulation circle animated sprite
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 400, 400, TextureOptions.BILINEAR);
    TiledTextureRegion tiled = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(buildableTextureAtlas, 
        parent, Config.CIRCLE, 2, 2);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(tiled, Config.CIRCLE);
    
    //load cannon sprite
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 380, 314, TextureOptions.BILINEAR);
    tiled = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(buildableTextureAtlas, 
        parent, Config.CANNON, 1, 2);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(tiled, Config.CANNON);
    
    //load acorn
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 180, 240, TextureOptions.BILINEAR);
    tiled = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(buildableTextureAtlas, 
        parent, Config.ACORN, 2, 2);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(tiled, Config.ACORN);
    
    //load button wood
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 100, 100, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.BUTTON_WOOD);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.BUTTON_WOOD);
    
    //load button bounce
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 100, 100, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.BUTTON_BOUNCE);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.BUTTON_BOUNCE);
    
    //load button accel
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 100, 100, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.BUTTON_ACCEL);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.BUTTON_ACCEL);
    
    //load button reset
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 70, 70, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.BUTTON_UNDO);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.BUTTON_UNDO);
    
    //load button clear
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 70, 70, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.BUTTON_CLEAR);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.BUTTON_CLEAR);
    
    //load acorns
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 100, 130, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.ACORN_STATIC);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.ACORN_STATIC);
    
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 100, 130, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, Config.ACORN_MOLD);
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.ACORN_MOLD);
    
    //load parallax backgrounds
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 650, 200, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, "parallax_background_layer_front.png");
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, "test1");
    
    buildableTextureAtlas = 
        new BuildableBitmapTextureAtlas(parent.getTextureManager(), 640, 85, TextureOptions.BILINEAR);
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, 
        parent, "parallax_background_layer_mid.png");
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, "test2");
    
    loadFonts();
  }
  
  public static void loadFonts() {
    GameEngine parent = GameEngine.getSharedInstance();

    //load fonts
    Font loadfont = FontFactory.createFromAsset(parent.getFontManager(), parent.getTextureManager(), 
        256, 256, parent.getAssets(), Config.FON_GROBOLD, 24f, true, Color.WHITE_ABGR_PACKED_INT);
    loadfont.prepareLetters(Config.PREPARE_ALPHA.toCharArray());
    loadfont.load();
    ResourceManager.addFont(loadfont, Config.FON_GROBOLD);
    
    BitmapTextureAtlas mFontTexture = new BitmapTextureAtlas(parent.getTextureManager(), 
        256, 256, TextureOptions.BILINEAR);
    Font mFont = FontFactory.createStrokeFromAsset(parent.getFontManager(), 
        mFontTexture, parent.getAssets(), Config.FON_HUD, 32, true, 
        org.andengine.util.adt.color.Color.WHITE_ABGR_PACKED_INT, 3, 
        org.andengine.util.adt.color.Color.BLACK_ABGR_PACKED_INT);
    mFont.prepareLetters(Config.PREPARE_HUD.toCharArray());
    mFont.load();
    ResourceManager.addFont(mFont, Config.FON_HUD);
  }
}
