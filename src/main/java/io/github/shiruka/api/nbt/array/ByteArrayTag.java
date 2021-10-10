package io.github.shiruka.api.nbt.array;

import io.github.shiruka.api.nbt.ArrayTag;
import io.github.shiruka.api.nbt.TagTypes;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents byte arrays.
 */
public final class ByteArrayTag implements ArrayTag<Byte> {

  /**
   * the original.
   */
  @NotNull
  private final Byte[] original;

  /**
   * the primitive original.
   */
  private final byte @NotNull [] primitiveOriginal;

  /**
   * ctor.
   *
   * @param original the original.
   */
  public ByteArrayTag(final byte... original) {
    this.primitiveOriginal = original.clone();
    this.original = ArrayUtils.toObject(this.primitiveOriginal);
  }

  @NotNull
  @Override
  public ByteArrayTag asByteArray() {
    return this;
  }

  @NotNull
  @Override
  public TagTypes getType() {
    return TagTypes.BYTE_ARRAY;
  }

  @Override
  public boolean isByteArray() {
    return true;
  }

  @NotNull
  @Override
  public Byte get(final int index) {
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
  public byte @NotNull [] primitiveValue() {
    return this.primitiveOriginal.clone();
  }

  @Override
  public String toString() {
    return Arrays.toString(this.original);
  }

  @NotNull
  @Override
  public Byte @NotNull [] value() {
    return this.original.clone();
  }
}
