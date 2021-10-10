package io.github.shiruka.api.world;

import io.github.shiruka.api.base.Location;
import io.github.shiruka.api.block.Block;
import io.github.shiruka.api.math.BlockPosition;
import io.github.shiruka.api.math.vectors.Vector3d;
import java.time.Duration;
import java.util.Collection;
import java.util.Optional;
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
  default Block block(@NotNull final Vector3d vector) {
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
   * gets chunk at the position.
   *
   * @param x the x to get.
   * @param z the z to get.
   *
   * @return chunk at the position.
   */
  @NotNull
  Optional<Chunk> chunkAt(int x, int z);

  /**
   * gets chunk at the position or generates it.
   *
   * @param x the x to get.
   * @param z the z to get.
   *
   * @return chunk at the position.
   */
  @NotNull
  Chunk chunkAtOrGenerate(int x, int z);

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
   * generates an empty chunk at the position.
   *
   * @param x the x to generate.
   * @param z the z to generate.
   *
   * @return an empty chunk at the position.
   */
  @NotNull
  Chunk generateEmptyChunk(int x, int z);

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
   * gets the highest block at the position.
   *
   * @param x the x to get.
   * @param z the z to get.
   *
   * @return highest block at the position
   */
  @NotNull
  Block highestBlockAt(int x, int z);

  /**
   * saves the world.
   */
  void save();

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

  /**
   * obtains the time.
   *
   * @return time.
   */
  @NotNull
  Duration time();

  /**
   * sets the time.
   *
   * @param duration the duration to set.
   */
  void time(@NotNull Duration duration);

  /**
   * unloads the chunk at the position.
   *
   * @param x the x to unload.
   * @param z the z to unload.
   */
  void unloadChunk(int x, int z);
}
