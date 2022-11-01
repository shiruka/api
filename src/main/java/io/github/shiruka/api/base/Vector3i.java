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
public record Vector3i(int x, int y, int z) {
  /**
   * the zero vector.
   */
  public static final Vector3i ZERO = Vector3i.zero();

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
  public static Vector3i of(final int x, final int y, final int z) {
    return new Vector3i(x, y, z);
  }

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
  public static Vector3i of(final double x, final double y, final double z) {
    return Vector3i.of(Floors.floor(x), Floors.floor(y), Floors.floor(z));
  }

  /**
   * creates a zero vector.
   *
   * @return zero vector.
   */
  @NotNull
  public static Vector3i zero() {
    return Vector3i.of(0, 0, 0);
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
  public Vector3i add(final int x, final int y, final int z) {
    return Vector3i.of(this.x + x, this.y + y, this.z + z);
  }

  /**
   * adds the given vector to {@code this}.
   *
   * @param vector the vector to add.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3i add(@NotNull final Vector3i vector) {
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
  public Vector3i divide(final int x, final int y, final int z) {
    return Vector3i.of(this.x / x, this.y / y, this.z / z);
  }

  /**
   * divides the given vector with {@code this}.
   *
   * @param vector the vector to divide.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3i divide(@NotNull final Vector3i vector) {
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
  public Vector3i multiply(final int x, final int y, final int z) {
    return Vector3i.of(this.x * x, this.y * y, this.z * z);
  }

  /**
   * multiplies the given vector to {@code this}.
   *
   * @param vector the vector to multiply.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3i multiply(@NotNull final Vector3i vector) {
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
  public Vector3i subtract(final int x, final int y, final int z) {
    return Vector3i.of(this.x - x, this.y - y, this.z - z);
  }

  /**
   * subtracts the given vector from {@code this}.
   *
   * @param vector the vector to subtract.
   *
   * @return a newly created vector.
   */
  @NotNull
  public Vector3i subtract(@NotNull final Vector3i vector) {
    return this.add(vector.x(), vector.y(), vector.z());
  }

  /**
   * converts {@code this} to {@link Vector3f}.
   *
   * @return vector 3f.
   */
  @NotNull
  public Vector3f toFloat() {
    return Vector3f.of(this.x, this.y, this.z);
  }

  /**
   * sets the x.
   *
   * @param x the x to set.
   *
   * @return a newly created vector with the new x value.
   */
  @NotNull
  public Vector3i x(final int x) {
    return Vector3i.of(x, this.y, this.z);
  }

  /**
   * sets the y.
   *
   * @param y the y to set.
   *
   * @return a newly created vector with the new y value.
   */
  @NotNull
  public Vector3i y(final int y) {
    return Vector3i.of(this.x, y, this.z);
  }

  /**
   * sets the z.
   *
   * @param z the z to set.
   *
   * @return a newly created vector with the new z value.
   */
  @NotNull
  public Vector3i z(final int z) {
    return Vector3i.of(this.x, this.y, z);
  }
}
