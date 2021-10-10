package io.github.shiruka.api.nbt.primitive;

import io.github.shiruka.api.nbt.TagTypes;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link NumberTagEnvelope}.
 */
public final class LongTag extends NumberTagEnvelope {

  /**
   * ctor.
   *
   * @param original the original.
   */
  public LongTag(final long original) {
    super(original);
  }

  @NotNull
  @Override
  public LongTag asLong() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.LONG;
  }

  @Override
  public boolean isLong() {
    return true;
  }
}
