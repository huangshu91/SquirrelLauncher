package com.tapcraft.squirrellaunch;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.ui.activity.BaseActivity;

public class SplashScene extends Scene {
  GameEngine parent;
  
  public SplashScene() {
    setBackground(new Background(0f, 0f, 0.500f));
    parent = GameEngine.getSharedInstance();

    
    //Text title = new Text(0, 0, parent.hudFont2, parent.getString(R.string.splash), parent.getVertexBufferObjectManager());
    
    //title.setPosition(parent.mCamera.getWidth() / 2, parent.mCamera.getHeight() /2);
    //title.setColor(1f, 1f, 0f);
    
    //attachChild(title);
    /*
    final LoopEntityModifier entityModifier = new LoopEntityModifier(new SequenceEntityModifier(
    new ScaleModifier(.7f, .5f, 1.0f, .5f, 1.0f),
    new ScaleModifier(.7f, 1f, .8f, 1f, .8f),
    new ScaleModifier(.7f, .8f, 1.0f, .8f, 1.0f),
    new ScaleModifier(.7f, 1f, .8f, 1f, .8f),
    new ScaleModifier(.7f, .8f, 1.0f, .8f, 1.0f),
    new ScaleModifier(.7f, 1f, .8f, 1f, .8f)));
    
    title.registerEntityModifier(entityModifier);
    */
  }
}
