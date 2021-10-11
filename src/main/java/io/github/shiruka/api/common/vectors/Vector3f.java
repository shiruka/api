package io.github.shiruka.api.common.vectors;

import io.github.shiruka.api.common.Floors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents three dimensional vectors.
 *
 * @param x the x.
 * @param y the y.
 * @param z the z.
 */
public final record Vector3f(
  float x,
  float y,
  float z
) {

  /**
   * the zero vector.
   */
  public static final Vector3f ZERO = Vector3f.zero();

  /**
   * creates a vector.
   *
   * @param x the x to create.
   * @param y the y to create.
   * @param z the z to create.
   *
   * @return a newly created vector.
   */
  @NotNull
  public static Vector3f of(final float x, final float y, final float z) {
    return new Vector3f(x, y, z);
  }

  /**
   * creates a zero vector.
   *
   * @return zero vector.
   */
  @NotNull
  public static Vector3f zero() {
    return Vector3f.of(0.0f, 0.0f, 0.0f);
  }

  /**
   * adds the given x, y and, z to {@code this}.
   *
   * @param x the x to add.
   * @param y the y to add.
   * @param z the z to add.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3f add(final float x, final float y, final float z) {
    return Vector3f.of(this.x + x, this.y + y, this.z + z);
  }

  /**
   * adds the given vector to {@code this}.
   *
   * @param vector the vector to add.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3f add(@NotNull final Vector3f vector) {
    return this.add(vector.x(), vector.y(), vector.z());
  }

  /**
   * divides the given x, y and, z with {@code this}.
   *
   * @param x the x to divide.
   * @param y the y to divide.
   * @param z the z to divide.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3f divide(final float x, final float y, final float z) {
    return Vector3f.of(this.x / x, this.y / y, this.z / z);
  }

  /**
   * divides the given vector with {@code this}.
   *
   * @param vector the vector to divide.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3f divide(@NotNull final Vector3f vector) {
    return this.add(vector.x(), vector.y(), vector.z());
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
   * floors the z.
   *
   * @return floored z.
   */
  public int floorZ() {
    return Floors.floor(this.z);
  }

  /**
   * multiplies the given x, y and, z to {@code this}.
   *
   * @param x the x to multiply.
   * @param y the y to multiply.
   * @param z the z to multiply.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3f multiply(final float x, final float y, final float z) {
    return Vector3f.of(this.x * x, this.y * y, this.z * z);
  }

  /**
   * multiplies the given vector to {@code this}.
   *
   * @param vector the vector to multiply.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3f multiply(@NotNull final Vector3f vector) {
    return this.add(vector.x(), vector.y(), vector.z());
  }

  /**
   * subtracts the given x, y and, z from {@code this}.
   *
   * @param x the x to subtract.
   * @param y the y to subtract.
   * @param z the z to subtract.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3f subtract(final float x, final float y, final float z) {
    return Vector3f.of(this.x - x, this.y - y, this.z - z);
  }

  /**
   * subtracts the given vector from {@code this}.
   *
   * @param vector the vector to subtract.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3f subtract(@NotNull final Vector3f vector) {
    return this.add(vector.x(), vector.y(), vector.z());
  }

  /**
   * sets the x.
   *
   * @param x the x to set.
   *
   * @return a newly created vector with the new x value.
   */
  @NotNull
  public Vector3f x(final float x) {
    return Vector3f.of(x, this.y, this.z);
  }

  /**
   * sets the y.
   *
   * @param y the y to set.
   *
   * @return a newly created vector with the new y value.
   */
  @NotNull
  public Vector3f y(final float y) {
    return Vector3f.of(this.x, y, this.z);
  }

  /**
   * sets the z.
   *
   * @param z the z to set.
   *
   * @return a newly created vector with the new z value.
   */
  @NotNull
  public Vector3f z(final float z) {
    return Vector3f.of(this.x, this.y, z);
  }
}
