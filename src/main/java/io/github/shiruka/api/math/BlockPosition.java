package io.github.shiruka.api.math;

import org.jetbrains.annotations.NotNull;

/**
 * a class that represents block positions.
 *
 * @param x the x.
 * @param y the y.
 * @param z the z.
 */
public record BlockPosition(
  int x,
  int y,
  int z
) {

  /**
   * the zero position.
   */
  private static final BlockPosition ZERO = BlockPosition.zero();

  /**
   * creates a block position.
   *
   * @param x the x to create.
   * @param y the y to create.
   * @param z the z to create.
   *
   * @return a newly created block position.
   */
  @NotNull
  public static BlockPosition of(final int x, final int y, final int z) {
    return new BlockPosition(x, y, z);
  }

  /**
   * creates a block position.
   *
   * @param vector the vector to create.
   *
   * @return a newly created block position.
   */
  @NotNull
  public static BlockPosition of(@NotNull final Vector3D vector) {
    return BlockPosition.of((int) vector.x(), (int) vector.y(), (int) vector.z());
  }

  /**
   * creates a zero position.
   *
   * @return zero position.
   */
  @NotNull
  public static BlockPosition zero() {
    return BlockPosition.of(0, 0, 0);
  }

  /**
   * adds the given x, y and, z to {@code this}.
   *
   * @param x the x to add.
   * @param y the y to add.
   * @param z the z to add.
   *
   * @return a newly created block position.
   */
  @NotNull
  public BlockPosition add(final int x, final int y, final int z) {
    return BlockPosition.of(this.x + x, this.y + y, this.z + z);
  }

  /**
   * adds the given position to {@code this}.
   *
   * @param position the position to add.
   *
   * @return a newly created position.
   */
  @NotNull
  public BlockPosition add(@NotNull final BlockPosition position) {
    return this.add(position.x(), position.y(), position.z());
  }

  /**
   * divides the given x, y and, z with {@code this}.
   *
   * @param x the x to divide.
   * @param y the y to divide.
   * @param z the z to divide.
   *
   * @return a newly created block position.
   */
  @NotNull
  public BlockPosition divide(final int x, final int y, final int z) {
    return BlockPosition.of(this.x / x, this.y / y, this.z / z);
  }

  /**
   * divides the given position with {@code this}.
   *
   * @param position the position to divide.
   *
   * @return a newly created position.
   */
  @NotNull
  public BlockPosition divide(@NotNull final BlockPosition position) {
    return this.add(position.x(), position.y(), position.z());
  }

  /**
   * multiplies the given x, y and, z to {@code this}.
   *
   * @param x the x to multiply.
   * @param y the y to multiply.
   * @param z the z to multiply.
   *
   * @return a newly created block position.
   */
  @NotNull
  public BlockPosition multiply(final int x, final int y, final int z) {
    return BlockPosition.of(this.x * x, this.y * y, this.z * z);
  }

  /**
   * multiplies the given position to {@code this}.
   *
   * @param position the position to multiply.
   *
   * @return a newly created position.
   */
  @NotNull
  public BlockPosition multiply(@NotNull final BlockPosition position) {
    return this.add(position.x(), position.y(), position.z());
  }

  /**
   * subtracts the given x, y and, z from {@code this}.
   *
   * @param x the x to subtract.
   * @param y the y to subtract.
   * @param z the z to subtract.
   *
   * @return a newly created block position.
   */
  @NotNull
  public BlockPosition subtract(final int x, final int y, final int z) {
    return BlockPosition.of(this.x - x, this.y - y, this.z - z);
  }

  /**
   * subtracts the given position from {@code this}.
   *
   * @param position the position to subtract.
   *
   * @return a newly created position.
   */
  @NotNull
  public BlockPosition subtract(@NotNull final BlockPosition position) {
    return this.add(position.x(), position.y(), position.z());
  }

  /**
   * sets the x.
   *
   * @param x the x to set.
   *
   * @return a newly created block position with the new x value.
   */
  @NotNull
  public BlockPosition x(final int x) {
    return BlockPosition.of(x, this.y, this.z);
  }

  /**
   * sets the y.
   *
   * @param y the y to set.
   *
   * @return a newly created block position with the new y value.
   */
  @NotNull
  public BlockPosition y(final int y) {
    return BlockPosition.of(this.x, y, this.z);
  }

  /**
   * sets the z.
   *
   * @param z the z to set.
   *
   * @return a newly created block position with the new z value.
   */
  @NotNull
  public BlockPosition z(final int z) {
    return BlockPosition.of(this.x, this.y, z);
  }
}
