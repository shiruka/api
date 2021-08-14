package io.github.shiruka.api.math;

import org.jetbrains.annotations.NotNull;

/**
 * a class that represents three dimensional vectors.
 *
 * @param x the x.
 * @param y the y.
 * @param z the z.
 */
public record Vector3D(
  double x,
  double y,
  double z
) {

  /**
   * the zero vector.
   */
  private static final Vector3D ZERO = Vector3D.zero();

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
  public static Vector3D of(final double x, final double y, final double z) {
    return new Vector3D(x, y, z);
  }

  /**
   * creates a zero vector.
   *
   * @return zero vector.
   */
  @NotNull
  public static Vector3D zero() {
    return Vector3D.of(0.0d, 0.0d, 0.0d);
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
  public Vector3D add(final double x, final double y, final double z) {
    return Vector3D.of(this.x + x, this.y + y, this.z + z);
  }

  /**
   * adds the given vector to {@code this}.
   *
   * @param vector the vector to add.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3D add(@NotNull final Vector3D vector) {
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
  public Vector3D divide(final double x, final double y, final double z) {
    return Vector3D.of(this.x / x, this.y / y, this.z / z);
  }

  /**
   * divides the given vector with {@code this}.
   *
   * @param vector the vector to divide.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3D divide(@NotNull final Vector3D vector) {
    return this.add(vector.x(), vector.y(), vector.z());
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
  public Vector3D multiply(final double x, final double y, final double z) {
    return Vector3D.of(this.x * x, this.y * y, this.z * z);
  }

  /**
   * multiplies the given vector to {@code this}.
   *
   * @param vector the vector to multiply.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3D multiply(@NotNull final Vector3D vector) {
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
  public Vector3D subtract(final double x, final double y, final double z) {
    return Vector3D.of(this.x - x, this.y - y, this.z - z);
  }

  /**
   * subtracts the given vector from {@code this}.
   *
   * @param vector the vector to subtract.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3D subtract(@NotNull final Vector3D vector) {
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
  public Vector3D x(final double x) {
    return Vector3D.of(x, this.y, this.z);
  }

  /**
   * sets the y.
   *
   * @param y the y to set.
   *
   * @return a newly created vector with the new y value.
   */
  @NotNull
  public Vector3D y(final double y) {
    return Vector3D.of(this.x, y, this.z);
  }

  /**
   * sets the z.
   *
   * @param z the z to set.
   *
   * @return a newly created vector with the new z value.
   */
  @NotNull
  public Vector3D z(final double z) {
    return Vector3D.of(this.x, this.y, z);
  }
}
