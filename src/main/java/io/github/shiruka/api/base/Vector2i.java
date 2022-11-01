package io.github.shiruka.api.base;

import io.github.shiruka.api.common.Floors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents two-dimensional vectors.
 *
 * @param x the x.
 * @param y the y.
 */
public record Vector2i(int x, int y) {
  /**
   * the zero vector.
   */
  public static final Vector2i ZERO = Vector2i.zero();

  /**
   * creates a vector.
   *
   * @param x the x to create.
   * @param y the y to create.
   *
   * @return a newly created vector.
   */
  @NotNull
  public static Vector2i of(final int x, final int y) {
    return new Vector2i(x, y);
  }

  /**
   * creates a zero vector.
   *
   * @return zero vector.
   */
  @NotNull
  public static Vector2i zero() {
    return Vector2i.of(0, 0);
  }

  /**
   * adds the given x, y and, z to {@code this}.
   *
   * @param x the x to add.
   * @param y the y to add.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector2i add(final int x, final int y) {
    return Vector2i.of(this.x + x, this.y + y);
  }

  /**
   * adds the given vector to {@code this}.
   *
   * @param vector the vector to add.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector2i add(@NotNull final Vector2i vector) {
    return this.add(vector.x(), vector.y());
  }

  /**
   * divides the given x, y and, z with {@code this}.
   *
   * @param x the x to divide.
   * @param y the y to divide.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector2i divide(final int x, final int y) {
    return Vector2i.of(this.x / x, this.y / y);
  }

  /**
   * divides the given vector with {@code this}.
   *
   * @param vector the vector to divide.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector2i divide(@NotNull final Vector2i vector) {
    return this.add(vector.x(), vector.y());
  }

  /**
   * floors the x.
   *
   * @return floored x.
   */
  public int floorX() {
    return Floors.floor(this.x);
  }

  /**
   * floors the y.
   *
   * @return floored y.
   */
  public int floorY() {
    return Floors.floor(this.y);
  }

  /**
   * multiplies the given x, y and, z to {@code this}.
   *
   * @param x the x to multiply.
   * @param y the y to multiply.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector2i multiply(final int x, final int y) {
    return Vector2i.of(this.x * x, this.y * y);
  }

  /**
   * multiplies the given vector to {@code this}.
   *
   * @param vector the vector to multiply.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector2i multiply(@NotNull final Vector2i vector) {
    return this.add(vector.x(), vector.y());
  }

  /**
   * subtracts the given x, y and, z from {@code this}.
   *
   * @param x the x to subtract.
   * @param y the y to subtract.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector2i subtract(final int x, final int y) {
    return Vector2i.of(this.x - x, this.y - y);
  }

  /**
   * subtracts the given vector from {@code this}.
   *
   * @param vector the vector to subtract.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector2i subtract(@NotNull final Vector2i vector) {
    return this.add(vector.x(), vector.y());
  }

  /**
   * sets the x.
   *
   * @param x the x to set.
   *
   * @return a newly created vector with the new x value.
   */
  @NotNull
  public Vector2i x(final int x) {
    return Vector2i.of(x, this.y);
  }

  /**
   * sets the y.
   *
   * @param y the y to set.
   *
   * @return a newly created vector with the new y value.
   */
  @NotNull
  public Vector2i y(final int y) {
    return Vector2i.of(this.x, y);
  }
}
