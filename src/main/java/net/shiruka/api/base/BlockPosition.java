package net.shiruka.api.base;

import java.util.Objects;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a vector is an immutable container of 3 coordinate values.
 */
@AllArgsConstructor
public final class BlockPosition {

  /**
   * the zero vector.
   */
  public static final BlockPosition ZERO = new BlockPosition();

  /**
   * the x.
   */
  private double x;

  /**
   * the y.
   */
  private double y;

  /**
   * the z.
   */
  private double z;

  /**
   * ctor.
   */
  public BlockPosition() {
    this(0.0d, 0.0d, 0.0d);
  }

  /**
   * ctor.
   *
   * @param x the x.
   * @param y the y.
   * @param z the z.
   */
  public BlockPosition(final int x, final int y, final int z) {
    this((double) x, y, z);
  }

  /**
   * hook method used to implement equals for comparing two {@code doubles}.
   *
   * @param first the first double.
   * @param second the second double.
   *
   * @return {@code true} if the two {@code doubles} are equal to each other.
   */
  private static boolean eq(final double first, final double second) {
    return Double.compare(first, second) == 0;
  }

  /**
   * squares the two {@code double}s.
   *
   * @param number the number to square.
   *
   * @return the result of squaring the double.
   */
  private static double square(final double number) {
    return number * number;
  }

  /**
   * obtains the x as {@code int}.
   *
   * @return the x.
   */
  public int getIntX() {
    return (int) this.x;
  }

  /**
   * obtains the y as {@code int}.
   *
   * @return the y.
   */
  public int getIntY() {
    return (int) this.y;
  }

  /**
   * obtains the z as {@code int}.
   *
   * @return the z.
   */
  public int getIntZ() {
    return (int) this.z;
  }

  /**
   * obtains the magnitude of this Vector.
   *
   * @return the magnitude.
   */
  public double getMagnitude() {
    return Math.sqrt(this.getMagnitudeSquared());
  }

  /**
   * obtains the square of the magnitude of this Vector.
   *
   * @return the magnitude squared.
   */
  public double getMagnitudeSquared() {
    return BlockPosition.square(this.x) + BlockPosition.square(this.y) + BlockPosition.square(this.z);
  }

  /**
   * obtains the x.
   *
   * @return the x.
   */
  public double getX() {
    return this.x;
  }

  /**
   * creates a new vector, containing all of the same fields as this vector, but with the X value set to
   * the given number.
   *
   * @param x the new X value to set in the created vector.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public BlockPosition setX(final int x) {
    return this.setX((double) x);
  }

  /**
   * creates a new vector, containing all of the same fields as this vector, but with the X value set to
   * the given number.
   *
   * @param x the new X value to set in the created vector.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public BlockPosition setX(final double x) {
    this.x = x;
    return this;
  }

  /**
   * obtains the y.
   *
   * @return the y.
   */
  public double getY() {
    return this.y;
  }

  /**
   * creates a new vector, containing all of the same fields as this vector, but with the Y value set to
   * the given number.
   *
   * @param y the new Y value to set in the created vector.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public BlockPosition setY(final int y) {
    return this.setY((double) y);
  }

  /**
   * creates a new vector, containing all of the same fields as this vector, but with the Y value set to
   * the given number.
   *
   * @param y the new Y value to set in the created vector.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public BlockPosition setY(final double y) {
    this.y = y;
    return this;
  }

  /**
   * obtains the z.
   *
   * @return the z.
   */
  public double getZ() {
    return this.z;
  }

  /**
   * Creates a new vector, containing all of the same fields as this vector, but with the Z value set to
   * the given number.
   *
   * @param z the new Z value to set in the created vector.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public BlockPosition setZ(final int z) {
    return this.setZ((double) z);
  }

  /**
   * creates a new vector, containing all of the same fields as this vector, but with the Z value set to
   * the given number.
   *
   * @param z the new Z value to set in the created vector.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public BlockPosition setZ(final double z) {
    this.z = z;
    return this;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y, this.z);
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof BlockPosition)) {
      return false;
    }
    final var vector = (BlockPosition) obj;
    return BlockPosition.eq(this.x, vector.x) &&
      BlockPosition.eq(this.y, vector.y) &&
      BlockPosition.eq(this.z, vector.z);
  }

  @Override
  public String toString() {
    return "Vector{" + this.x + ',' + this.y + ',' + this.z + '}';
  }
}
