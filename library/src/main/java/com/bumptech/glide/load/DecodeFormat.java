package com.bumptech.glide.load;

/**
 * Options for setting the value of  for {@link
 * android.graphics.Bitmap}s returned by {@link com.bumptech.glide.load.ResourceDecoder}s.
 *
 * <p>Note - In some cases it may not be possible to obey the requested setting, not all {@link
 * com.bumptech.glide.load.resource.bitmap.Downsampler}s support setting formats and certain images
 * may not be able to be loaded as certain configurations. Therefore this class represents a
 * preference rather than a requirement.
 */
public enum DecodeFormat {
  /**
   * Bitmaps returned by the {@link ResourceDecoder}. should return  for  when
   * possible.
   *
   * <p>On Android O+, this format will use ARGB_8888 only when it's not possible to use . More information is available about hardware Bitmaps
   * here: https://goo.gl/tn2A6k. If you need to disable hardware Bitmaps for a particular request,
   * use {@link com.bumptech.glide.request.RequestOptions#d<a href="isallowHardwareConfig">...</a>()}.
   *
   * <p>GIF images decoded by {@link android.graphics.BitmapFactory} currently use an internal
   * hidden format that is returned as null from . Since
   * we cannot force {@link android.graphics.BitmapFactory} to always return our desired config,
   * this setting is a preference, not a promise.
   */
  PREFER_ARGB_8888,

  /**
   * Bitmaps decoded from image formats that support and/or use alpha (some types of PNGs, GIFs etc)
   * should return  for . Bitmaps decoded from formats that don't support or use
   * alpha should return  for .
   *
   * <p>On Android O+, this format will use RGB_565 only when it's not possible to use .
   */
  PREFER_RGB_565;

  /** The default value for DecodeFormat. */
  public static final DecodeFormat DEFAULT = PREFER_ARGB_8888;
}
