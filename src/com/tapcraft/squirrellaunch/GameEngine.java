package com.tapcraft.squirrellaunch;

import java.io.IOException;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.BaseGameActivity;  

public class GameEngine extends BaseGameActivity {
  public BoundCamera   mCamera;
  
  public static GameEngine instance;
  public Scene currentScene;

  public ITextureRegion splash;
  
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


  @Override
  public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
    BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
    //BitmapTextureAtlas textureAtlas = 
    //    new BitmapTextureAtlas(this.getTextureManager(), 820, 500, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
    
    BuildableBitmapTextureAtlas buildableTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 820, 500);

    //splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(textureAtlas, this, "worldtile.png", 10, 10);
    
    splash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buildableTextureAtlas, this, "splashscreen.png");
    
    try {
      buildableTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
      
    } catch (TextureAtlasBuilderException e) {
      e.printStackTrace();
    }
    
    buildableTextureAtlas.load();
    
    pOnCreateResourcesCallback.onCreateResourcesFinished();
  }

  @Override
  public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {
    mEngine.registerUpdateHandler(new FPSLogger());

    currentScene = new Scene();
    Sprite bgsprite = new Sprite(Config.CAMERA_WIDTH/2, Config.CAMERA_HEIGHT/2, splash, this.getVertexBufferObjectManager());
    currentScene.attachChild(bgsprite);
    
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