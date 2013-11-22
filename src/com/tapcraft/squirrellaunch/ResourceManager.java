package com.tapcraft.squirrellaunch;

import java.util.HashMap;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;

public class ResourceManager {
  public static HashMap<String, ITextureRegion> textureHashMap = new HashMap<String, ITextureRegion>();
  public static HashMap<String, Font>           fontHashMap = new HashMap<String, Font>();
  
  public static void load() {
    
  }
  
  //sometimes need to add textures outside of the general batch loading
  public static void addTexture(ITextureRegion tex, String key) {
    textureHashMap.put(key, tex);
    
  }
  
  public static void addFont(Font fon, String key) {
    fontHashMap.put(key,  fon);
  }
}
