package io.github.shiruka.api.nbt.array;

import io.github.shiruka.api.nbt.ArrayTag;
import io.github.shiruka.api.nbt.TagTypes;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents long arrays.
 */
public final class LongArrayTag implements ArrayTag<Long> {

  /**
   * the original.
   */
  @NotNull
  private final Long[] original;

  /**
   * the primitive original.
   */
  private final long @NotNull [] primitiveOriginal;

  /**
   * ctor.
   *
   * @param original the original.
   */
  public LongArrayTag(final long... original) {
    this.primitiveOriginal = original.clone();
    this.original = ArrayUtils.toObject(this.primitiveOriginal);
  }

  @NotNull
  @Override
  public LongArrayTag asLongArray() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.LONG_ARRAY;
  }

  @Override
  public boolean isLongArray() {
    return true;
  }

  @NotNull
  @Override
  public Long get(final int index) {
    ArrayTag.checkIndex(index, this.original.length);
    return this.original[index];
  }

  @Override
  public int size() {
    return this.original.length;
  }

  /**
   * obtains the primitive original value.
   *
   * @return primitive value.
   */
  public long @NotNull [] primitiveValue() {
    return this.primitiveOriginal.clone();
  }

  @Override
  public String toString() {
    return Arrays.toString(this.original);
  }

  @NotNull
  @Override
  public Long @NotNull [] value() {
    return this.original.clone();
  }
}
