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

import io.github.shiruka.api.base.Material;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

/**
 * a chunk generator is responsible for the initial shaping of an entire chunk.
 */
public abstract class ChunkGenerator {

  /**
   * tests if the specified location is valid for a natural spawn position.
   *
   * @param world the world to test.
   * @param x the x to test.
   * @param z the z to test.
   *
   * @return {@code true} if the location is valid, otherwise {@code false}.
   */
  public boolean canSpawn(@NotNull final World world, final int x, final int z) {
    final var highest = world.getBlockAt(x, world.getHighestBlockYAt(x, z), z);
    switch (world.getEnvironment()) {
      case NETHER:
        return true;
      case THE_END:
        return highest.getType() != Material.AIR && highest.getType() != Material.WATER && highest.getType() != Material.LAVA;
      case NORMAL:
      default:
        return highest.getType() == Material.SAND || highest.getType() == Material.GRAVEL;
    }
  }

  /**
   * Create a ChunkData for use in a generator, that is populated by the vanilla generator for that world
   *
   * @param world the world to create the ChunkData for
   * @param x the x coordinate of the chunk
   * @param z the z coordinate of the chunk
   *
   * @return a new ChunkData for the world
   */
  @NotNull
  public ChunkData createVanillaChunkData(@NotNull final World world, final int x, final int z) {
    return Bukkit.getServer().createVanillaChunkData(world, x, z);
  }

  /**
   * Shapes the chunk for the given coordinates.
   * <p>
   * This method must return a ChunkData.
   * <p>
   * Notes:
   * <p>
   * This method should <b>never</b> attempt to get the Chunk at
   * the passed coordinates, as doing so may cause an infinite loop
   * <p>
   * This method should <b>never</b> modify a ChunkData after it has
   * been returned.
   * <p>
   * This method <b>must</b> return a ChunkData returned by {@link ChunkGenerator#createChunkData(org.bukkit.World)}
   *
   * @param world The world this chunk will be used for
   * @param random The random generator to use
   * @param x The X-coordinate of the chunk
   * @param z The Z-coordinate of the chunk
   * @param biome Proposed biome values for chunk - can be updated by
   *   generator
   *
   * @return ChunkData containing the types for each block created by this
   *   generator
   */
  @NotNull
  public ChunkData generateChunkData(@NotNull final World world, @NotNull final Random random, final int x, final int z, @NotNull final BiomeGrid biome) {
    throw new UnsupportedOperationException("Custom generator " + this.getClass().getName() + " is missing required method generateChunkData");
  }

  /**
   * Gets a list of default {@link BlockPopulator}s to apply to a given
   * world
   *
   * @param world World to apply to
   *
   * @return List containing any amount of BlockPopulators
   */
  @NotNull
  public List<BlockPopulator> getDefaultPopulators(@NotNull final World world) {
    return new ArrayList<BlockPopulator>();
  }

  /**
   * Gets a fixed spawn location to use for a given world.
   * <p>
   * A null value is returned if a world should not use a fixed spawn point,
   * and will instead attempt to find one randomly.
   *
   * @param world The world to locate a spawn point for
   * @param random Random generator to use in the calculation
   *
   * @return Location containing a new spawn point, otherwise null
   */
  @Nullable
  public Location getFixedSpawnLocation(@NotNull final World world, @NotNull final Random random) {
    return null;
  }

  /**
   * Gets if this ChunkGenerator is parallel capable.
   * <p>
   * See {@link ChunkGenerator} for more information.
   *
   * @return parallel capable status
   */
  public boolean isParallelCapable() {
    return false;
  }

  /**
   * Gets if the server should generate Vanilla caves after this
   * ChunkGenerator.
   *
   * @return true if the server should generate Vanilla caves
   */
  public boolean shouldGenerateCaves() {
    return false;
  }

  /**
   * Gets if the server should generate Vanilla decorations after this
   * ChunkGenerator.
   *
   * @return true if the server should generate Vanilla decorations
   */
  public boolean shouldGenerateDecorations() {
    return false;
  }

  /**
   * Gets if the server should generate Vanilla mobs after this
   * ChunkGenerator.
   *
   * @return true if the server should generate Vanilla mobs
   */
  public boolean shouldGenerateMobs() {
    return false;
  }

  /**
   * Gets if the server should generate Vanilla structures after this
   * ChunkGenerator.
   *
   * @return true if the server should generate Vanilla structures
   */
  public boolean shouldGenerateStructures() {
    return false;
  }
  // Paper start

  /**
   * Create a ChunkData for a world.
   *
   * @param world the world the ChunkData is for
   *
   * @return a new ChunkData for world
   */
  @NotNull
  protected final ChunkData createChunkData(@NotNull final World world) {
    return Bukkit.getServer().createChunkData(world);
  }
  // Paper end

  /**
   * Data for a Chunk.
   */
  public interface ChunkData {

    /**
     * Get the type and data of the block at x, y, z.
     * <p>
     * Getting blocks outside the chunk's bounds returns air.
     *
     * @param x the x location in the chunk from 0-15 inclusive
     * @param y the y location in the chunk from 0 (inclusive) - maxHeight (exclusive)
     * @param z the z location in the chunk from 0-15 inclusive
     *
     * @return the data of the block or the BlockData for air if x, y or z are outside the chunk's bounds
     */
    @NotNull BlockData getBlockData(int x, int y, int z);

