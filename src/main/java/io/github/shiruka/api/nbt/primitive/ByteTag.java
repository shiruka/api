package io.github.shiruka.api.nbt.primitive;

import io.github.shiruka.api.nbt.TagTypes;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link NumberTagEnvelope}.
 */
public final class ByteTag extends NumberTagEnvelope {

  /**
   * ctor.
   *
   * @param original the original.
   */
  public ByteTag(final byte original) {
    super(original);
  }

  @NotNull
  @Override
  public ByteTag asByte() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.BYTE;
  }

  @Override
  public boolean isByte() {
    return true;
  }
}
