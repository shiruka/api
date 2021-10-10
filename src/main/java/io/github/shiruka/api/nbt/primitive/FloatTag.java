package io.github.shiruka.api.nbt.primitive;

import io.github.shiruka.api.nbt.TagTypes;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link NumberTagEnvelope}.
 */
public final class FloatTag extends NumberTagEnvelope {

  /**
   * ctor.
   *
   * @param original the original.
   */
  public FloatTag(final float original) {
    super(original);
  }

  @NotNull
  @Override
  public FloatTag asFloat() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.FLOAT;
  }

  @Override
  public boolean isFloat() {
    return true;
  }
}
