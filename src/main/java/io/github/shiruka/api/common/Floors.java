package io.github.shiruka.api.common;

import lombok.experimental.UtilityClass;

/**
 * a class that contains utility methods for flooring.
 */
@UtilityClass
public class Floors {

  /**
   * rounds the value up to the closest integer.
   *
   * @param value the value to floor.
   *
   * @return the closest long.
   */
  public int ceil(final double value) {
    var possibleResult = (int) value;
    if (value - possibleResult > 0) {
      possibleResult++;
    }
    return possibleResult;
  }

  /**
   * rounds the value up to the closest integer.
   *
   * @param value the value to floor.
   *
   * @return the closest long.
   */
  public int ceil(final float value) {
    var possibleResult = (int) value;
    if (value - possibleResult > 0) {
      possibleResult++;
    }
    return possibleResult;
  }

  /**
   * rounds the value up to the closest long.
   *
   * @param value the value to floor.
   *
   * @return the closest long.
   */
  public long ceil64(final double value) {
    var possibleResult = (long) value;
    if (value - possibleResult > 0) {
      possibleResult++;
    }
    return possibleResult;
  }

  /**
   * rounds the value up to the closest long.
   *
   * @param value the value to floor.
   *
   * @return the closest long.
   */
  public long ceil64(final float value) {
    var possibleResult = (long) value;
    if (value - possibleResult > 0) {
      possibleResult++;
    }
    return possibleResult;
  }

  /**
   * rounds the value down to the closest integer.
   *
   * @param value the value to floor.
   *
   * @return the closest integer.
   */
  public int floor(final float value) {
    final var y = (int) value;
    return value < y ? y - 1 : y;
  }

  /**
   * rounds the value down to the closest integer.
   *
   * @param value the value to floor.
   *
   * @return the closest integer.
   */
  public int floor(final double value) {
    final var y = (int) value;
    return value < y ? y - 1 : y;
  }

  /**
   * rounds the value down to the closest long.
   *
   * @param value the value to floor.
   *
   * @return the closest long.
   */
  public long floor64(final double value) {
    final var y = (long) value;
    return value < y ? y - 1 : y;
  }

  /**
   * rounds the value down to the closest long.
   *
   * @param value the value to floor.
   *
   * @return the closest long.
   */
  public long floor64(final float value) {
    final var y = (long) value;
    return value < y ? y - 1 : y;
  }
}
