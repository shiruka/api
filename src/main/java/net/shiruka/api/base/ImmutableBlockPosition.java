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
 * a class that represents immutable block positions.
 */
public final class ImmutableBlockPosition extends BlockPosition {

  /**
   * the zero position.
   */
  public static final ImmutableBlockPosition ZERO = new ImmutableBlockPosition(0, 0, 0);

  /**
   * ctor.
   *
   * @param x the x.
   * @param y the y.
   * @param z the z.
   */
  public ImmutableBlockPosition(final int x, final int y, final int z) {
    super(x, y, z);
  }

  /**
   * ctor.
   *
   * @param x the x.
   * @param y the y.
   * @param z the z.
   */
  public ImmutableBlockPosition(final double x, final double y, final double z) {
    super(x, y, z);
  }
}
