package io.github.shiruka.api.nbt.array;

import io.github.shiruka.api.nbt.ArrayTag;
import io.github.shiruka.api.nbt.TagTypes;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents int arrays.
 */
public final class IntArrayTag implements ArrayTag<Integer> {

  /**
   * the original.
   */
  @NotNull
  private final Integer @NotNull [] original;

  /**
   * the primitive original.
   */
  private final int @NotNull [] primitiveOriginal;

  /**
   * ctor.
   *
   * @param original the original.
   */
  public IntArrayTag(final int... original) {
    this.primitiveOriginal = original.clone();
    this.original = ArrayUtils.toObject(this.primitiveOriginal);
  }

  @NotNull
  @Override
  public IntArrayTag asIntArray() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.INT_ARRAY;
  }

  @Override
  public boolean isIntArray() {
    return true;
  }

  @NotNull
  @Override
  public Integer get(final int index) {
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
  public int @NotNull [] primitiveValue() {
    return this.primitiveOriginal.clone();
  }

  @Override
  public String toString() {
    return Arrays.toString(this.original);
  }

  @NotNull
  @Override
  public Integer @NotNull [] value() {
    return this.original.clone();
  }
}
