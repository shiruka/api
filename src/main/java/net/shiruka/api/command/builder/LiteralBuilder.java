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

package net.shiruka.api.command.builder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import net.shiruka.api.command.tree.LiteralNode;
import org.jetbrains.annotations.NotNull;

/**
 * a simple literal implementation for {@link ArgumentBuilder}.
 */
public final class LiteralBuilder extends ArgumentBuilder<LiteralBuilder> {

  /**
   * the aliases.
   */
  @NotNull
  private final List<String> aliases = new ObjectArrayList<>();

  /**
   * the literal.
   */
  @NotNull
  private final String literal;

  /**
   * ctor.
   *
   * @param literal the literal.
   * @param isDefaultNode the is default node.
   */
  public LiteralBuilder(@NotNull final String literal, final boolean isDefaultNode) {
    super(isDefaultNode);
    this.literal = literal;
  }

  /**
   * ctor.
   *
   * @param literal the literal.
   */
  public LiteralBuilder(@NotNull final String literal) {
    this(literal, false);
  }

  /**
   * adds the aliases.
   *
   * @param aliases the aliases to add.
   *
   * @return {@code this} for builder chain.
   */
  @NotNull
  public LiteralBuilder aliases(@NotNull final String... aliases) {
    this.aliases.addAll(List.of(aliases));
    return this.getSelf();
  }

  @NotNull
  @Override
  public LiteralNode build() {
    return this.createMain(this.getLiteral(), this.aliases.stream()
      .map(this::createAliases)
      .collect(Collectors.toCollection(ObjectArrayList::new)));
  }

  /**
   * obtains the aliases.
   *
   * @return aliases.
   */
  @NotNull
  public List<String> getAliases() {
    return Collections.unmodifiableList(this.aliases);
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
  public LiteralBuilder getSelf() {
    return this;
  }

  /**
   * creates a alias literal node.
   *
   * @param literal the literal to create.
   *
   * @return a newly created alias literal node.
   */
  @NotNull
  private LiteralNode createAliases(@NotNull final String literal) {
    return this.createMain(literal, Collections.emptyList());
  }

  /**
   * creates a main literal node instance.
   *
   * @param literal the literal to create.
   * @param aliases the aliases to create.
   *
   * @return a newly created main literal node.
   */
  @NotNull
  private LiteralNode createMain(@NotNull final String literal, @NotNull final List<LiteralNode> aliases) {
    final var node = new LiteralNode(aliases, this.getDefaultNode(), this.getDescription(), this.isFork(),
      this.isDefaultNode(), this.getModifier(), this.getRedirect(), this.getRequirements(), this.getCommand(), literal);
    this.getArguments().forEach(node::addChild);
    return node;
  }
}
