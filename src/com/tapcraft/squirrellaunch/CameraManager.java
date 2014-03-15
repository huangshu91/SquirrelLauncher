package com.tapcraft.squirrellaunch;

import org.andengine.engine.camera.SmoothCamera;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.controller.MultiTouch;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.PinchZoomDetector.IPinchZoomDetectorListener;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.input.touch.detector.SurfaceScrollDetector;

import com.badlogic.gdx.math.Vector2;
import com.tapcraft.levels.World;
import com.tapcraft.util.Logger;

public class CameraManager implements IOnSceneTouchListener,
    IPinchZoomDetectorListener, IScrollDetectorListener {

  private SmoothCamera          mCamera;
  private GameEngine            parent;
  private World                 curWorld;

  public boolean                multiSupport;

  private PinchZoomDetector     detect;
  private SurfaceScrollDetector scroll;

  private float                 startZoom;
  public boolean                active;
  public boolean                scrolling;
  
  private Vector2               scrollOffset;

  public CameraManager() {
    parent = GameEngine.getSharedInstance();
    mCamera = new SmoothCamera(0, 0, Config.CAMERA_WIDTH, Config.CAMERA_HEIGHT,
        Config.CAM_VEL, Config.CAM_VEL, Config.CAM_ZOOM_VEL);

    scroll = new SurfaceScrollDetector(this);
    if (MultiTouch.isSupported(parent)) {
      detect = new PinchZoomDetector(this);
      detect.setEnabled(true);
      Logger.d("multitouch");
    } else {
      detect = null;
      Logger.d("no multitouch");
    }

    scrollOffset = new Vector2(0, 0);
    mCamera.setZoomFactor(0.8f);
  }

  public SmoothCamera getCamera() {
    return mCamera;
  }
  
  public void setWorld(World w) {
    curWorld = w;
  }

  @Override
  public void onScrollStarted(ScrollDetector pScollDetector, int pPointerID,
      float pDistanceX, float pDistanceY) {
    if (curWorld != null) curWorld.camStart();
    scrollOffset.x = 0;
    scrollOffset.y = 0;
  }

  @Override
  public void onScroll(ScrollDetector pScollDetector, int pPointerID,
      float pDistanceX, float pDistanceY) {
    final float zoomFactor = mCamera.getZoomFactor();
    mCamera.offsetCenter(-pDistanceX*1.5f / zoomFactor, pDistanceY*1.5f / zoomFactor);
    
    scrollOffset.x += pDistanceX;
    scrollOffset.y += pDistanceY;
  }

  @Override
  public void onScrollFinished(ScrollDetector pScollDetector, int pPointerID,
      float pDistanceX, float pDistanceY) {
    final float zoomFactor = mCamera.getZoomFactor();
    mCamera.offsetCenter((-scrollOffset.x/2) / zoomFactor, (scrollOffset.y/2) / zoomFactor);
    
    if (curWorld != null) curWorld.camEnd();
  }

  @Override
  public void onPinchZoomStarted(PinchZoomDetector pPinchZoomDetector,
      TouchEvent pSceneTouchEvent) {
    startZoom = mCamera.getZoomFactor();
    if (curWorld != null) curWorld.camStart();
  }

  @Override
  public void onPinchZoom(PinchZoomDetector pPinchZoomDetector,
      TouchEvent pTouchEvent, float pZoomFactor) {
    if (pZoomFactor != 1) {
      float newZoom = startZoom * pZoomFactor;
      
      //this code to limit zoom lags, investigate in future
      //if (Config.CAMERA_HEIGHT/newZoom > curWorld.getWorldHeight() || 
      //    Config.CAMERA_WIDTH/newZoom > curWorld.getWorldWidth()) return;
      
      if (newZoom < Config.MAX_ZOOM && newZoom > Config.MIN_ZOOM) 
        mCamera.setZoomFactor(newZoom);
    }

  }

  @Override
  public void onPinchZoomFinished(PinchZoomDetector pPinchZoomDetector,
      TouchEvent pTouchEvent, float pZoomFactor) {
    if (curWorld != null) curWorld.camEnd();
  }

  @Override
  public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {    
    if (detect != null) {
      detect.onTouchEvent(pSceneTouchEvent);

      if (detect.isZooming()) {
        scroll.setEnabled(false);
      } else {
        if (pSceneTouchEvent.isActionDown()) {
          scroll.setEnabled(true);
        }
        scroll.onTouchEvent(pSceneTouchEvent);
      }
    } else {
      scroll.onTouchEvent(pSceneTouchEvent);
    }

    return true;
  }

}
