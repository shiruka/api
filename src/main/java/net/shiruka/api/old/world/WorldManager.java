package net.shiruka.api.old.world;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import net.shiruka.api.old.registry.Resourced;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine world managers.
 */
public interface WorldManager {

  /**
   * gets the world by name.
   *
   * @param name the name to get.
   *
   * @return world instance by name.
   */
  @NotNull
  Optional<World> getWorldByName(@NotNull String name);

  /**
   * gets the world by resource.
   *
   * @param resource the resource to get.
   *
   * @return world instance by resource.
   */
  @NotNull
  Optional<World> getWorldByResource(@NotNull Resourced resource);

  /**
   * gets the world by unique id.
   *
   * @param uniqueId the unique to get.
   *
   * @return world instance by unique id.
   */
  @NotNull
  Optional<World> getWorldByUniqueId(@NotNull UUID uniqueId);

  /**
   * obtains all the worlds.
   *
   * @return worlds.
   */
  @NotNull
  Collection<World> getWorlds();
}
