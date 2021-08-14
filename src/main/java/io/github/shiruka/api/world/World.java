package io.github.shiruka.api.world;

import io.github.shiruka.api.base.Location;
import java.util.Collection;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine Minecraft worlds.
 */
public interface World {

  /**
   * obtains the difficulty.
   *
   * @return difficulty.
   */
  @NotNull
  Difficulty difficulty();

  /**
   * sets the difficulty.
   *
   * @param difficulty the difficulty to set.
   */
  void difficulty(@NotNull Difficulty difficulty);

  /**
   * gets the game rule.
   *
   * @param gameRule the game rule to get.
   * @param <T> type of the game rule.
   *
   * @return game rule's value.
   */
  @NotNull <T> T gameRule(@NotNull GameRule<T> gameRule);

  /**
   * sets the game rule's value.
   *
   * @param gameRule the game rule to set.
   * @param value the value to set.
   * @param <T> type of the value.
   */
  <T> void gameRule(@NotNull GameRule<T> gameRule, @NotNull T value);

  /**
   * obtains the all game rules.
   *
   * @return all game rules.
   */
  @NotNull
  Collection<GameRule<?>> gameRules();

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
