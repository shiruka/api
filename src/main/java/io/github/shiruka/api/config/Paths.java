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

package io.github.shiruka.api.config;

import io.github.shiruka.api.config.path.advanced.ApUniqueId;
import io.github.shiruka.api.config.path.advanced.ApUniqueIdList;
import io.github.shiruka.api.config.path.comment.CmBasic;
import io.github.shiruka.api.conf.path.simple.*;
import io.github.shiruka.api.config.path.simple.*;
import java.util.*;
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
   * represents {@link CpBoolean} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static CpBoolean booleanPath(@NotNull final String path, @Nullable final Boolean def) {
    return new CpBoolean(path, def);
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
  @NotNull
  public static <T> CommentablePath<T> commented(@NotNull final ConfigPath<T> path, @NotNull final String... comments) {
    return new CmBasic<>(path, comments);
  }

  /**
   * represents {@link CpDouble} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static CpDouble doublePath(@NotNull final String path, @Nullable final Double def) {
    return new CpDouble(path, def);
  }

  /**
   * represents {@link CpFloat} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static CpFloat floatPath(@NotNull final String path, @Nullable final Float def) {
    return new CpFloat(path, def);
  }

  /**
   * represents {@link CpInteger} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static CpInteger integerPath(@NotNull final String path, @Nullable final Integer def) {
    return new CpInteger(path, def);
  }

  /**
   * represents {@link CpSimple} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static CpSimple<List<String>> listStringPath(@NotNull final String path, @Nullable final String... def) {
    return Paths.simplePath(path, Optional.ofNullable(def)
      .map(Arrays::asList)
      .orElse(null));
  }

  /**
   * represents {@link CpSimple} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static CpSimple<List<String>> listStringPath(@NotNull final String path, final List<String> def) {
    return Paths.simplePath(path, def);
  }

  /**
   * represents {@link ApUniqueIdList} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static ApUniqueIdList listUniqueIdPath(@NotNull final String path, @Nullable final UUID... def) {
    return Paths.listUniqueIdPath(path, Optional.ofNullable(def)
      .map(Arrays::asList)
      .orElse(null));
  }

  /**
   * represents {@link ApUniqueIdList} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static ApUniqueIdList listUniqueIdPath(@NotNull final String path, @Nullable final List<UUID> def) {
    return new ApUniqueIdList(path, def);
  }

  /**
   * represents {@link CpLong} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static CpLong longPath(@NotNull final String path, @Nullable final Long def) {
    return new CpLong(path, def);
  }

  /**
   * represents {@link CpSimple} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static CpSimple<Map<String, Object>> mapPath(@NotNull final String path,
                                                      @Nullable final Map<String, Object> def) {
    return Paths.simplePath(path, def);
  }

  /**
   * represents {@link CpSimple} instance.
   *
   * @param path the path.
   * @param def th default value.
   * @param <T> type of the value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static <T> CpSimple<T> simplePath(@NotNull final String path, @Nullable final T def) {
    return new CpSimple<>(path, def);
  }

  /**
   * represents {@link CpString} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static CpString stringPath(@NotNull final String path, @Nullable final StringBuilder def) {
    return Paths.stringPath(path, Optional.ofNullable(def)
      .map(StringBuilder::toString)
      .orElse(null));
  }

  /**
   * represents {@link CpString} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static CpString stringPath(@NotNull final String path, @Nullable final String def) {
    return new CpString(path, def);
  }

  /**
   * represents {@link ApUniqueId} instance.
   *
   * @param path the path.
   * @param def th default value.
   *
   * @return a config path instance.
   */
  @NotNull
  public static ApUniqueId uniqueIdPath(@NotNull final String path, @Nullable final UUID def) {
    return new ApUniqueId(path, def);
  }
}
