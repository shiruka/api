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

import io.github.shiruka.api.base.Material;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine chunk data.
 */
public interface ChunkData {

  /**
   * Get the type and data of the block at x, y, z.
   *
   * @param x the x location in the chunk from 0-15 inclusive
   * @param y the y location in the chunk from 0 (inclusive) - maxHeight (exclusive)
   * @param z the z location in the chunk from 0-15 inclusive
   *
   * @return the data of the block or the BlockData for air if x, y or z are outside the chunk's bounds
   */
  @NotNull
  BlockData getBlockData(int x, int y, int z);

  /**
   * Get the block data at x,y,z in the chunk data.
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
   *
   * @return the maximum height
   */
  int getMaxHeight();

  /**
   * Get the type of the block at x, y, z.
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
   *
   * @param x the x location in the chunk from 0-15 inclusive
   * @param y the y location in the chunk from 0 (inclusive) - maxHeight (exclusive)
   * @param z the z location in the chunk from 0-15 inclusive
   * @param material the type to set the block to
   */
  void setBlock(int x, int y, int z, @NotNull Material material);

  /**
   * Set the block at x,y,z in the chunk data to material.
   *
   * @param x the x location in the chunk from 0-15 inclusive
   * @param y the y location in the chunk from 0 (inclusive) - maxHeight (exclusive)
   * @param z the z location in the chunk from 0-15 inclusive
   * @param material the type to set the block to
   */
  void setBlock(int x, int y, int z, @NotNull MaterialData material);

  /**
   * Set the block at x,y,z in the chunk data to material.
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
