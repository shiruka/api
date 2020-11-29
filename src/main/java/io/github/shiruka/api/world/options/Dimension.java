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

package io.github.shiruka.api.world.options;

import io.github.shiruka.api.misc.Misc;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

/**
 * the set of dimensions that a world can be set to.
 */
public enum Dimension {
  /**
   * represents the Nether dimension.
   */
  NETHER(-1),
  /**
   * represents the over-world (or normal) dimension.
   */
  OVER_WORLD(0),
  /**
   * represents the End dimension.
   */
  END(1);

  /**
   * the NBT value of the dimension.
   */
  private final byte id;

  /**
   * ctor.
   *
   * @param id the NBT value of the dimension.
   */
  Dimension(final int id) {
    this.id = (byte) id;
  }

  /**
   * obtains the dimension that has the given {@code int} associated as its NBT value.
   *
   * @param id NBT value of the dimension to find.
   *
   * @return the Dimension, if found.
   *
   * @throws IndexOutOfBoundsException if the Dimension is not found.
   */
  @NotNull
  public static Dimension from(final int id) {
    return Arrays.stream(Dimension.values())
      .filter(dimension -> dimension.id == id)
      .findFirst()
      .orElseThrow(() ->
        new IndexOutOfBoundsException(
          String.format(Misc.NBT_BOUND_FAIL, "io.github.shiruka.api.world.options.Dimension")));
  }

  /**
   * obtains the NBT value of the dimension represented as a {@code byte}.
   *
   * @return the byte form of the NBT value.
   */
  public byte asByte() {
    return this.id;
  }

  /**
   * obtains the NBT value of the dimension represented as an {@code int}.
   *
   * @return the NBT value of the dimension.
   */
  public int asInt() {
    return this.id;
  }
}