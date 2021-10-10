package io.github.shiruka.api.nbt.primitive;

import io.github.shiruka.api.nbt.TagTypes;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link NumberTagEnvelope}.
 */
public final class IntTag extends NumberTagEnvelope {

  /**
   * ctor.
   *
   * @param original the original.
   */
  public IntTag(final int original) {
    super(original);
  }

  @NotNull
  @Override
  public IntTag asInt() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.INT;
  }

  @Override
  public boolean isInt() {
    return true;
  }
}
