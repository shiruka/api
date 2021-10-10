package io.github.shiruka.api.math.vectors;

import io.github.shiruka.api.math.Floors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents two dimensional vectors.
 *
 * @param x the x.
 * @param y the y.
 */
public final record Vector2f(
  float x,
  float y
) {

  /**
   * the zero vector.
   */
  public static final Vector2f ZERO = Vector2f.zero();

  /**
   * creates a vector.
   *
   * @param x the x to create.
   * @param y the y to create.
   *
   * @return a newly created vector.
   */
  @NotNull
  public static Vector2f of(final float x, final float y) {
    return new Vector2f(x, y);
  }

  /**
   * creates a zero vector.
   *
   * @return zero vector.
   */
  @NotNull
  public static Vector2f zero() {
    return Vector2f.of(0.0f, 0.0f);
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
  public Vector2f add(final float x, final float y) {
    return Vector2f.of(this.x + x, this.y + y);
  }

  /**
   * adds the given vector to {@code this}.
   *
   * @param vector the vector to add.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector2f add(@NotNull final Vector2f vector) {
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
  public Vector2f divide(final float x, final float y) {
    return Vector2f.of(this.x / x, this.y / y);
  }

  /**
   * divides the given vector with {@code this}.
   *
   * @param vector the vector to divide.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector2f divide(@NotNull final Vector2f vector) {
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
  public Vector2f multiply(final float x, final float y) {
    return Vector2f.of(this.x * x, this.y * y);
  }

  /**
   * multiplies the given vector to {@code this}.
   *
   * @param vector the vector to multiply.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector2f multiply(@NotNull final Vector2f vector) {
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
  public Vector2f subtract(final float x, final float y) {
    return Vector2f.of(this.x - x, this.y - y);
  }

  /**
   * subtracts the given vector from {@code this}.
   *
   * @param vector the vector to subtract.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector2f subtract(@NotNull final Vector2f vector) {
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
  public Vector2f x(final float x) {
    return Vector2f.of(x, this.y);
  }

  /**
   * sets the y.
   *
   * @param y the y to set.
   *
   * @return a newly created vector with the new y value.
   */
  @NotNull
  public Vector2f y(final float y) {
    return Vector2f.of(this.x, y);
  }
}
