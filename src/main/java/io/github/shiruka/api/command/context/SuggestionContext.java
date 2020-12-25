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

package io.github.shiruka.api.command.context;

import io.github.shiruka.api.command.tree.CommandNode;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents suggestion context.
 */
public final class SuggestionContext {

  /**
   * the parent.
   */
  @NotNull
  private final CommandNode parent;

  /**
   * the start position.
   */
  private final int startPos;

  /**
   * ctor.
   *
   * @param parent the parent.
   * @param startPos the start position.
   */
  public SuggestionContext(@NotNull final CommandNode parent, final int startPos) {
    this.parent = parent;
    this.startPos = startPos;
  }

  /**
   * obtains the parent.
   *
   * @return parent.
   */
  @NotNull
  public CommandNode getParent() {
    return this.parent;
  }

  /**
   * obtains the start position.
   *
   * @return start position.
   */
  public int getStartPos() {
    return this.startPos;
  }
}
