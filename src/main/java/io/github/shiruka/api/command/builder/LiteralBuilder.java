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

package io.github.shiruka.api.command.builder;

import io.github.shiruka.api.command.tree.CommandNode;
import io.github.shiruka.api.command.tree.LiteralNode;
import org.jetbrains.annotations.NotNull;

/**
 * a simple literal implementation for {@link ArgumentBuilder}.
 */
public final class LiteralBuilder extends ArgumentBuilder<LiteralBuilder> {

  /**
   * the literal.
   */
  @NotNull
  private final String literal;

  /**
   * ctor.
   *
   * @param literal the literal.
   */
  public LiteralBuilder(@NotNull final String literal) {
    this.literal = literal;
  }

  @NotNull
  @Override
  public CommandNode build() {
    final var result = new LiteralNode(this.isFork(), this.getModifier(), this.getRedirect(), this.getRequirements(),
      this.getCommand(), this.getLiteral());
    this.getArguments().forEach(result::addChild);
    return result;
  }

  /**
   * obtains the literal.
   *
   * @return literal.
   */
  @NotNull
  public String getLiteral() {
    return this.literal;
  }

  @NotNull
  @Override
  public LiteralBuilder self() {
    return this;
  }
}
