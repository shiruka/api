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
import net.shiruka.api.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.simpleyaml.utils.NumberConversions;

/**
 * a class that represents locations.
 */
public final class Location implements Cloneable {

  /**
   * the pitch.
   */
  private float pitch;

  /**
   * the world.
   */
  @Nullable
  private Reference<World> world;

  /**
   * the x.
   */
  private double x;

  /**
   * the y.
   */
  private double y;

  /**
   * the yaw.
   */
  private float yaw;

  /**
   * the z.
   */
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
   * safely converts a double (location coordinate) to an int (block coordinate).
   *
   * @param location the location to get.
   *
   * @return block coordinate.
   */
  private static int locToBlock(final double location) {
    return NumberConversions.floor(location);
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
   * @param vector the vector to add.
   *
   * @return {@code this} for builder chain.
   *
   * @see Vector
   */
  @NotNull
  public Location add(@NotNull final Vector vector) {
    this.x += vector.getX();
    this.y += vector.getY();
    this.z += vector.getZ();
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
   * @see Vector
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
   * calculates the distance between this location and another.
   *
   * @param other the other to calculate.
   *
   * @return the distance.
   *
   * @throws IllegalArgumentException for differing worlds or not found world for {@code this} and {@code vector}.
   * @see Vector
   */
  public double distance(@NotNull final Location other) {
    return Math.sqrt(this.distanceSquared(other));
  }

  /**
   * calculates the squared distance between this location and another.
   *
   * @param other the other to calculate.
   *
   * @return the distance.
   *
   * @throws IllegalArgumentException for differing worlds or not found world for {@code this} and {@code vector}.
   * @see Vector
   */
  public double distanceSquared(@NotNull final Location other) {
    this.checkTwoWorld(other);
    return NumberConversions.square(this.x - other.x) +
      NumberConversions.square(this.y - other.y) +
      NumberConversions.square(this.z - other.z);
  }

  /**
   * gets a unit-vector pointing in the direction that this location is facing.
   *
   * @return a vector pointing the direction of this location's {@link #getPitch()} and {@link #getYaw()}.
   */
  @NotNull
  public Vector getDirection() {
    final var rotX = this.getYaw();
    final var rotY = this.getPitch();
    final double xz = Math.cos(Math.toRadians(rotY));
    return new Vector(
      -xz * Math.sin(Math.toRadians(rotX)),
      -Math.sin(Math.toRadians(rotY)),
      xz * Math.cos(Math.toRadians(rotX)));
  }

  /**
   * sets the {@link #getYaw() yaw} and {@link #getPitch() pitch} to point in the direction of the vector.
   *
   * @param vector the vector to set.
   *
   * @return the same location.
   */
  @NotNull
  public Location setDirection(@NotNull final Vector vector) {
    final var twoTimesPI = 2 * Math.PI;
    final var vectorX = vector.getX();
    final var vectorZ = vector.getZ();
    if (vectorX == 0 && vectorZ == 0) {
      this.pitch = vector.getY() > 0 ? -90 : 90;
      return this;
    }
    final var theta = Math.atan2(-vectorX, vectorZ);
    this.yaw = (float) Math.toDegrees((theta + twoTimesPI) % twoTimesPI);
    final var x2 = NumberConversions.square(vectorX);
    final var z2 = NumberConversions.square(vectorZ);
    final var xz = Math.sqrt(x2 + z2);
    this.pitch = (float) Math.toDegrees(Math.atan(-vector.getY() / xz));
    return this;
  }

  /**
   * obtains the pitch.
   *
   * @return pitch.
   */
  public float getPitch() {
    return this.pitch;
  }

  /**
   * sets the pitch.
   *
   * @param pitch the pitch to set.
   */
  public void setPitch(final float pitch) {
    this.pitch = pitch;
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
  public double getX() {
    return this.x;
  }

  /**
   * sets the x.
   *
   * @param x the x to set.
   */
  public void setX(final double x) {
    this.x = x;
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
  public float getYaw() {
    return this.yaw;
  }

  /**
   * sets the yaw.
   *
   * @param yaw the yaw to set.
   */
  public void setYaw(final float yaw) {
    this.yaw = yaw;
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
  public double getZ() {
    return this.z;
  }

  /**
   * sets the z.
   *
   * @param z the z to set.
   */
  public void setZ(final double z) {
    this.z = z;
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
    int hash = 3;
    final var world = this.world == null ? null : this.world.get();
    hash = 19 * hash + (world != null ? world.hashCode() : 0);
    hash = 19 * hash + (int) (Double.doubleToLongBits(this.x) ^ Double.doubleToLongBits(this.x) >>> 32);
    hash = 19 * hash + (int) (Double.doubleToLongBits(this.y) ^ Double.doubleToLongBits(this.y) >>> 32);
    hash = 19 * hash + (int) (Double.doubleToLongBits(this.z) ^ Double.doubleToLongBits(this.z) >>> 32);
    hash = 19 * hash + Float.floatToIntBits(this.pitch);
    hash = 19 * hash + Float.floatToIntBits(this.yaw);
    return hash;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    final var other = (Location) obj;
    final var thisWorld = this.world == null ? null : this.world.get();
    final var otherWorld = other.world == null ? null : other.world.get();
    if (!Objects.equals(thisWorld, otherWorld) ||
      Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x) ||
      Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y) ||
      Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z) ||
      Float.floatToIntBits(this.pitch) != Float.floatToIntBits(other.pitch)) {
      return false;
    }
    return Float.floatToIntBits(this.yaw) == Float.floatToIntBits(other.yaw);
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
   * @see Vector
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
   * sets the y.
   *
   * @param y the y to set.
   */
  public void setY(final double y) {
    this.y = y;
  }

  /**
   * subtracts the location by another.
   *
   * @param vector the vector to subtract.
   *
   * @return {@code this} for builder chain.
   *
   * @throws IllegalArgumentException for differing worlds or not found world for {@code this} and {@code vector}.
   * @see Vector
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
   * @param vector the vector to add.
   *
   * @return {@code this} for builder chain.
   *
   * @see Vector
   */
  @NotNull
  public Location subtract(@NotNull final Vector vector) {
    this.x -= vector.getX();
    this.y -= vector.getY();
    this.z -= vector.getZ();
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
   * @see Vector
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
   * @see Vector
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
