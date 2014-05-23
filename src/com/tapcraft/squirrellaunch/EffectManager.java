package com.tapcraft.squirrellaunch;

import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

import com.tapcraft.levels.World;

public final class EffectManager {
  World parent;
  
  public static float DUST_SCALE = 0.5f;
  public static int DUST_RADIUS = 60;
  
  public static float BOUNCE_DUR = 3f;
  
  public EffectManager(World w) {
    parent = w;
  }
  
  public void createDustCloud(float x, float y, float s) {
    float modscale = DUST_SCALE*s;
    for (int i = 0; i < 8; i++) {
      float xoff = (float) ((Math.random()*DUST_RADIUS)-DUST_RADIUS/2);
      float yoff = (float) ((Math.random()*DUST_RADIUS)-DUST_RADIUS/2);
      float scale = (float) ((Math.random()*(modscale/2))+modscale/2);
      float rot = (float) (Math.random()*360);
      float fadetime = (float) ((Math.random()*2.5f));
      Sprite dust = new Sprite(x+xoff, y+yoff, ResourceManager.textureHashMap.get(Config.DUST), 
          GameEngine.getSharedInstance().getVertexBufferObjectManager());
      dust.registerEntityModifier(AniManager.addFade(fadetime, 1, 0));
      dust.setScale(scale);
      dust.setRotation(rot);
      parent.attachChild(dust);
    }
  }
  
  public Text createDropBounceText(String t, boolean vert, boolean stay, float delay, float stay_dur) {
    Text tex = ObjectFactory.createText(0, 0, Config.FON_HUD, t);
    tex.setY(Config.CAMERA_HEIGHT+tex.getHeight()/2);
    tex.setX(Config.CAMERA_WIDTH/2);
    
    MoveYModifier mym = AniManager.dropBounce(BOUNCE_DUR, tex.getY(), Config.CAMERA_HEIGHT/2);
    SequenceEntityModifier seqMod = new SequenceEntityModifier(new DelayModifier(delay), mym);
    
    if (!stay) {
      MoveYModifier mym2 = AniManager.dropBounce(BOUNCE_DUR, Config.CAMERA_HEIGHT/2, -300);//-10-tex.getHeight()/2);
      seqMod = new SequenceEntityModifier(mym, new DelayModifier(stay_dur), mym2);
      seqMod.addModifierListener(AniManager.REMOVE_LISTENER);
    }
    tex.registerEntityModifier(seqMod);
    return tex;
  }
  
  public Rectangle createDimScreen(boolean stay, float opac, float delay, float stay_dur) {
    Rectangle rec = ObjectFactory.createRect(0, 0, Config.CAMERA_WIDTH, Config.CAMERA_HEIGHT);
    rec.setColor(0, 0, 0);
    
    AlphaModifier am = AniManager.addFade(4, 0, opac);
    AlphaModifier am2 = AniManager.addFade(4, opac, 0);
    
    SequenceEntityModifier seqMod;
    
    if (stay) seqMod = new SequenceEntityModifier(new DelayModifier(delay), am);
    else {
      seqMod = new SequenceEntityModifier(new DelayModifier(delay), am, new DelayModifier(stay_dur), am2);
      seqMod.addModifierListener(AniManager.REMOVE_LISTENER);
    }
    
    rec.registerEntityModifier(seqMod);
    
    rec.setX(Config.CAMERA_WIDTH/2);
    rec.setY(Config.CAMERA_HEIGHT/2);
    
    return rec;
  }
}
