package com.bumptech.glide.signature;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Key;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/**
 * A unique signature based on metadata data from the media store that detects common changes to
 * media store files like edits, rotations, and temporary file replacement.
 */
public class MediaStoreSignature implements Key {
  @NonNull private final String mimeType;
  private final long dateModified;
  private final int orientation;

  /**
   * Constructor for {@link com.bumptech.glide.signature.MediaStoreSignature}.
   *
   * @param mimeType The mime type of the media store media. Ok to default to empty string "". See
   *      or .
   * @param dateModified The date modified time of the media store media. Ok to default to 0. See
   *      or .
   * @param orientation The orientation of the media store media. Ok to default to 0. See .
   */
  public MediaStoreSignature(@Nullable String mimeType, long dateModified, int orientation) {
    this.mimeType = mimeType == null ? "" : mimeType;
    this.dateModified = dateModified;
    this.orientation = orientation;
  }

  @SuppressWarnings({"PMD.SimplifyBooleanReturns", "RedundantIfStatement"})
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    MediaStoreSignature that = (MediaStoreSignature) o;

    if (dateModified != that.dateModified) {
      return false;
    }
    if (orientation != that.orientation) {
      return false;
    }
    return mimeType.equals(that.mimeType);
  }

  @Override
  public int hashCode() {
    int result = mimeType.hashCode();
    result = 31 * result + (int) (dateModified ^ (dateModified >>> 32));
    result = 31 * result + orientation;
    return result;
  }

  @Override
  public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
    byte[] data = ByteBuffer.allocate(12).putLong(dateModified).putInt(orientation).array();
    messageDigest.update(data);
    messageDigest.update(mimeType.getBytes(CHARSET));
  }
}
