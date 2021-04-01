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

import com.google.common.base.Preconditions;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import net.shiruka.api.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents locations.
 */
public final class Location implements Cloneable {

  /**
   * the pitch.
   */
  @Setter
  @Getter
  private float pitch;

  /**
   * the world.
   */
  @Nullable
  private Reference<World> world;

  /**
   * the x.
   */
  @Setter
  @Getter
  private double x;

  /**
   * the y.
   */
  @Setter
  @Getter
  private double y;

  /**
   * the yaw.
   */
  @Setter
  @Getter
  private float yaw;

  /**
   * the z.
   */
  @Setter
  @Getter
  private double z;

  /**
   * ctor.
   *
   * @param world the world.
   * @param x the x.
   * @param y the y.
   * @param z the z.
   * @param yaw the yaw.
   * @param pitch the pitch.
   */
  public Location(@Nullable final World world, final double x, final double y, final double z, final float yaw,
                  final float pitch) {
    if (world != null) {
      this.world = new WeakReference<>(world);
    }
    this.x = x;
    this.y = y;
    this.z = z;
    this.pitch = pitch;
    this.yaw = yaw;
  }

  /**
   * ctor.
   *
   * @param world the world.
   * @param x the x.
   * @param y the y.
   * @param z the z.
   */
  public Location(@Nullable final World world, final double x, final double y, final double z) {
    this(world, x, y, z, 0, 0);
  }

  /**
   * adds the location by another.
   *
   * @param location the location to add.
   *
   * @return {@code this} for builder chain.
   *
   * @throws IllegalArgumentException for differing worlds or not found world for {@code this} and {@code location}.
   */
  @NotNull
  public Location add(@NotNull final Location location) {
    this.checkTwoWorld(location);
    this.x += location.x;
    this.y += location.y;
    this.z += location.z;
    return this;
  }

  /**
   * adds the location by a vector.
   *
   * @param vector3D the vector to add.
   *
   * @return {@code this} for builder chain.
   *
   * @see Vector3D
   */
  @NotNull
  public Location add(@NotNull final Vector3D vector3D) {
    this.x += vector3D.getX();
    this.y += vector3D.getY();
    this.z += vector3D.getZ();
    return this;
  }

  /**
   * adds the location by another.
   *
   * @param x the x to add.
   * @param y the y to add.
   * @param z the z to add.
   *
   * @return {@code this} for builder chain.
   *
   * @see Vector3D
   */
  @NotNull
  public Location add(final double x, final double y, final double z) {
    this.x += x;
    this.y += y;
    this.z += z;
    return this;
  }

  /**
   * takes the x/y/z from base and adds the specified x/y/z to it and returns self.
   *
   * @param base The base coordinate to modify
   * @param x the x to add.
   * @param y the x to add.
   * @param z the x to add.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public Location add(@NotNull final Location base, final double x, final double y, final double z) {
    return this.set(base.x + x, base.y + y, base.z + z);
  }

  /**
   * gets a unit-vector pointing in the direction that this location is facing.
   *
   * @return a vector pointing the direction of this location's {@link #pitch} and {@link #yaw}.
   */
  @NotNull
  public Vector3D getDirection() {
    final var xz = Math.cos(Math.toRadians(this.pitch));
    return new Vector3D(
      -xz * Math.sin(Math.toRadians(this.yaw)),
      -Math.sin(Math.toRadians(this.pitch)),
      xz * Math.cos(Math.toRadians(this.yaw)));
  }

  /**
   * obtains the pitch.
   *
   * @return pitch.
   */
  @NotNull
  public Number getPitchAsNumber() {
    return this.pitch;
  }

  /**
   * gets the world that this location resides in.
   *
   * @return world that contains this location.
   *
   * @throws IllegalArgumentException when world is unloaded
   */
  @NotNull
  public Optional<World> getWorld() {
    if (this.world == null) {
      return Optional.empty();
    }
    final var reference = this.world.get();
    Preconditions.checkArgument(reference != null, "World unloaded!");
    return Optional.of(reference);
  }

  /**
   * sets the world that this location resides in.
   *
   * @param world the world to set.
   */
  public void setWorld(@Nullable final World world) {
    this.world = world == null ? null : new WeakReference<>(world);
  }

  /**
   * obtains the x.
   *
   * @return x.
   */
  @NotNull
  public Number getXAsNumber() {
    return this.x;
  }

  /**
   * obtains the y.
   *
   * @return y.
   */
  @NotNull
  public Number getYAsNumber() {
    return this.y;
  }

  /**
   * obtains the yaw.
   *
   * @return yaw.
   */
  @NotNull
  public Number getYawAsNumber() {
    return this.yaw;
  }

