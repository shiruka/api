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

package net.shiruka.api.config.path.commentable;

import java.util.EnumMap;
import java.util.Optional;
import net.shiruka.api.config.CommentablePath;
import net.shiruka.api.config.Config;
import net.shiruka.api.config.ConfigPath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.simpleyaml.configuration.comments.CommentType;
import org.simpleyaml.configuration.comments.Commentable;

/**
 * a commentable config path.
 *
 * @param <T> the type of the value.
 */
public final class CmBasic<T> implements CommentablePath<T> {

  /**
   * the default comments.
   */
  private final EnumMap<CommentType, String> defaultCommentMap = new EnumMap<>(CommentType.class);

  /**
   * the original {@link ConfigPath}.
   */
  @NotNull
  private final ConfigPath<T> origin;

  /**
   * ctor.
   *
   * @param origin the original config path.
   * @param defaultComments the default comments.
   */
  public CmBasic(@NotNull final ConfigPath<T> origin, @NotNull final String... defaultComments) {
    this.origin = origin;
    if (defaultComments.length > 0) {
      this.defaultCommentMap.put(CommentType.BLOCK, defaultComments[0]);
    }
    if (defaultComments.length > 1) {
      this.defaultCommentMap.put(CommentType.SIDE, defaultComments[1]);
    }
  }

  @NotNull
  @Override
  public Optional<String> getComment(@NotNull final CommentType commentType) {
    return Optional.ofNullable(this.getConfig()
      .map(Config::getConfiguration)
      .filter(config -> config instanceof Commentable)
      .map(config -> ((Commentable) config).getComment(this.getPath(), commentType))
      .orElse(this.defaultCommentMap.get(commentType)));
  }

  @Override
  public void setComment(@NotNull final CommentType commentType, @Nullable final String comment) {
    this.getConfig()
      .map(Config::getConfiguration)
      .filter(config -> config instanceof Commentable)
      .ifPresent(config -> ((Commentable) config).setComment(this.getPath(), comment, commentType));
  }

  @NotNull
  @Override
  public Optional<Config> getConfig() {
    return this.origin.getConfig();
  }

  @Override
  public void setConfig(@NotNull final Config config) {
    this.origin.setConfig(config);
    final var configuration = config.getConfiguration();
    if (!(configuration instanceof Commentable)) {
      return;
    }
    this.defaultCommentMap.forEach((type, s) -> {
      if (((Commentable) configuration).getComment(this.getPath(), type) == null) {
        ((Commentable) configuration).setComment(this.getPath(), s, type);
      }
    });
  }

  @NotNull
  @Override
  public Optional<T> getDefault() {
    return this.origin.getDefault();
  }

  @NotNull
  @Override
  public String getPath() {
    return this.origin.getPath();
  }

  @NotNull
  @Override
  public Optional<T> getValue() {
    return this.origin.getValue();
  }

  @Override
  public void setValue(@Nullable final T value) {
    this.origin.setValue(value);
  }
}
