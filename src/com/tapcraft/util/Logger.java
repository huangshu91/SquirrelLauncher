package com.tapcraft.util;

import com.tapcraft.squirrellaunch.Config;

import android.util.Log;

public class Logger {
  private final static boolean LOGGING_ENABLED = true;

  public static void d(String msg) {
    if (LOGGING_ENABLED) {
      Log.d(Config.TAG, msg);
    }
  }

  public static void e(String msg) {
    if (LOGGING_ENABLED) {
      Log.e(Config.TAG, msg);
    }
  }

  public static void i(String msg) {
    if (LOGGING_ENABLED) {
      Log.i(Config.TAG, msg);
    }
  }

  public static void v(String msg) {
    if (LOGGING_ENABLED) {
      Log.v(Config.TAG, msg);
    }
  }

  public static void w(String msg) {
    if (LOGGING_ENABLED) {
      Log.w(Config.TAG, msg);
    }
  }
}