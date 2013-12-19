package com.tapcraft.squirrellaunch;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

public class SplashScene extends Scene {
  private GameEngine parent;
  
  private Sprite bgsprite;
  private Text   flavor;
  
  private String[] loadtext = {"charging capacitors", "loading capapults", "test1", "test2"};
  private int loadindex;
  
  private Rectangle loadbarfill;
  private Entity loadbar;
  
  private int barsize_w;
  private int barsize_h;
  private int curload_per;
  
  public static int BAR_SIZE_W = 310;
  public static int BAR_SIZE_H = 50;
  
  public SplashScene() {
    parent = GameEngine.getSharedInstance();
    setBackground(new Background(0.3804f, 0.6274f, 0.500f));
    
    loadindex = 0;
    bgsprite = ObjectFactory.createSprite(Config.CAMERA_WIDTH/2, Config.CAMERA_HEIGHT/2, Config.TEX_SPLASH);
    
    Rectangle loadbarstroke = ObjectFactory.createRect(Config.CAMERA_WIDTH/2, Config.CAMERA_HEIGHT/2, BAR_SIZE_W + 10, BAR_SIZE_H + 10);
    loadbarstroke.setColor(1.0f, 1.0f, 1.0f);
    Rectangle loadbarback = ObjectFactory.createRect(Config.CAMERA_WIDTH/2, Config.CAMERA_HEIGHT/2, BAR_SIZE_W, BAR_SIZE_H);
    loadbarback.setColor(0f, 0f, 0f);
    loadbar = new Entity();
    loadbar.attachChild(loadbarstroke);
    loadbar.attachChild(loadbarback);
    
    barsize_w = BAR_SIZE_W - 10;
    barsize_h = BAR_SIZE_H - 10;
    curload_per = 0;
    
    loadbarfill = ObjectFactory.createRect(Config.CAMERA_WIDTH/2, Config.CAMERA_HEIGHT/2, 3, barsize_h);
    loadbarfill.setColor(0f, 0.9f, 0f);
    
    flavor = ObjectFactory.createText(Config.CAMERA_WIDTH/2, Config.CAMERA_HEIGHT/2, Config.FON_GROBOLD, loadtext[loadindex]);
    this.attachChild(bgsprite);
    this.attachChild(loadbar);
    this.attachChild(loadbarfill);
    this.attachChild(flavor);
  }
  
  public void loadNext() {
    
  }
  
  public void finishLoad() {
    //ding sound once finished
    this.detachChild(loadbar);
    this.detachChild(loadbarfill);
    this.detachChild(flavor);
  }
}
