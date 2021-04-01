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
 * a class that represents axi aligned bounding boxes.
 */
public final class AxisAlignedBoundingBox {

  /**
   * the max x.
   */
  public final double maxX;

  /**
   * the max y.
   */
  public final double maxY;

  /**
   * the max z.
   */
  public final double maxZ;

  /**
   * the min x.
   */
  public final double minX;

  /**
   * the min y.
   */
  public final double minY;

  /**
   * the min z.
   */
  public final double minZ;

  /**
   * ctor.
   *
   * @param minX the min x.
   * @param minY the min y.
   * @param minZ the min z.
   * @param maxX the max x.
   * @param maxY the max y.
   * @param maxZ the max z.
   */
  public AxisAlignedBoundingBox(final double minX, final double minY, final double minZ,
                                final double maxX, final double maxY, final double maxZ) {
    this.maxX = maxX;
    this.maxY = maxY;
    this.maxZ = maxZ;
    this.minX = minX;
    this.minY = minY;
    this.minZ = minZ;
  }
}
