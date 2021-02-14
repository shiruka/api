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

package net.shiruka.api.world;

import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an enum class that represents various types of worlds that may exist.
 */
public enum WorldType {
  /**
   * the normal.
   */
  NORMAL,
  /**
   * the flat.
   */
  FLAT,
  /**
   * the large biomes.
   */
  LARGE_BIOMES,
  /**
   * the amplified.
   */
  AMPLIFIED;

  /**
   * the cache by name.
   */
  private static final Map<String, WorldType> BY_NAME = Maps.newHashMap();

  /**
   * the name.
   */
  @NotNull
  private final String name;

  static {
    Arrays.stream(WorldType.values())
      .forEach(type -> WorldType.BY_NAME.put(type.name, type));
  }

  /**
   * ctor.
   */
  WorldType() {
    this.name = this.name();
  }

  /**
   * gets a WorldType by its name.
   *
   * @param name the name to get.
   *
   * @return requested world type.
   */
  @NotNull
  public static WorldType getByName(@NotNull final String name) {
    return Optional.ofNullable(WorldType.BY_NAME.get(name.toUpperCase(Locale.ROOT)))
      .orElseThrow();
  }

  /**
   * obtains the name.
   *
   * @return name.
   */
  @NotNull
  public String getName() {
    return this.name;
  }
}
