/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.shiruka.api.util;

import org.jetbrains.annotations.NotNull;

/**
 * a vector is an immutable container of 3 coordinate values.
 */
public final class Vector {

  /**
   * the x.
   */
  private final double x;

  /**
   * the y.
   */
  private final double y;

  /**
   * the z.
   */
  private final double z;

  /**
   * ctor.
   *
   * @param x the x.
   * @param y the y.
   * @param z the z.
   */
  private Vector(final double x, final double y, final double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * creates a new vector, with the given new set of XYZ numbers, saving the non-vector fields.
   *
   * @param x the new X value.
   * @param y the new Y value.
   * @param z the new Z value.
   *
   * @return the new vector containing the specified XYZ coordinates, but retaining the same fields that may
   *   be possessed by a subclass.
   */
  @NotNull
  public static Vector create(final int x, final int y, final int z) {
    return Vector.create(x, y, (double) z);
  }

  /**
   * creates a new vector, with the given new set of XYZ numbers, saving the non-vector fields.
   *
   * @param x the new X value.
   * @param y the new Y value.
   * @param z the new Z value.
   *
   * @return the new vector containing the specified XYZ coordinates, but retaining the same fields that may
   *   be possessed by a subclass.
   */
  @NotNull
  public static Vector create(final double x, final double y, final double z) {
    return new Vector(x, y, z);
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
   * creates a new vector but its XYZ fields are those of this vector added with the XYZ fields of the given vector.
   *
   * @param vector the XYZ fields of which to add to this vector.
   *
   * @return the new vector with this vector's XYZ values added to the given vector's XYZ values.
   */
  @NotNull
  public Vector add(@NotNull final Vector vector) {
    return Vector.create(this.x + vector.x, this.y + vector.y, this.z + vector.z);
  }

  /**
   * creates a new vector but its XYZ fields are those of this vector added with the given values.
   *
   * @param x the amount to add to this vector's X value.
   * @param y the amount to add to this vector's Y value.
   * @param z the amount to add to this vector's Z value.
   *
   * @return the new vector with this vector's XYZ values added to those given in the parameters.
   */
  @NotNull
  public Vector add(final int x, final int y, final int z) {
    return Vector.create(this.x + x, this.y + y, this.z + z);
  }

  /**
   * creates a new vector but its XYZ fields are those of this vector added with the given values.
   *
   * @param x the amount to add to this vector's X value.
   * @param y the amount to add to this vector's Y value.
   * @param z the amount to add to this vector's Z value.
   *
   * @return the new vector with this vector's XYZ values added to those given in the parameters.
   */
  @NotNull
  public Vector add(final double x, final double y, final double z) {
    return Vector.create(this.x + x, this.y + y, this.z + z);
  }

  /**
   * creates a new vector but its XYZ fields are those of this vector divided with the XYZ fields of the given vector.
   *
   * @param vector the XYZ fields of which to divide from this vector.
   *
   * @return the new vector with this vector's XYZ values divided with the given vector's XYZ values
   */
  @NotNull
  public Vector divide(@NotNull final Vector vector) {
    return Vector.create(this.x / vector.x, this.y / vector.y, this.z / vector.z);
  }

  /**
   * creates a new vector but its XYZ fields are those of this vector divided with the given values.
   *
   * @param x the amount to divide the this vector's X value.
   * @param y the amount to divide the this vector's Y value.
   * @param z the amount to divide the this vector's Z value.
   *
   * @return the new vector with this vector's XYZ values divided with those given in the parameters.
   */
  @NotNull
  public Vector divide(final int x, final int y, final int z) {
    return Vector.create(this.x / x, this.y / y, this.z / z);
  }

  /**
   * creates a new vector but its XYZ fields are those of this vector divided with the given values.
   *
   * @param x the amount to divide the this vector's X value.
   * @param y the amount to divide the this vector's Y value.
   * @param z the amount to divide the this vector's Z value.
   *
   * @return the new vector with this vector's XYZ values divided with those given in the parameters.
   */
  @NotNull
  public Vector divide(final double x, final double y, final double z) {
    return Vector.create(this.x / x, this.y / y, this.z / z);
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
    return Vector.square(this.x) + Vector.square(this.y) + Vector.square(this.z);
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
   * @return the vector with the new X value and all of the rest of its fields unmodified.
   */
  @NotNull
  public Vector setX(final int x) {
    return this.setX((double) x);
  }

  /**
   * creates a new vector, containing all of the same fields as this vector, but with the X value set to
   * the given number.
   *
   * @param x the new X value to set in the created vector.
   *
   * @return the vector with the new X value and all of the rest of its fields unmodified.
   */
  @NotNull
  public Vector setX(final double x) {
    return new Vector(x, this.y, this.z);
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
   * @return the vector with the new Y value and all of the rest of its fields unmodified.
   */
  @NotNull
  public Vector setY(final int y) {
    return this.setY((double) y);
  }

  /**
   * creates a new vector, containing all of the same fields as this vector, but with the Y value set to
   * the given number.
   *
   * @param y the new Y value to set in the created vector.
   *
   * @return the vector with the new Y value and all of the rest of its fields unmodified.
   */
  @NotNull
  public Vector setY(final double y) {
    return new Vector(this.x, y, this.z);
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
   * @return the vector with the new Z value and all of the rest of its fields unmodified.
   */
  @NotNull
  public Vector setZ(final int z) {
    return this.setZ((double) z);
  }

  /**
   * creates a new vector, containing all of the same fields as this vector, but with the Z value set to
   * the given number.
   *
   * @param z the new Z value to set in the created vector.
   *
   * @return the vector with the new Z value and all of the rest of its fields unmodified.
   */
  @NotNull
  public Vector setZ(final double z) {
    return new Vector(this.x, this.y, z);
  }

  @Override
  public int hashCode() {
    var hash = 1;
    hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(this.x));
    hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(this.y));
    hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(this.z));
    return hash;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof Vector) {
      final var vector = (Vector) obj;
      return Vector.eq(this.x, vector.x) && Vector.eq(this.y, vector.y) && Vector.eq(this.z, vector.z);
    }
    return false;
  }

  @Override
  public String toString() {
    return "Vector{" + this.x + ',' + this.y + ',' + this.z + '}';
  }

  /**
   * creates a new vector but its XYZ fields are those of this vector multiplied with the XYZ fields of the
   * given vector.
   *
   * @param vector the XYZ fields of which to multiply with this vector.
   *
   * @return the new vector with this vector's XYZ values multiplied to the given vector's XYZ values.
   */
  @NotNull
  public Vector multiply(@NotNull final Vector vector) {
    return Vector.create(this.x * vector.x, this.y * vector.y, this.z * vector.z);
  }

  /**
   * creates a new vector but its XYZ fields are those of this vector multiplied with the given values.
   *
   * @param x the amount to multiply with this vector's X value.
   * @param y the amount to multiply with this vector's Y value.
   * @param z the amount to multiply with this vector's Z value.
   *
   * @return the new vector with this vector's XYZ values multiplied with those given in the parameters.
   */
  @NotNull
  public Vector multiply(final int x, final int y, final int z) {
    return Vector.create(this.x * x, this.y * y, this.z * z);
  }

  /**
   * creates a new vector but its XYZ fields are those of this vector multiplied with the given values.
   *
   * @param x the amount to multiply with this vector's X value.
   * @param y the amount to multiply with this vector's Y value.
   * @param z the amount to multiply with this vector's Z value.
   *
   * @return the new vector with this vector's XYZ values multiplied with those given in the parameters.
   */
  @NotNull
  public Vector multiply(final double x, final double y, final double z) {
    return Vector.create(this.x * x, this.y * y, this.z * z);
  }

  /**
   * obtains this vector converted to a unit vector, with a magnitude of {@code 1} but retaining the same direction.
   *
   * @return the normalized vector.
   */
  @NotNull
  public Vector normalize() {
    final var mag = this.getMagnitude();
    return this.divide(mag, mag, mag);
  }

  /**
   * creates a new vector but its XYZ fields are those of this vector subtracted with the XYZ fields of the
   * given vector.
   *
   * @param vector the XYZ fields of which to subtract from this vector
   *
   * @return the new vector with this vector's XYZ values subtracted with the given vector's XYZ values.
   */
  @NotNull
  public Vector subtract(@NotNull final Vector vector) {
    return Vector.create(this.x - vector.x, this.y - vector.y, this.z - vector.z);
  }

  /**
   * creates a new vector but its XYZ fields are those of this vector subtracted with the given values.
   *
   * @param x the amount to subtract from this vector's X value.
   * @param y the amount to subtract from this vector's Y value.
   * @param z the amount to subtract from this vector's Z value.
   *
   * @return the new vector with this vector's XYZ values subtracted with those given in the parameters.
   */
  @NotNull
  public Vector subtract(final int x, final int y, final int z) {
    return Vector.create(this.x - x, this.y - y, this.z - z);
  }

  /**
   * creates a new vector but its XYZ fields are those of this vector subtracted with the given values.
   *
   * @param x the amount to subtract from this vector's X value.
   * @param y the amount to subtract from this vector's Y value.
   * @param z the amount to subtract from this vector's Z value.
   *
   * @return the new vector with this vector's XYZ values subtracted with those given in the parameters.
   */
  @NotNull
  public Vector subtract(final double x, final double y, final double z) {
    return Vector.create(this.x - x, this.y - y, this.z - z);
  }
}
