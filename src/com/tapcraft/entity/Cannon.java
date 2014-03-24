package com.tapcraft.entity;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.util.math.MathUtils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tapcraft.levels.World;
import com.tapcraft.squirrellaunch.Config;
import com.tapcraft.squirrellaunch.GameEngine;
import com.tapcraft.squirrellaunch.ObjectFactory;
import com.tapcraft.squirrellaunch.ResourceManager;
import com.tapcraft.util.Logger;

public class Cannon extends EntityObj{
  private AnimatedSprite sprite;
  private Sprite squirrel;
  
  private Sprite but;
  private Entity traj;
  
  private boolean active;
  public boolean locked;
  
  public Cannon(World w, int x, int y) {
    super(w, x, y);
    sprite = new AnimatedSprite(x, y, (ITiledTextureRegion) 
        ResourceManager.textureHashMap.get(Config.CANNON), GameEngine.getSharedInstance().getVertexBufferObjectManager()) {
      Vector2 a = new Vector2();
      Vector2 b = new Vector2(getX(), getY());

      @Override
      public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, 
      final float pTouchAreaLocalY) {
        if (locked) return true;
        switch(pSceneTouchEvent.getAction()) {
        case TouchEvent.ACTION_DOWN:
          a.x = pSceneTouchEvent.getX();
          a.y = pSceneTouchEvent.getY();
          active = false;
          ((Cannon)getUserData()).toggleCamera();
          break;
        case TouchEvent.ACTION_MOVE:
          Vector2 ba = new Vector2(a.x - b.x, a.y - b.y);
          Vector2 bc = new Vector2(pSceneTouchEvent.getX() - b.x, pSceneTouchEvent.getY() - b.y);
          
          float dot = (ba.x*bc.x + ba.y*bc.y);
          float cross = (ba.x*bc.y - ba.y*bc.x);
          
          float alpha = (float) Math.atan2(cross, dot);
          float degrees = MathUtils.radToDeg(alpha);
          setRotation(getRotation()+-degrees);
          
          a.x = pSceneTouchEvent.getX();
          a.y = pSceneTouchEvent.getY();
          
          Cannon parent = (Cannon)getUserData();
          parent.simulate();
          
          break;
        case TouchEvent.ACTION_UP:
          active = true;
          ((Cannon)getUserData()).toggleCamera();
          break;
        }
        return true;
      }
    };
    sprite.setScale(0.8f, 1.0f);
    locked = false;
    traj = new Entity();
    
    squirrel = new Sprite(sprite.getWidth()-4, sprite.getHeight()/2-14, ResourceManager.textureHashMap.get(Config.PLAYER_SPRITE), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager());
    
    sprite.attachChild(squirrel);
    squirrel.setZIndex(-1);
    
    sprite.setRotationCenter(0.5f,  0.5f);
    sprite.animate(300);
    sprite.setUserData(this);
    
    parent.registerTouchArea(sprite);
    parent.attachChild(sprite);
    
    float xpos = Config.HUD_PAD + Config.BUTTON_SIZE/2;
    float ypos = xpos;
    but = new Sprite(xpos, ypos, ResourceManager.textureHashMap.get(Config.BUTTON_LAUNCH), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager()) {
      @Override public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, 
          final float pTouchAreaLocalY) {
        Cannon parent = (Cannon)getUserData();
        parent.launch();
        
        return true;
      }
    };
    
    but.setUserData(this);
    parent.getHudMan().getHud().attachChild(but);
    parent.getHudMan().getHud().registerTouchArea(but);
    
    active = true;
    simulate();
  }
  
  public void toggleCamera() {
    parent.getCameraMan().active = !parent.getCameraMan().active;
  }
  
  public void remove() {
    parent.detachChild(sprite);
  }
  
  public void simulate() {
    parent.detachChild(traj);
    traj.detachChildren();
    
    float degrees = -1*sprite.getRotation();
    float[] location = squirrel.convertLocalCoordinatesToSceneCoordinates(squirrel.getWidth()/2, squirrel.getHeight()/2);
    
    float impx = (float) (Config.IMPULSE[0]*Math.cos(Math.toRadians(degrees)) - Config.IMPULSE[1]*Math.sin(degrees));
    float impy = (float) (Config.IMPULSE[0]*Math.sin(Math.toRadians(degrees)) + Config.IMPULSE[1]*Math.cos(degrees));
    Vector2 newv = new Vector2(impx, impy);
    PhysicsWorld simWorld = parent.getSimWorld();
    
    Sprite temp = new Sprite(location[0], location[1], ResourceManager.textureHashMap.get(Config.PLAYER_SPRITE), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager());
    Body simBody = PhysicsFactory.createCircleBody(simWorld, temp, 
        BodyType.DynamicBody, Config.FIXTURE_DEF);
    simBody.setUserData(Config.PLAYER_ID);
    
    PhysicsConnector pcon = new PhysicsConnector(temp, simBody, true, true);
    simWorld.registerPhysicsConnector(pcon);
    simBody.applyLinearImpulse(newv, simBody.getWorldCenter());
    
    for (int i = 0; i < 30; i++) {
      simWorld.onUpdate(1/10f);
      AnimatedSprite circle = ObjectFactory.createAnimSprite(temp.getX(), temp.getY(), Config.CIRCLE);
      if (i%2 == 0) circle.animate(150);
      circle.setScale(0.1f);
      traj.attachChild(circle);
    }
    
    simWorld.unregisterPhysicsConnector(pcon);
    simBody.setActive(false);
    simWorld.destroyBody(simBody);
    parent.attachChild(traj);
  }
  
  public void reactivate() {
    active = true;
    
    parent.getHudMan().getHud().attachChild(but);
    parent.getHudMan().getHud().registerTouchArea(but);
    parent.registerTouchArea(sprite);
    sprite.attachChild(squirrel);
    parent.getCamera().setChaseEntity(null);
    parent.getCamera().setCenter(sprite.getX(), sprite.getY());
  }
  
  public void launch() {
    if (!active) return;
    
    float degrees = -1*sprite.getRotation();
    float[] location = squirrel.convertLocalCoordinatesToSceneCoordinates(squirrel.getWidth()/2, squirrel.getHeight()/2);
    PlayerEntity player = new PlayerEntity(parent, location[0], location[1]);
    parent.setPlayer(player);
    
    parent.getCamera().setCenter(location[0], location[1]);
    
    float impx = (float) (Config.IMPULSE[0]*Math.cos(Math.toRadians(degrees)) - Config.IMPULSE[1]*Math.sin(degrees));
    float impy = (float) (Config.IMPULSE[0]*Math.sin(Math.toRadians(degrees)) + Config.IMPULSE[1]*Math.cos(degrees));
    Vector2 newv = new Vector2(impx, impy);
    player.launch(newv);
    
    active = false;

    parent.getHudMan().getHud().unregisterTouchArea(but);
    parent.getHudMan().getHud().detachChild(but);
    parent.unregisterTouchArea(sprite);
    sprite.detachChild(squirrel);
  }
  
}
