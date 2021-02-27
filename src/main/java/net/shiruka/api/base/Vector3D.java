/*
 * MIT License
 *
 * Copyright (c) 2021 Shiru ka
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

package net.shiruka.api.base;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * a vector is an immutable container of 3 coordinate values.
 */
public final class Vector3D {

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
  public Vector3D() {
    this.x = 0;
    this.y = 0;
    this.z = 0;
  }

  /**
   * ctor.
   *
   * @param x the x.
   * @param y the y.
   * @param z the z.
   */
  public Vector3D(final double x, final double y, final double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * ctor.
   *
   * @param x the x.
   * @param y the y.
   * @param z the z.
   */
  public Vector3D(final int x, final int y, final int z) {
    this.x = x;
    this.y = y;
    this.z = z;
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
    return Vector3D.square(this.x) + Vector3D.square(this.y) + Vector3D.square(this.z);
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
  public Vector3D setX(final int x) {
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
  public Vector3D setX(final double x) {
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
  public Vector3D setY(final int y) {
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
  public Vector3D setY(final double y) {
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
  public Vector3D setZ(final int z) {
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
  public Vector3D setZ(final double z) {
    this.z = z;
    return this;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y, this.z);
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof Vector3D)) {
      return false;
    }
    final var vector = (Vector3D) obj;
    return Vector3D.eq(this.x, vector.x) &&
      Vector3D.eq(this.y, vector.y) &&
      Vector3D.eq(this.z, vector.z);
  }

  @Override
  public String toString() {
    return "Vector{" + this.x + ',' + this.y + ',' + this.z + '}';
  }
}
