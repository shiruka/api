package io.github.shiruka.api.world;

import io.github.shiruka.api.base.Location;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine Minecraft worlds.
 */
public interface World {

  /**
   * obtains the name.
   *
   * @return name.
   */
  @NotNull
  String getName();

  /**
   * obtains the unique id.
   *
   * @return unique id.
   */
  @NotNull
  UUID getUniqueId();

  /**
   * obtains the spawn location.
   *
   * @return spawn location.
   */
  @NotNull
  Location spawnLocation();

  /**
   * sets the spawn location.
   *
   * @param location the location to set.
   */
  void spawnLocation(@NotNull Location location);
}
