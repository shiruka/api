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

package io.github.shiruka.api.conf;

import io.github.portlek.replaceable.rp.RpList;
import io.github.portlek.replaceable.rp.RpString;
import io.github.shiruka.api.conf.path.advanced.ApReplaceableList;
import io.github.shiruka.api.conf.path.advanced.ApReplaceableString;
import io.github.shiruka.api.conf.path.comment.CmBasic;
import io.github.shiruka.api.conf.path.simple.CpString;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * all {@link ConfigPath} list.
 */
public final class Paths {

  /**
   * ctor.
   */
  private Paths() {
  }

  /**
   * represents {@link CmBasic} instance.
   *
   * @param path the path to the value.
   * @param comments the comment to create.
   * @param <T> path's value type.
   *
   * @return the commentable path.
   */
  public static <T> CommentablePath<T> commented(@NotNull final ConfigPath<T> path, @NotNull final String... comments) {
    return new CmBasic<>(path, comments);
  }

  /**
   * represents {@link io.github.shiruka.api.conf.path.simple.CpBoolean} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static io.github.shiruka.api.conf.path.simple.CpBoolean booleanPath(@NotNull final String path, @Nullable final Boolean def) {
    return new io.github.shiruka.api.conf.path.simple.CpBoolean(path, def);
  }

  /**
   * represents {@link io.github.shiruka.api.conf.path.simple.CpDouble} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static io.github.shiruka.api.conf.path.simple.CpDouble doublePath(@NotNull final String path, @Nullable final Double def) {
    return new io.github.shiruka.api.conf.path.simple.CpDouble(path, def);
  }

  /**
   * represents {@link io.github.shiruka.api.conf.path.simple.CpFloat} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static io.github.shiruka.api.conf.path.simple.CpFloat floatPath(@NotNull final String path, @Nullable final Float def) {
    return new io.github.shiruka.api.conf.path.simple.CpFloat(path, def);
  }

  /**
   * represents {@link io.github.shiruka.api.conf.path.simple.CpInteger} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static io.github.shiruka.api.conf.path.simple.CpInteger integerPath(@NotNull final String path, @Nullable final Integer def) {
    return new io.github.shiruka.api.conf.path.simple.CpInteger(path, def);
  }

  /**
   * represents {@link io.github.shiruka.api.conf.path.simple.CpLong} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static io.github.shiruka.api.conf.path.simple.CpLong longPath(@NotNull final String path, @Nullable final Long def) {
    return new io.github.shiruka.api.conf.path.simple.CpLong(path, def);
  }

  /**
   * represents {@link io.github.shiruka.api.conf.path.simple.CpString} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static io.github.shiruka.api.conf.path.simple.CpString stringPath(@NotNull final String path, @Nullable final StringBuilder def) {
    return Paths.stringPath(path, Optional.ofNullable(def)
      .map(StringBuilder::toString)
      .orElse(null));
  }

  /**
   * represents {@link io.github.shiruka.api.conf.path.simple.CpString} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static io.github.shiruka.api.conf.path.simple.CpString stringPath(@NotNull final String path, @Nullable final String def) {
    return new CpString(path, def);
  }

  /**
   * represents {@link ApReplaceableString} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static ApReplaceableString replaceableStringPath(final String path, final RpString def) {
    return new ApReplaceableString(path, def);
  }

  /**
   * represents {@link ApReplaceableList} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static ApReplaceableList replaceableListPath(final String path, final RpList def) {
    return new ApReplaceableList(path, def);
  }

  /**
   * represents {@link io.github.shiruka.api.conf.path.simple.CpSimple} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static io.github.shiruka.api.conf.path.simple.CpSimple<List<String>> listStringPath(final String path, final String... def) {
    return Paths.simplePath(path, Optional.ofNullable(def)
      .map(Arrays::asList)
      .orElse(null));
  }

  /**
   * represents {@link io.github.shiruka.api.conf.path.simple.CpSimple} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static io.github.shiruka.api.conf.path.simple.CpSimple<List<String>> listStringPath(final String path, final List<String> def) {
    return Paths.simplePath(path, def);
  }

  /**
   * represents {@link io.github.shiruka.api.conf.path.simple.CpSimple} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static io.github.shiruka.api.conf.path.simple.CpSimple<Map<String, Object>> mapPath(final String path, final Map<String, Object> def) {
    return Paths.simplePath(path, def);
  }

  /**
   * represents {@link io.github.shiruka.api.conf.path.simple.CpSimple} instance.
   *
   * @param path the path.
   * @param def th default value.
   * @param <T> type of the value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static <T> io.github.shiruka.api.conf.path.simple.CpSimple<T> simplePath(final String path, final T def) {
    return new io.github.shiruka.api.conf.path.simple.CpSimple<>(path, def);
  }
}
