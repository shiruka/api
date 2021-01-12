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

package io.github.shiruka.api.world;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an enum class that represents various map environment types that a world may be.
 */
public enum Environment {
  /**
   * "normal"/"surface world" map.
   */
  NORMAL(0),
  /**
   * a nether based map ("hell").
   */
  NETHER(-1),
  /**
   * the "end" map.
   */
  THE_END(1);

  /**
   * the cache.
   */
  private static final Map<Byte, Environment> CACHE = new HashMap<>();

  /**
   * the id.
   */
  private final byte id;

  static {
    Arrays.stream(Environment.values())
      .forEach(env -> Environment.CACHE.put(env.getId(), env));
  }

  /**
   * ctor.
   *
   * @param id the id.
   */
  Environment(final Number id) {
    this.id = id.byteValue();
  }

  /**
   * obtains an environment by id.
   *
   * @param id the id to get.
   *
   * @return the environment.
   */
  @NotNull
  public static Environment getEnvironment(final byte id) {
    return Optional.ofNullable(Environment.CACHE.get(id))
      .orElseThrow();
  }

  /**
   * obtains the id.
   *
   * @return id.
   */
  public byte getId() {
    return this.id;
  }
}