    /**
     * Get the block data at x,y,z in the chunk data.
     * <p>
     * Getting blocks outside the chunk's bounds returns 0.
     *
     * @param x the x location in the chunk from 0-15 inclusive
     * @param y the y location in the chunk from 0 (inclusive) - maxHeight (exclusive)
     * @param z the z location in the chunk from 0-15 inclusive
     *
     * @return the block data value or air if x, y or z are outside the chunk's bounds
     *
     * @deprecated Uses magic values
     */
    @Deprecated
    byte getData(int x, int y, int z);

    /**
     * Get the maximum height for the chunk.
     * <p>
     * Setting blocks at or above this height will do nothing.
     *
     * @return the maximum height
     */
    int getMaxHeight();

    /**
     * Get the type of the block at x, y, z.
     * <p>
     * Getting blocks outside the chunk's bounds returns air.
     *
     * @param x the x location in the chunk from 0-15 inclusive
     * @param y the y location in the chunk from 0 (inclusive) - maxHeight (exclusive)
     * @param z the z location in the chunk from 0-15 inclusive
     *
     * @return the type of the block or Material.AIR if x, y or z are outside the chunk's bounds
     */
    @NotNull Material getType(int x, int y, int z);

    /**
     * Get the type and data of the block at x, y, z.
     * <p>
     * Getting blocks outside the chunk's bounds returns air.
     *
     * @param x the x location in the chunk from 0-15 inclusive
     * @param y the y location in the chunk from 0 (inclusive) - maxHeight (exclusive)
     * @param z the z location in the chunk from 0-15 inclusive
     *
     * @return the type and data of the block or the MaterialData for air if x, y or z are outside the chunk's bounds
     */
    @NotNull MaterialData getTypeAndData(int x, int y, int z);

    /**
     * Set the block at x,y,z in the chunk data to material.
     * <p>
     * Note: setting blocks outside the chunk's bounds does nothing.
     *
     * @param x the x location in the chunk from 0-15 inclusive
     * @param y the y location in the chunk from 0 (inclusive) - maxHeight (exclusive)
     * @param z the z location in the chunk from 0-15 inclusive
     * @param material the type to set the block to
     */
    void setBlock(int x, int y, int z, @NotNull Material material);

    /**
     * Set the block at x,y,z in the chunk data to material.
     * <p>
     * Setting blocks outside the chunk's bounds does nothing.
     *
     * @param x the x location in the chunk from 0-15 inclusive
     * @param y the y location in the chunk from 0 (inclusive) - maxHeight (exclusive)
     * @param z the z location in the chunk from 0-15 inclusive
     * @param material the type to set the block to
     */
    void setBlock(int x, int y, int z, @NotNull MaterialData material);

    /**
     * Set the block at x,y,z in the chunk data to material.
     * <p>
     * Setting blocks outside the chunk's bounds does nothing.
     *
     * @param x the x location in the chunk from 0-15 inclusive
     * @param y the y location in the chunk from 0 (inclusive) - maxHeight (exclusive)
     * @param z the z location in the chunk from 0-15 inclusive
     * @param blockData the type to set the block to
     */
    void setBlock(int x, int y, int z, @NotNull BlockData blockData);

    /**
     * Set a region of this chunk from xMin, yMin, zMin (inclusive)
     * to xMax, yMax, zMax (exclusive) to material.
     * <p>
     * Setting blocks outside the chunk's bounds does nothing.
     *
     * @param xMin minimum x location (inclusive) in the chunk to set
     * @param yMin minimum y location (inclusive) in the chunk to set
     * @param zMin minimum z location (inclusive) in the chunk to set
     * @param xMax maximum x location (exclusive) in the chunk to set
     * @param yMax maximum y location (exclusive) in the chunk to set
     * @param zMax maximum z location (exclusive) in the chunk to set
     * @param material the type to set the blocks to
     */
    void setRegion(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, @NotNull Material material);

    /**
     * Set a region of this chunk from xMin, yMin, zMin (inclusive)
     * to xMax, yMax, zMax (exclusive) to material.
     * <p>
     * Setting blocks outside the chunk's bounds does nothing.
     *
     * @param xMin minimum x location (inclusive) in the chunk to set
     * @param yMin minimum y location (inclusive) in the chunk to set
     * @param zMin minimum z location (inclusive) in the chunk to set
     * @param xMax maximum x location (exclusive) in the chunk to set
     * @param yMax maximum y location (exclusive) in the chunk to set
     * @param zMax maximum z location (exclusive) in the chunk to set
     * @param material the type to set the blocks to
     */
    void setRegion(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, @NotNull MaterialData material);

    /**
     * Set a region of this chunk from xMin, yMin, zMin (inclusive) to xMax,
     * yMax, zMax (exclusive) to material.
     * <p>
     * Setting blocks outside the chunk's bounds does nothing.
     *
     * @param xMin minimum x location (inclusive) in the chunk to set
     * @param yMin minimum y location (inclusive) in the chunk to set
     * @param zMin minimum z location (inclusive) in the chunk to set
     * @param xMax maximum x location (exclusive) in the chunk to set
     * @param yMax maximum y location (exclusive) in the chunk to set
     * @param zMax maximum z location (exclusive) in the chunk to set
     * @param blockData the type to set the blocks to
     */
    void setRegion(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, @NotNull BlockData blockData);
  }
}