  /**
   * obtains the z.
   *
   * @return z.
   */
  @NotNull
  public Number getZAsNumber() {
    return this.z;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.pitch, this.world, this.x, this.y, this.yaw, this.z);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    final Location location = (Location) obj;
    return Float.compare(location.pitch, this.pitch) == 0 &&
      Double.compare(location.x, this.x) == 0 &&
      Double.compare(location.y, this.y) == 0 &&
      Float.compare(location.yaw, this.yaw) == 0 &&
      Double.compare(location.z, this.z) == 0 &&
      Objects.equals(this.world, location.world);
  }

  @Override
  @NotNull
  public Location clone() {
    try {
      return (Location) super.clone();
    } catch (final CloneNotSupportedException e) {
      throw new Error(e);
    }
  }

  @Override
  public String toString() {
    final var thisWorld = this.world == null ? null : this.world.get();
    return "Location{" +
      "world=" + thisWorld +
      ",x=" + this.x +
      ",y=" + this.y +
      ",z=" + this.z +
      ",pitch=" + this.pitch +
      ",yaw=" + this.yaw + '}';
  }

  /**
   * performs scalar multiplication, multiplying all components with a scalar.
   *
   * @param multiply the multiply to multiply.
   *
   * @return {@code this} for builder chain.
   *
   * @see Vector3D
   */
  @NotNull
  public Location multiply(final double multiply) {
    this.x *= multiply;
    this.y *= multiply;
    this.z *= multiply;
    return this;
  }

  /**
   * sets the position of this Location and returns itself.
   *
   * @param x the x to set.
   * @param y the x to set.
   * @param z the x to set.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public Location set(final double x, final double y, final double z) {
    this.x = x;
    this.y = y;
    this.z = z;
    return this;
  }

  /**
   * subtracts the location by another.
   *
   * @param vector the vector to subtract.
   *
   * @return {@code this} for builder chain.
   *
   * @throws IllegalArgumentException for differing worlds or not found world for {@code this} and {@code vector}.
   * @see Vector3D
   */
  @NotNull
  public Location subtract(@NotNull final Location vector) {
    this.checkTwoWorld(vector);
    this.x -= vector.x;
    this.y -= vector.y;
    this.z -= vector.z;
    return this;
  }

  /**
   * subtracts the location by a vector.
   *
   * @param vector3D the vector to add.
   *
   * @return {@code this} for builder chain.
   *
   * @see Vector3D
   */
  @NotNull
  public Location subtract(@NotNull final Vector3D vector3D) {
    this.x -= vector3D.getX();
    this.y -= vector3D.getY();
    this.z -= vector3D.getZ();
    return this;
  }

  /**
   * subtracts the location by another.
   *
   * @param x the x to subtract.
   * @param y the y to subtract.
   * @param z the z to subtract.
   *
   * @return {@code this} for builder chain.
   *
   * @see Vector3D
   */
  @NotNull
  public Location subtract(final double x, final double y, final double z) {
    this.x -= x;
    this.y -= y;
    this.z -= z;
    return this;
  }

  /**
   * takes the x/y/z from base and subtracts the specified x/y/z to it and returns self.
   *
   * @param base the base to subtract.
   * @param x the x to subtract.
   * @param y the x to subtract.
   * @param z the x to subtract.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public Location subtract(@NotNull final Location base, final double x, final double y, final double z) {
    return this.set(base.x - x, base.y - y, base.z - z);
  }

  /**
   * gives the block location.
   *
   * @return a new location where X/Y/Z are on the block location (integer value of X/Y/Z).
   */
  @NotNull
  public Location toBlockLocation() {
    final var location = this.clone();
    location.setX(this.getXAsNumber().intValue());
    location.setY(this.getYAsNumber().intValue());
    location.setZ(this.getZAsNumber().intValue());
    return location;
  }

  /**
   * gives the centered location.
   *
   * @return A new location where X/Y/Z are the center of the block
   */
  @NotNull
  public Location toCenterLocation() {
    final var location = this.clone();
    location.setX(this.getXAsNumber().intValue() + 0.5);
    location.setY(this.getYAsNumber().intValue() + 0.5);
    location.setZ(this.getZAsNumber().intValue() + 0.5);
    return location;
  }

  /**
   * zero this location's components.
   *
   * @return {@code this} for builder chain.
   *
   * @see Vector3D
   */
  @NotNull
  public Location zero() {
    this.x = 0;
    this.y = 0;
    this.z = 0;
    return this;
  }

  /**
   * checks if {@link #getWorld()} is present and equals to given {@code location}'s world.
   *
   * @param location the location to check.
   *
   * @throws IllegalArgumentException for differing worlds or not found world for {@code this} and {@code location}.
   */
  private void checkTwoWorld(@NotNull final Location location) {
    final var thisWorld = this.getWorld();
    final var otherWorld = location.getWorld();
    Preconditions.checkArgument(otherWorld.isPresent() && thisWorld.isPresent(),
      "Cannot find world!");
    Preconditions.checkArgument(otherWorld.get() == thisWorld.get(),
      "Cannot add Locations of differing worlds!");
  }
}
