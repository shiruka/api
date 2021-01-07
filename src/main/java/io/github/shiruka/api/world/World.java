/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.shiruka.api.world;

import io.github.shiruka.api.base.HeightMap;
import io.github.shiruka.api.base.Location;
import io.github.shiruka.api.base.Named;
import io.github.shiruka.api.base.UniqueId;
import io.github.shiruka.api.block.Block;
import io.github.shiruka.api.world.options.Dimension;
import io.github.shiruka.api.world.options.GeneratorOptions;
import java.nio.file.Path;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents of a world.
 */
public interface World extends Named, UniqueId {

  /**
   * gets the {@link Block} at the given coordinates.
   *
   * @param x the x to get.
   * @param y the y to get.
   * @param z th z to get.
   *
   * @return block at the given coordinates.
   */
  @NotNull
  Block getBlockAt(int x, int y, int z);

  /**
   * gets the {@link Block} at the given {@link Location}.
   *
   * @param location the location to get.
   *
   * @return block at the given location.
   */
  @NotNull
  Block getBlockAt(@NotNull Location location);

  /**
   * obtains the chunk that is located at the given X/Z horizontal plane coordinates.
   *
   * @param location the location to get.
   *
   * @return the chunk at the given coordinates.
   */
  @NotNull
  Chunk getChunkAt(@NotNull Location location);

  /**
   * obtains the chunk that is located at the given X/Z horizontal plane coordinates.
   *
   * @param x the chunk X coordinate.
   * @param z the chunk Z coordinate.
   *
   * @return the chunk at the given coordinates.
   */
  @NotNull
  Chunk getChunkAt(int x, int z);

  /**
   * obtains a chunk at the given coordinates with an option to not generate the chunk.
   *
   * @param x the x coordinate.
   * @param z the z coordinate.
   * @param gen {@code true} to generate the chunk if it doesn't exist, {@code false} to return null.
   *
   * @return the chunk, or {@code null} if {@code gen} is {@code false} and no chunk is found.
   */
  @Nullable
  Chunk getChunkAt(int x, int z, boolean gen);

  /**
   * obtains dimension of the world.
   *
   * @return dimension of the world.
   */
  @NotNull
  Dimension getDimension();

  /**
   * obtains world's directory.
   *
   * @return world's directory.
   */
  @NotNull
  Path getDirectory();

  /**
   * obtains the options for the generator of this world.
   *
   * @return the generator options.
   */
  @NotNull
  GeneratorOptions getGeneratorOptions();

  /**
   * gets the highest block corresponding to the {@link HeightMap} at the given coordinates.
   *
   * @param x the x to get.
   * @param z the z to get.
   * @param heightMap the heightMap to get.
   *
   * @return highest block corresponding to the {@link HeightMap}.
   */
  @NotNull Block getHighestBlockAt(int x, int z, @NotNull HeightMap heightMap);

  /**
   * gets the highest block corresponding to the {@link HeightMap} at the given coordinates.
   *
   * @param location the location to get.
   * @param heightMap the heightMap to get.
   *
   * @return highest block corresponding to the {@link HeightMap}.
   */
  @NotNull Block getHighestBlockAt(@NotNull Location location, @NotNull HeightMap heightMap);

  /**
   * gets the highest coordinate corresponding to the {@link HeightMap} at the given coordinates.
   *
   * @param x the x to get.
   * @param z the z to get.
   * @param heightMap the heightMap to get.
   *
   * @return Y-coordinate of the highest block corresponding to the {@link HeightMap}.
   */
  int getHighestBlockYAt(int x, int z, @NotNull HeightMap heightMap);

  /**
   * gets the highest coordinate corresponding to the {@link HeightMap} at the given {@link Location}.
   *
   * @param location the location to get.
   * @param heightMap the heightMap to get.
   *
   * @return Y-coordinate of the highest block corresponding to the {@link HeightMap}.
   */
  int getHighestBlockYAt(@NotNull Location location, @NotNull HeightMap heightMap);

  /**
   * obtains the highest non-air block at the given two coordinates.
   *
   * @param x the x coordinate.
   * @param z the z coordinate.
   *
   * @return the highest Y.
   */
  int getHighestY(int x, int z);

  /**
   * obtains the collection of chunks that are currently loaded on this world.
   *
   * @return the collection of loaded chunks.
   */
  @NotNull
  Collection<Chunk> getLoadedChunks();

  /**
   * obtains the current world time in ticks.
   *
   * @return the time between 0 and 23999.
   */
  int getTime();

  /**
   * checks if the {@link Chunk} at the specified coordinates is generated.
   *
   * @param x the x to check.
   * @param z the z to check.
   *
   * @return {@code true} if the chunk is generated, otherwise {@code false}.
   */
  boolean isChunkGenerated(int x, int z);

  /**
   * checks if the {@link Chunk} at the specified coordinates is loaded.
   *
   * @param x the x to check.
   * @param z the z to check.
   *
   * @return {@code true} if the chunk is loaded, otherwise {@code false}.
   */
  boolean isChunkLoaded(int x, int z);

  /**
   * loads the chunks at spawn.
   */
  void loadSpawnChunks();

  /**
   * removes the chunk from memory, without doing any save or file write operations that are necessary to cleanup.
   *
   * @param x the chunk X coordinate.
   * @param z the chunk Z coordinate.
   *
   * @return the chunk that was removed, or {@code null} if no chunk was removed.
   */
  @Nullable
  Chunk removeChunkAt(int x, int z);

  /**
   * saves this world to the region files in the world directory.
   */
  void save();
}
