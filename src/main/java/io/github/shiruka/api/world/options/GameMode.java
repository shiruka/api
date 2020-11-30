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
 * the set of game types that may be applied to a world in order to enforce upon players.
 */
public enum GameMode {
  /**
   * survival mode.
   */
  SURVIVAL(0),
  /**
   * creative mode.
   */
  CREATIVE(1),
  /**
   * adventure mode.
   */
  ADVENTURE(2),
  /**
   * spectator mode.
   */
  SPECTATOR(3);

  /**
   * the NBT value associated with the constructed game mode.
   */
  private final byte id;

  /**
   * creates a new game mode with the NBT value represented as an {@code int} for conciseness.
   *
   * @param id the NBT value for the game mode.
   */
  GameMode(final int id) {
    this.id = (byte) id;
  }

  /**
   * obtains the game mode from the NBT value specified.
   *
   * @param id the NBT value.
   *
   * @return the game mode, if found.
   *
   * @throws IndexOutOfBoundsException if there is no game mode for the given NBT value
   */
  @NotNull
  public static GameMode from(final int id) {
    return Stream.of(GameMode.values())
      .filter(gameMode -> gameMode.id == id)
      .findFirst()
      .orElseThrow(() ->
        new IndexOutOfBoundsException(
          String.format(Misc.NBT_BOUND_FAIL, "io.github.shiruka.api.world.options.GameMode")));
  }

  /**
   * obtains the NBT value of the given game mode as it is held in this class.
   *
   * @return the NBT value as {@code byte}.
   */
  public byte asByte() {
    return this.id;
  }

  /**
   * obtains the NBT value of the given game mode converted to the actual NBT type.
   *
   * @return the NBT value as {@code int}.
   */
  public int asInt() {
    return this.id;
  }
}
