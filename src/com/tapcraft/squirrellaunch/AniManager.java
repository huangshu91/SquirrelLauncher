package com.tapcraft.squirrellaunch;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.util.modifier.IModifier;
import org.andengine.util.modifier.ease.EaseBounceOut;

public final class AniManager {
  public static IEntityModifierListener REMOVE_LISTENER = new IEntityModifierListener() {
    @Override
    public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
      // TODO Auto-generated method stub 
    }
    @Override
    public void onModifierFinished(IModifier<IEntity> pModifier, final IEntity pItem) {
      GameEngine.getSharedInstance().runOnUpdateThread(new Runnable() {
        @Override
        public void run() {
          pItem.detachSelf();
        }
      });
    }
  };
  
  public static AlphaModifier addFade(float t, float s, float e) {
    AlphaModifier am = new AlphaModifier(t, s, e);
    //am.addModifierListener(AniManager.REMOVE_LISTENER);
    return am;
  }
  
  public static MoveYModifier dropBounce(float d, float from, float to) {
    return new MoveYModifier(d, from, to, EaseBounceOut.getInstance());
  }
  
}
