package io.github.shiruka.api.world;

import io.github.shiruka.api.base.Location;
import io.github.shiruka.api.block.Block;
import io.github.shiruka.api.math.BlockPosition;
import io.github.shiruka.api.math.Vector3D;
import java.util.Collection;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine Minecraft worlds.
 */
public interface World {

  /**
   * gets the block at the position.
   *
   * @param vector the vector to get.
   *
   * @return block at the position.
   */
  @NotNull
  default Block block(@NotNull final Vector3D vector) {
    return this.block(vector.asPosition());
  }

  /**
   * gets the block at the position.
   *
   * @param position the position to get.
   *
   * @return block at the position.
   */
  @NotNull
  default Block block(@NotNull final BlockPosition position) {
    return this.block(position.x(), position.y(), position.z());
  }

  /**
   * gets the block at the position.
   *
   * @param x the x to get.
   * @param y the y to get.
   * @param z the z to get.
   *
   * @return block at the position.
   */
  @NotNull
  Block block(int x, int y, int z);

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
