package com.tapcraft.entity;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.util.math.MathUtils;

import com.badlogic.gdx.math.Vector2;
import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.ResourceManager;
import com.tapcraft.util.Logger;

public class Cannon extends Entity{
  private AnimatedSprite sprite;
  private Sprite squirrel;
  
  private Sprite but;
  
  private boolean active;
  
  public Cannon(World w, int x, int y) {
    super(w, x, y);
    sprite = new AnimatedSprite(x, y, (ITiledTextureRegion) 
        ResourceManager.textureHashMap.get(Config.CANNON), GameEngine.getSharedInstance().getVertexBufferObjectManager()) {
      Vector2 a = new Vector2();
      Vector2 b = new Vector2(getX(), getY());

      @Override
      public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, 
      final float pTouchAreaLocalY) {
        switch(pSceneTouchEvent.getAction()) {
        case TouchEvent.ACTION_DOWN:
          a.x = pSceneTouchEvent.getX();
          a.y = pSceneTouchEvent.getY();
          break;
        case TouchEvent.ACTION_MOVE:
          Vector2 ba = new Vector2(a.x - b.x, a.y - b.y);
          Vector2 bc = new Vector2(pSceneTouchEvent.getX() - b.x, pSceneTouchEvent.getY() - b.y);
          
          float dot = (ba.x*bc.x + ba.y*bc.y);
          float cross = (ba.x*bc.y - ba.y*bc.x);
          
          float alpha = (float) Math.atan2(cross, dot);
          float degrees = MathUtils.radToDeg(alpha);
          setRotation(getRotation()+-degrees);
          
          //Logger.d("degrees: "+getRotation());
          
          a.x = pSceneTouchEvent.getX();
          a.y = pSceneTouchEvent.getY();
          
          break;
        }
        return true;
      }
    };
    
    squirrel = new Sprite(sprite.getWidth()-4, sprite.getHeight()/2-12, ResourceManager.textureHashMap.get(Config.PLAYER_SPRITE), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager());
    
    sprite.attachChild(squirrel);
    squirrel.setZIndex(-1);
    
    sprite.setRotationCenter(0.5f,  0.5f);
    sprite.animate(300);
    
    parent.registerTouchArea(sprite);
    parent.attachChild(sprite);
    
    but = new Sprite(80, 80, ResourceManager.textureHashMap.get(Config.LAUNCH), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager()) {
      @Override public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, 
          final float pTouchAreaLocalY) {
        Cannon parent = (Cannon)getUserData();
        parent.launch();
        
        return true;
      }
    };
    
    but.setScale(0.3f);
    but.setUserData(this);
    parent.registerTouchArea(but);
    parent.attachChild(but);
    
    active = true;
  }
  
  public void remove() {
    parent.detachChild(sprite);
  }
  
  public void launch() {
    if (!active) return;
    
    float degrees = -1*sprite.getRotation();
    float[] location = squirrel.convertLocalCoordinatesToSceneCoordinates(squirrel.getWidth()/2, squirrel.getHeight()/2);
    PlayerEntity player = new PlayerEntity(parent, location[0], location[1]);
    //Logger.d(location[0] + " : " + location[1]);
    parent.setPlayer(player);
    
    float impx = (float) (Config.IMPULSE[0]*Math.cos(Math.toRadians(degrees)) - Config.IMPULSE[1]*Math.sin(degrees));
    float impy = (float) (Config.IMPULSE[0]*Math.sin(Math.toRadians(degrees)) + Config.IMPULSE[1]*Math.cos(degrees));
    Vector2 newv = new Vector2(impx, impy);
    //Logger.d("degrees: "+degrees);
    //Logger.d(newv.x + " : " + newv.y);
    player.launch(newv);
    
    active = false;
    parent.detachChild(but);
    parent.unregisterTouchArea(but);
    sprite.detachChild(squirrel);
  }
  
}
