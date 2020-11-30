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
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

/**
 * this is a set of the possible difficulties that can be applied to a world in order to determine the
 * aggressiveness of mob spawns.
 */
public enum Difficulty {
  /**
   * peaceful
   */
  PEACEFUL(0),
  /**
   * easy
   */
  EASY(1),
  /**
   * normal
   */
  NORMAL(2),
  /**
   * hard
   */
  HARD(3),
  /**
   * hardcore
   */
  HARDCORE(3);

  /**
   * the NBT value of the current difficulty.
   */
  private final byte id;

  /**
   * ctor.
   *
   * @param id the NBT value.
   */
  Difficulty(final int id) {
    this.id = (byte) id;
  }

  /**
   * obtains the difficulty given the NBT value.
   *
   * @param id the NBT value of the difficulty to find.
   *
   * @return the difficulty, if found.
   *
   * @throws IndexOutOfBoundsException if it is not found.
   */
  @NotNull
  public static Difficulty from(final int id) {
    return Stream.of(Difficulty.values())
      .filter(difficulty -> difficulty.id == id)
      .findFirst()
      .orElseThrow(() ->
        new IllegalArgumentException(
          String.format(Misc.NBT_BOUND_FAIL, "io.github.shiruka.api.world.options.Difficulty")));
  }

  /**
   * obtains the NBT value of the given difficulty.
   *
   * @return the {@code byte} form of the difficulty.
   */
  public byte asByte() {
    return this.id;
  }
}
