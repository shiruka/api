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

import net.shiruka.api.util.MathHelper;

/**
 * an abstract class that represents block positions.
 */
public abstract class BlockPosition implements Comparable<BlockPosition> {

  /**
   * the x.
   */
  protected int x;

  /**
   * the y.
   */
  protected int y;

  /**
   * the z.
   */
  protected int z;

  /**
   * ctor.
   *
   * @param x the x.
   * @param y the y.
   * @param z the z.
   */
  protected BlockPosition(final int x, final int y, final int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * ctor.
   *
   * @param x the x.
   * @param y the y.
   * @param z the z.
   */
  protected BlockPosition(final double x, final double y, final double z) {
    this(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
  }

  /**
   * obtains the x.
   *
   * @return x.
   */
  public final int getX() {
    return this.x;
  }

  /**
   * obtains the y.
   *
   * @return y.
   */
  public final int getY() {
    return this.y;
  }

  /**
   * obtains the z.
   *
   * @return z.
   */
  public final int getZ() {
    return this.z;
  }
}
