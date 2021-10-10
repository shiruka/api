package io.github.shiruka.api.nbt;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine array tags.
 *
 * @param <T> type of arrays.
 */
public interface ArrayTag<T> extends PrimitiveTag<T[]> {

  /**
   * checks indexes of the array tag.
   *
   * @param index the index to check.
   * @param length the length to check.
   */
  static void checkIndex(final int index, final int length) {
    Preconditions.checkElementIndex(index, length, String.format("Index out of bounds: %s", index));
  }

  @Override
  @NotNull
  default ArrayTag<?> asArray() {
    return this;
  }

  @Override
  default boolean isArray() {
    return true;
  }

  /**
   * gets the value.
   *
   * @param index the index to get.
   *
   * @return value at {@code index}.
   */
  @NotNull
  T get(int index);

  /**
   * gets the size of the array.
   *
   * @return array size.
   */
  int size();
}
