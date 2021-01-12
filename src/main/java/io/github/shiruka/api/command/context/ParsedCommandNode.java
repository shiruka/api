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

package io.github.shiruka.api.command.context;

import io.github.shiruka.api.command.CommandNode;
import io.github.shiruka.api.command.TextRange;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents parsed command nodes.
 */
public final class ParsedCommandNode {

  /**
   * the node.
   */
  @NotNull
  private final CommandNode node;

  /**
   * the range.
   */
  @NotNull
  private final TextRange range;

  /**
   * ctor.
   *
   * @param node the node.
   * @param range the range.
   */
  public ParsedCommandNode(@NotNull final CommandNode node, @NotNull final TextRange range) {
    this.node = node;
    this.range = range;
  }

  /**
   * obtains the node.
   *
   * @return node.
   */
  @NotNull
  public CommandNode getNode() {
    return this.node;
  }

  /**
   * obtains the range.
   *
   * @return range.
   */
  @NotNull
  public TextRange getRange() {
    return this.range;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.node, this.range);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    final var that = (ParsedCommandNode) obj;
    return Objects.equals(this.node, that.node) &&
      Objects.equals(this.range, that.range);
  }

  @Override
  public String toString() {
    return this.node + "@" + this.range;
  }
}
