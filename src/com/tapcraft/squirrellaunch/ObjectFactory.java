package com.tapcraft.squirrellaunch;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

public class ObjectFactory {
  public static Sprite createSprite(float x, float y, String texture) {
    return new Sprite(x, y, ResourceManager.textureHashMap.get(texture), 
        GameEngine.getSharedInstance().getVertexBufferObjectManager());
  }
  
  public static Text createText(float x, float y, String font, String text) {
    return new Text(x, y, ResourceManager.fontHashMap.get(font), text, 
        GameEngine.getSharedInstance().getVertexBufferObjectManager());
  }
  
  public static Rectangle createRect(float x, float y, float w, float h) {
    return new Rectangle(x,y,w,h,
        GameEngine.getSharedInstance().getVertexBufferObjectManager());
  }
}
