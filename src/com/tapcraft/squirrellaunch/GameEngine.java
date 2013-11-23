package com.tapcraft.squirrellaunch;

import java.io.IOException;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;  
import org.andengine.util.adt.color.Color;

public class GameEngine extends BaseGameActivity {
  public BoundCamera   mCamera;
  
  public static GameEngine instance;
  public Scene currentScene;
  
  public EngineOptions onCreateEngineOptions() {
    instance = this;
    
    mCamera = new BoundCamera(0, 0, Config.CAMERA_WIDTH, Config.CAMERA_HEIGHT);
    EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, 
        new FillResolutionPolicy(), mCamera);
    
    //engineOptions.getAudioOptions().setNeedsMusic(true);
    return engineOptions;
  }
  
  
  public Engine onCreateEngine(final EngineOptions pEngineOptions) {
    return new LimitedFPSEngine(pEngineOptions, Config.FPS);
  }
  
  public static GameEngine getSharedInstance() {
    return instance;
  }


  //only need to load starting resources and then afterwards once loading screen is set up,
  //load remaining resources
  @Override
  public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
    BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(Config.GFX_PATH);
    BuildableBitmapTextureAtlas buildableTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 820, 500);
    ITextureRegion splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, this, Config.TEX_SPLASH);
    
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
      
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    
    buildableTextureAtlas.load();
    ResourceManager.addTexture(splash, Config.TEX_SPLASH);
    
    Font loadfont = FontFactory.createFromAsset(this.getFontManager(), this.getTextureManager(), 
        256, 256, this.getAssets(), Config.FON_GROBOLD, 24f, true, Color.WHITE_ABGR_PACKED_INT);
    loadfont.prepareLetters(Config.PREPARE_ALPHA.toCharArray());
    loadfont.load();
    ResourceManager.addFont(loadfont, Config.FON_GROBOLD);
    
    pOnCreateResourcesCallback.onCreateResourcesFinished();
  }

  @Override
  public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {
    mEngine.registerUpdateHandler(new FPSLogger());

    currentScene = new SplashScene();
    
    pOnCreateSceneCallback.onCreateSceneFinished(this.currentScene);
  }

  public void loadResources() {
    
  }

  @Override
  public void onPopulateScene(Scene pScene,
      OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
    
    pOnPopulateSceneCallback.onPopulateSceneFinished();
  }


}