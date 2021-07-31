package net.shiruka.api.world;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import net.shiruka.api.base.BlockPosition;
import net.shiruka.api.base.Namespaced;
import net.shiruka.api.block.Block;
import net.shiruka.api.metadata.Metadatable;
import net.shiruka.api.registry.Registry;
import net.shiruka.api.registry.Resourced;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine worlds, which may contain entities, chunks and blocks.
 */
public interface World extends Metadatable {

  /**
   * the over world.
   */
  Resourced OVER_WORLD = Resourced.create(Registry.WORLD, Namespaced.minecraft("over_world"));

  /**
   * the end.
   */
  Resourced THE_END = Resourced.create(Registry.WORLD, Namespaced.minecraft("the_end"));

  /**
   * the nether.
   */
  Resourced THE_NETHER = Resourced.create(Registry.WORLD, Namespaced.minecraft("the_nether"));

  /**
   * generates an empty chunk.
   *
   * @param x the x to generate.
   * @param z the z to generate.
   *
   * @return a newly generated empty chunk.
   */
  @NotNull
  Chunk generateEmptyChunk(int x, int z);

  /**
   * obtains the block at the position.
   *
   * @param position the position to obtain.
   *
   * @return block at the position.
   */
  @NotNull
  Block getBlock(@NotNull BlockPosition position);

  /**
   * obtains the block at the position.
   *
   * @param x the x to obtain.
   * @param y the y to obtain.
   * @param z the z to obtain.
   *
   * @return block at the position.
   *
   * @see #getBlock(BlockPosition)
   */
  @NotNull
  default Block getBlock(final int x, final int y, final int z) {
    return this.getBlock(new BlockPosition(x, y, z));
  }

  /**
   * obtains the chunk.
   *
   * @param x the x to obtain.
   * @param z the z to obtain.
   *
   * @return chunk at the x and z.-
   */
  @NotNull
  Optional<Chunk> getChunk(int x, int z);

  /**
   * obtains the dimension key.
   *
   * @return dimension key.
   */
  @NotNull
  Resourced getDimensionKey();

  /**
   * obtains the game rule value .
   *
   * @param gameRule the game rule to obtain.
   * @param <T> type of the game rule's value.
   *
   * @return game rule value.
   */
  @NotNull <T> T getGameRule(@NotNull GameRule<T> gameRule);

  /**
   * obtains the holder.
   *
   * @return holder.
   */
  @NotNull
  WorldHolder getHolder();

  /**
   * obtains the world name.
   *
   * @return world name.
   */
  @NotNull
  String getName();

  /**
   * gets or generates a chunk.
   *
   * @param x the x to get.
   * @param z the z to get.
   *
   * @return chunk at x and z.
   */
  @NotNull
  Chunk getOrGenerateChunk(int x, int z);

  /**
   * obtains the spawn point.
   *
   * @return spawn point.
   */
  @NotNull
  BlockPosition getSpawnPoint();

  /**
   * sets the spawn point of the world.
   *
   * @param location the location to set.
   */
  void setSpawnPoint(@NotNull BlockPosition location);

  /**
   * obtains the time.
   *
   * @return time.
   */
  @NotNull
  Duration getTime();

  /**
   * sets the time.
   *
   * @param time the time to set.
   */
  void setTime(@NotNull Duration time);

  /**
   * obtains the unique id.
   *
   * @return unique id.
   */
  @NotNull
  UUID getUniqueId();

  /**
   * saves the world.
   */
  void save();

  /**
   * sets the game rule.
   *
   * @param gameRule the game rule to set.
   * @param value the value to set.
   * @param <T> type of the game rule's value.
   */
  <T> void setGameRule(@NotNull GameRule<T> gameRule, @NotNull T value);

  /**
   * unloads chunk at the given coordinates.
   *
   * @param x the x to unload.
   * @param z the z to unload.
   */
  void unloadChunk(int x, int z);
}
