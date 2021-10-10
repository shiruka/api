package io.github.shiruka.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine primitive tags.
 *
 * @param <T> type of the tag's value.
 */
public interface PrimitiveTag<T> extends Tag {

  @NotNull
  @Override
  default PrimitiveTag<T> asPrimitive() {
    return this;
  }

  @Override
  default boolean isPrimitive() {
    return true;
  }

  /**
   * obtains the tag's value.
   *
   * @return the tag's value.
   */
  @NotNull
  T value();
}
