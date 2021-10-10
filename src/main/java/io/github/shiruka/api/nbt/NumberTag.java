package io.github.shiruka.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine numbers.
 */
public interface NumberTag extends PrimitiveTag<Double> {

  @NotNull
  @Override
  default NumberTag asNumber() {
    return this;
  }

  @Override
  default boolean isNumber() {
    return true;
  }

  /**
   * returns the value of the specified number as a {@code byte}.
   *
   * @return the numeric value represented by this object after conversion to type {@code byte}.
   */
  byte byteValue();

  /**
   * returns the value of the specified number as a {@code double}.
   *
   * @return the numeric value represented by this object after conversion to type {@code double}.
   */
  double doubleValue();

  /**
   * returns the value of the specified number as a {@code float}.
   *
   * @return the numeric value represented by this object after conversion to type {@code float}.
   */
  float floatValue();

  /**
   * returns the value of the specified number as an {@code int}.
   *
   * @return the numeric value represented by this object after conversion to type {@code int}.
   */
  int intValue();

  /**
   * returns the value of the specified number as a {@code long}.
   *
   * @return the numeric value represented by this object after conversion to type {@code long}.
   */
  long longValue();

  /**
   * returns the value of the specified number as a {@code short}.
   *
   * @return the numeric value represented by this object after conversion to type {@code short}.
   */
  short shortValue();

  @Override
  @NotNull
  default Double value() {
    return this.doubleValue();
  }
}
