package com.bumptech.glide.manager;

/**
 * An interface for listener to {@link android.app.Fragment} and {@link android.app.Activity}
 * lifecycle events.
 */
public interface LifecycleListener {

  /**
   * Callback for when } or  is called.
   */
  void onStart();

  /**
   * Callback for when } or } is called.
   */
  void onStop();

  /**
   * Callback for when } or  is called.
   */
  void onDestroy();
}
