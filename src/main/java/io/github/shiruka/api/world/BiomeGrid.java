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

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine biome section for chunk to be generated.
 */
public interface BiomeGrid {

  /**
   * gets biome at x, z within chunk being generated.
   *
   * @param x the x to get.
   * @param y the y to get.
   * @param z the z to get.
   *
   * @return biome value.
   */
  @NotNull
  Biome getBiome(int x, int y, int z);

  /**
   * sets biome at x, z within chunk being generated.
   *
   * @param x the x to set.
   * @param y the y to set.
   * @param z the z to set.
   * @param biome the biome to set
   */
  void setBiome(int x, int y, int z, @NotNull Biome biome);
}
