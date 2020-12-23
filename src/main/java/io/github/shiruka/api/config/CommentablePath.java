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

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.simpleyaml.configuration.comments.CommentType;

/**
 * the interface of CommentablePath classes.
 *
 * @param <T> the type of the value.
 */
public interface CommentablePath<T> extends ConfigPath<T> {

  /**
   * gets the block comment.
   *
   * @return the comment.
   */
  @NotNull
  default Optional<String> getComment() {
    return this.getComment(CommentType.BLOCK);
  }

  /**
   * sets the block comment.
   *
   * @param comment the comment.
   */
  default void setComment(@Nullable final String comment) {
    this.setComment(CommentType.BLOCK, comment);
  }

  /**
   * gets the comment.
   *
   * @param commentType the comment type.
   *
   * @return the comment.
   */
  @NotNull
  Optional<String> getComment(@NotNull final CommentType commentType);

  /**
   * sets the comment.
   *
   * @param commentType the comment type.
   * @param comment the comment.
   */
  void setComment(@NotNull final CommentType commentType, @Nullable final String comment);
}
