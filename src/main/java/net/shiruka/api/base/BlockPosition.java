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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.shiruka.api.util.MathHelper;
import org.jetbrains.annotations.NotNull;

/**
 * an abstract class that represents block positions.
 */
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BlockPosition implements Comparable<BlockPosition> {

  /**
   * the x.
   */
  private final int x;

  /**
   * the y.
   */
  private final int y;

  /**
   * the z.
   */
  private final int z;

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

  @Override
  public final int compareTo(@NotNull final BlockPosition o) {
    return this.y == o.y ? this.z == o.z ? this.x - o.x : this.z - o.z : this.y - o.y;
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
