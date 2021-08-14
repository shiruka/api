package io.github.shiruka.api.base;

import io.github.shiruka.api.math.Vector3D;
import io.github.shiruka.api.world.World;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents locations.
 *
 * @param world the world.
 * @param position the position.
 * @param yaw the yaw.
 * @param pitch the pitch.
 */
public record Location(
  @NotNull World world,
  @NotNull Vector3D position,
  float yaw,
  float pitch
) {

  /**
   * creates a location.
   *
   * @param world the world to create.
   * @param position the position to create.
   * @param yaw the yaw to create.
   * @param pitch the pitch to create.
   *
   * @return a newly created location.
   */
  @NotNull
  public static Location of(@NotNull final World world, @NotNull final Vector3D position, final float yaw,
                            final float pitch) {
    return new Location(world, position, yaw, pitch);
  }

  /**
   * sets the pitch.
   *
   * @param pitch the pitch to set.
   *
   * @return a newly created location with the new pitch value.
   */
  @NotNull
  public Location pitch(final float pitch) {
    return Location.of(this.world, this.position, this.yaw, pitch);
  }

  /**
   * sets the position.
   *
   * @param position the position to set.
   *
   * @return a newly created location with the new position value.
   */
  @NotNull
  public Location position(@NotNull final Vector3D position) {
    return Location.of(this.world, position, this.yaw, this.pitch);
  }

  /**
   * sets the x, y and, z.
   *
   * @param x the x to set.
   * @param y the y to set.
   * @param z the z to set.
   *
   * @return a newly created location with the new x, y and, z values.
   */
  @NotNull
  public Location position(final double x, final double y, final double z) {
    return this.position(Vector3D.of(x, y, z));
  }

  /**
   * sets the world.
   *
   * @param world the world to set.
   *
   * @return a newly created location with the new world value.
   */
  @NotNull
  public Location world(@NotNull final World world) {
    return Location.of(world, this.position, this.yaw, this.pitch);
  }

  /**
   * sets the x.
   *
   * @param x the x to set.
   *
   * @return a newly created location with the new x value.
   */
  @NotNull
  public Location x(final double x) {
    return this.position(this.position.x(x));
  }

  /**
   * sets the y.
   *
   * @param y the y to set.
   *
   * @return a newly created location with the new y value.
   */
  @NotNull
  public Location y(final double y) {
    return this.position(this.position.y(y));
  }

  /**
   * sets the yaw.
   *
   * @param yaw the yaw to set.
   *
   * @return a newly created location with the new yaw value.
   */
  @NotNull
  public Location yaw(final float yaw) {
    return Location.of(this.world, this.position, yaw, this.pitch);
  }

  /**
   * sets the z.
   *
   * @param z the z to set.
   *
   * @return a newly created location with the new z value.
   */
  @NotNull
  public Location z(final double z) {
    return this.position(this.position.z(z));
  }
}
