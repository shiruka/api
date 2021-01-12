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
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine chunk data.
 */
public interface ChunkData {

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
  @NotNull
  Material getType(int x, int y, int z);

  /**
   * Set the block at x,y,z in the chunk data to material.
   *
   * @param x the x location in the chunk from 0-15 inclusive
   * @param y the y location in the chunk from 0 (inclusive) - maxHeight (exclusive)
   * @param z the z location in the chunk from 0-15 inclusive
   * @param material the type to set the block to
   */
  void setBlock(int x, int y, int z, @NotNull Material material);
}
