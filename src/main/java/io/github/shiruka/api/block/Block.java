package io.github.shiruka.api.block;

import io.github.shiruka.api.base.Location;
import io.github.shiruka.api.base.Vector3i;
import io.github.shiruka.api.world.World;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine blocks.
 */
public interface Block {
  /**
   * the obtains the location.
   *
   * @return location.
   */
  @NotNull
  Location location();

  /**
   * the obtains the position.
   *
   * @return position.
   */
  @NotNull
  Vector3i position();

  /**
   * the obtains the world.
   *
   * @return world.
   */
  @NotNull
  World world();
}
