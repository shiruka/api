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

package io.github.shiruka.api.world.generators;

import io.github.shiruka.api.base.Material;
import org.jetbrains.annotations.NotNull;

/**
 * this class represents the generation context in which a world generator creates chunks and features.
 */
public interface GeneratorContext {

  /**
   * obtains the height of the max Y value at the given x and z coordinates.
   *
   * @param x the x coordinate.
   * @param z the z coordinate.
   *
   * @return the max generated Y value.
   */
  int maxHeight(int x, int z);

  /**
   * obtains the next random integer that this context provides.
   *
   * @return the next random integer.
   */
  int nextInt();

  /**
   * obtains the next random integer value that this context provides within the given bound.
   *
   * @param max the largest number, non-inclusive.
   *
   * @return the next integer within the given bound.
   */
  int nextInt(int max);

  /**
   * obtains the next random long value that this context provides.
   *
   * @return the next random long.
   */
  long nextLong();

  /**
   * obtains the next random long value that this context provides within the given bound.
   *
   * @param max the largest number, non-inclusive.
   *
   * @return the next long within the given bound.
   */
  long nextLong(long max);

  /**
   * schedules the given generation task to be run when the command is given for the generator to proceed.
   *
   * @param r the task to run.
   */
  void run(@NotNull Runnable r);

  /**
   * obtains the seed that is used to generate the chunk which uses this context.
   *
   * @return the seed used to generate the chunk.
   */
  long seed();

  /**
   * sets the block at the coordinates relative to the chunk (0-15) to the given block substance and metadata.
   *
   * @param x the x value.
   * @param y the y value.
   * @param z the z value.
   * @param material the material of the block.
   * @param meta the block meta.
   */
  void set(int x, int y, int z, @NotNull Material material, byte meta);

  /**
   * sets the block at the coordinates relative to the chunk (0-15) to the given block substance and no metadata.
   *
   * @param x the x value.
   * @param y the y value.
   * @param z the z value.
   * @param material the material of the block.
   */
  void set(int x, int y, int z, @NotNull Material material);

  /**
   * sets the block at the coordinates relative to the chunk (0-15) to the given block ID and metadata.
   *
   * @param x the x value.
   * @param y the y value.
   * @param z the z value.
   * @param id the block ID.
   * @param meta the block meta.
   */
  void set(int x, int y, int z, int id, byte meta);
}
