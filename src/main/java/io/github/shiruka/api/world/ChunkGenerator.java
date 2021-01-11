/*
 * MIT License
 *
 * Copyright (c) 2021 Shiru ka
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

import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.base.Location;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

/**
 * an abstract class that represents chunk generators.
 */
public abstract class ChunkGenerator {

  /**
   * Create a ChunkData for a world.
   *
   * @param world the world the ChunkData is for
   *
   * @return a new ChunkData for world
   */
  @NotNull
  protected static ChunkData createChunkData(@NotNull final World world) {
    return Shiruka.getWorldManager().createChunkData(world);
  }

  /**
   * creates a chunk data for use in a generator, that is populated by the vanilla generator for that world.
   *
   * @param world the world to create.
   * @param x the x to create.
   * @param z the z to create.
   *
   * @return a new chunk data for the world.
   */
  @NotNull
  public ChunkData createNativeChunkData(@NotNull final World world, final int x, final int z) {
    return Shiruka.getWorldManager().createNativeChunkData(world, x, z);
  }

  /**
   * Shapes the chunk for the given coordinates.
   *
   * @param world The world this chunk will be used for
   * @param random The random generator to use
   * @param x The X-coordinate of the chunk
   * @param z The Z-coordinate of the chunk
   * @param biome Proposed biome values for chunk - can be updated by generator
   *
   * @return ChunkData containing the types for each block created by this generator
   */
  @NotNull
  public ChunkData generateChunkData(@NotNull final World world, @NotNull final Random random, final int x, final int z, @NotNull final BiomeGrid biome) {
    throw new UnsupportedOperationException("Custom generator " + this.getClass().getName() + " is missing required method generateChunkData");
  }

  /**
   * Gets a list of default {@link BlockPopulator}s to apply to a given world
   *
   * @param world World to apply to
   *
   * @return List containing any amount of BlockPopulators
   */
  @NotNull
  public List<BlockPopulator> getDefaultPopulators(@NotNull final World world) {
    return new ArrayList<>();
  }

  /**
   * Gets a fixed spawn location to use for a given world.
   *
   * @param world The world to locate a spawn point for
   * @param random Random generator to use in the calculation
   *
   * @return Location containing a new spawn point, otherwise null
   */
  @NotNull
  public Optional<Location> getFixedSpawnLocation(@NotNull final World world, @NotNull final Random random) {
    return Optional.empty();
  }

  /**
   * Gets if this ChunkGenerator is parallel capable.
   *
   * @return parallel capable status
   */
  public boolean isParallelCapable() {
    return false;
  }

  /**
   * Gets if the server should generate Vanilla caves after this ChunkGenerator.
   *
   * @return true if the server should generate Vanilla caves
   */
  public boolean shouldGenerateCaves() {
    return false;
  }

  /**
   * Gets if the server should generate Vanilla decorations after this ChunkGenerator.
   *
   * @return true if the server should generate Vanilla decorations
   */
  public boolean shouldGenerateDecorations() {
    return false;
  }

  /**
   * Gets if the server should generate Vanilla mobs after this ChunkGenerator.
   *
   * @return true if the server should generate Vanilla mobs
   */
  public boolean shouldGenerateMobs() {
    return false;
  }

  /**
   * Gets if the server should generate Vanilla structures after this ChunkGenerator.
   *
   * @return true if the server should generate Vanilla structures
   */
  public boolean shouldGenerateStructures() {
    return false;
  }
}
