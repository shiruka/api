package io.github.shiruka.api.math;

/**
 * a class that contains utility methods for flooring.
 */
public final class Floors {

  /**
   * ctor.
   */
  private Floors() {
  }

  /**
   * rounds the value up to the closest integer.
   *
   * @param value the value to floor.
   *
   * @return the closest long.
   */
  public static int ceil(final double value) {
    var possible_result = (int) value;
    if (value - possible_result > 0) {
      possible_result++;
    }
    return possible_result;
  }

  /**
   * rounds the value up to the closest integer.
   *
   * @param value the value to floor.
   *
   * @return the closest long.
   */
  public static int ceil(final float value) {
    var possible_result = (int) value;
    if (value - possible_result > 0) {
      possible_result++;
    }
    return possible_result;
  }

  /**
   * rounds the value up to the closest long.
   *
   * @param value the value to floor.
   *
   * @return the closest long.
   */
  public static long ceil64(final double value) {
    var possible_result = (long) value;
    if (value - possible_result > 0) {
      possible_result++;
    }
    return possible_result;
  }

  /**
   * rounds the value up to the closest long.
   *
   * @param value the value to floor.
   *
   * @return the closest long.
   */
  public static long ceil64(final float value) {
    var possible_result = (long) value;
    if (value - possible_result > 0) {
      possible_result++;
    }
    return possible_result;
  }

  /**
   * rounds the value down to the closest integer.
   *
   * @param value the value to floor.
   *
   * @return the closest integer.
   */
  public static int floor(final float value) {
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
  public static int floor(final double value) {
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
  public static long floor64(final double value) {
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
  public static long floor64(final float value) {
    final var y = (long) value;
    return value < y ? y - 1 : y;
  }
}
