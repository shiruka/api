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

package net.shiruka.api.base;

/**
 * an enum class that represents height maps.
 */
public enum HeightMap {
  /**
   * the highest block that blocks motion or contains a fluid.
   */
  MOTION_BLOCKING,
  /**
   * the highest block that blocks motion or contains a fluid or is in the
   */
  MOTION_BLOCKING_NO_LEAVES,
  /**
   * the highest non-air block, solid block.
   */
  OCEAN_FLOOR,
  /**
   * the highest block that is neither air nor contains a fluid, for world generation.
   */
  OCEAN_FLOOR_WG,
  /**
   * the highest non-air block.
   */
  WORLD_SURFACE,
  /**
   * the highest non-air block, for world generation.
   */
  WORLD_SURFACE_WG,
}
