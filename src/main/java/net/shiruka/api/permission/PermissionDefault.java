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

package net.shiruka.api.permission;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an enum class that represents the possible default values for permissions.
 */
public enum PermissionDefault {
  /**
   * the true.
   */
  TRUE("true"),
  /**
   * the false.
   */
  FALSE("false"),
  /**
   * the op.
   */
  OP("op", "isop", "operator", "isoperator", "admin", "isadmin"),
  /**
   * the not op.
   */
  NOT_OP("!op", "notop", "!operator", "notoperator", "!admin", "notadmin");

  /**
   * the cache.
   */
  private static final Map<String, PermissionDefault> CACHE = new Object2ObjectOpenHashMap<>();

  /**
   * the names.
   */
  @NotNull
  private final String[] names;

  static {
    Arrays.stream(PermissionDefault.values())
      .forEach(value -> Arrays.stream(value.names)
        .forEach(name -> PermissionDefault.CACHE.put(name, value)));
  }

  /**
   * ctor.
   *
   * @param names the names.
   */
  PermissionDefault(@NotNull final String... names) {
    this.names = names;
  }

  /**
   * obtains the permission default instance from the name.
   *
   * @param name the name to get.
   *
   * @return specified value.
   */
  @NotNull
  public static Optional<PermissionDefault> getByName(@NotNull final String name) {
    return Optional.ofNullable(PermissionDefault.CACHE.get(name.toLowerCase(Locale.ROOT).replaceAll("[^a-z!]", "")));
  }

  /**
   * calculates the value of permission default for the given operator value.
   *
   * @param op the op to calculate.
   *
   * @return {@code true} if the default should be true, or {@code false}
   */
  public boolean getValue(final boolean op) {
    switch (this) {
      case TRUE:
        return true;
      case OP:
        return op;
      case NOT_OP:
        return !op;
      case FALSE:
      default:
        return false;
    }
  }

  @NotNull
  @Override
  public String toString() {
    return this.names[0];
  }
}
