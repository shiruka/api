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
 * a class that contains the set of all the possible level types that a world can generate as.
 */
public enum LevelType {
  /**
   * the default level type
   */
  DEFAULT("default"),
  /**
   * generates a world using the default flat generator
   */
  FLAT("flat"),
  /**
   * generates a world with enlarged biomes
   */
  LARGE_BIOMES("largeBiomes"),
  /**
   * generates a world with amplified hills and valleys
   */
  AMPLIFIED("amplified");

  /**
   * this is the NBT value for the level type.
   */
  private final String name;

  /**
   * ctor.
   *
   * @param name the NBT value.
   */
  LevelType(@NotNull final String name) {
    this.name = name;
  }

  /**
   * obtains the level type which uses the given string as its NBT format, ignoring case.
   *
   * @param s the level type's NBT string form.
   *
   * @return the level type, if found.
   *
   * @throws IllegalArgumentException if the level type is not found.
   */
  @NotNull
  public static LevelType from(@NotNull final String s) {
    return Stream.of(LevelType.values())
      .filter(levelType -> levelType.toString().equalsIgnoreCase(s))
      .findFirst()
      .orElseThrow(() ->
        new IllegalArgumentException(
          String.format(Misc.NBT_BOUND_FAIL, "io.github.shiruka.api.world.options.LevelType")));
  }

  @Override
  public String toString() {
    return this.name;
  }
}
