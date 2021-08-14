package io.github.shiruka.api.world;

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
}
