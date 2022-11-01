package io.github.shiruka.api.base;

import io.github.shiruka.api.common.Floors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents three-dimensional vectors.
 *
 * @param x the x.
 * @param y the y.
 * @param z the z.
 */
public record Vector3d(double x, double y, double z) {
  /**
   * the zero vector.
   */
  public static final Vector3d ZERO = Vector3d.zero();

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
  public static Vector3d of(final double x, final double y, final double z) {
    return new Vector3d(x, y, z);
  }

  /**
   * creates a zero vector.
   *
   * @return zero vector.
   */
  @NotNull
  public static Vector3d zero() {
    return Vector3d.of(0.0d, 0.0d, 0.0d);
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
  public Vector3d add(final double x, final double y, final double z) {
    return Vector3d.of(this.x + x, this.y + y, this.z + z);
  }

  /**
   * adds the given vector to {@code this}.
   *
   * @param vector the vector to add.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3d add(@NotNull final Vector3d vector) {
    return this.add(vector.x(), vector.y(), vector.z());
  }

  /**
   * obtains {@code this} as {@link Vector3i}.
   *
   * @return vector 3i
   */
  @NotNull
  public Vector3i asVector3i() {
    return Vector3i.of(this.floorX(), this.floorY(), this.floorZ());
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
  public Vector3d divide(final double x, final double y, final double z) {
    return Vector3d.of(this.x / x, this.y / y, this.z / z);
  }

  /**
   * divides the given vector with {@code this}.
   *
   * @param vector the vector to divide.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3d divide(@NotNull final Vector3d vector) {
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
  public Vector3d multiply(final double x, final double y, final double z) {
    return Vector3d.of(this.x * x, this.y * y, this.z * z);
  }

  /**
   * multiplies the given vector to {@code this}.
   *
   * @param vector the vector to multiply.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3d multiply(@NotNull final Vector3d vector) {
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
  public Vector3d subtract(final double x, final double y, final double z) {
    return Vector3d.of(this.x - x, this.y - y, this.z - z);
  }

  /**
   * subtracts the given vector from {@code this}.
   *
   * @param vector the vector to subtract.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3d subtract(@NotNull final Vector3d vector) {
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
  public Vector3d x(final double x) {
    return Vector3d.of(x, this.y, this.z);
  }

  /**
   * sets the y.
   *
   * @param y the y to set.
   *
   * @return a newly created vector with the new y value.
   */
  @NotNull
  public Vector3d y(final double y) {
    return Vector3d.of(this.x, y, this.z);
  }

  /**
   * sets the z.
   *
   * @param z the z to set.
   *
   * @return a newly created vector with the new z value.
   */
  @NotNull
  public Vector3d z(final double z) {
    return Vector3d.of(this.x, this.y, z);
  }
}
