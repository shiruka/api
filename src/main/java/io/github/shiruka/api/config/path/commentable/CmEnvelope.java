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

package io.github.shiruka.api.config.path.commentable;

import io.github.shiruka.api.config.CommentablePath;
import io.github.shiruka.api.config.path.simple.CpEnvelope;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.simpleyaml.configuration.comments.CommentType;

/**
 * a commentable config path.
 *
 * @param <T> the type of the value.
 */
public abstract class CmEnvelope<T> extends CpEnvelope<T> implements CommentablePath<T> {

  /**
   * the original {@link CommentablePath}.
   */
  @NotNull
  private final CommentablePath<T> origin;

  /**
   * ctor.
   *
   * @param origin the original.
   */
  protected CmEnvelope(@NotNull final CommentablePath<T> origin) {
    super(origin);
    this.origin = origin;
  }

  @NotNull
  @Override
  public final Optional<String> getComment(@NotNull final CommentType commentType) {
    return this.origin.getComment(commentType);
  }

  @Override
  public final void setComment(@NotNull final CommentType commentType, @Nullable final String comment) {
    this.origin.setComment(commentType, comment);
  }
}
