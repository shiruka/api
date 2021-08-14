package io.github.shiruka.api.base;

import io.github.shiruka.api.math.Vector3D;
import io.github.shiruka.api.world.World;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents locations.
 *
 * @param world the world.
 * @param vector the vector.
 * @param pitch the pitch.
 * @param yaw the yaw.
 */
public record Location(
  @NotNull World world,
  @NotNull Vector3D vector,
  float pitch,
  float yaw
) {

}
