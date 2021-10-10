package io.github.shiruka.api.nbt.primitive;

import io.github.shiruka.api.nbt.TagTypes;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link NumberTagEnvelope}.
 */
public final class DoubleTag extends NumberTagEnvelope {

  /**
   * ctor.
   *
   * @param original the original.
   */
  public DoubleTag(final double original) {
    super(original);
  }

  @NotNull
  @Override
  public DoubleTag asDouble() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.DOUBLE;
  }

  @Override
  public boolean isDouble() {
    return true;
  }
}
