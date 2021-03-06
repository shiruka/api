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

import net.shiruka.api.command.ArgumentType;
import net.shiruka.api.command.SuggestionProvider;
import net.shiruka.api.command.tree.ArgumentNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a simple literal implementation for {@link ArgumentBuilder}.
 *
 * @param <V> type of the argument value.
 */
public final class RequiredBuilder<V> extends ArgumentBuilder<RequiredBuilder<V>> {

  /**
   * the default value.
   */
  @Nullable
  private final V defaultValue;

  /**
   * the name.
   */
  @NotNull
  private final String name;

  /**
   * the type.
   */
  @NotNull
  private final ArgumentType<V> type;

  /**
   * the suggestion override.
   */
  @Nullable
  private SuggestionProvider suggestions;

  /**
   * ctor.
   *
   * @param defaultValue the default value.
   * @param isDefaultNode the is default node.
   * @param name the name.
   * @param type the type.
   */
  @NotNull
  public RequiredBuilder(@Nullable final V defaultValue, final boolean isDefaultNode, @NotNull final String name,
                         @NotNull final ArgumentType<V> type) {
    super(isDefaultNode);
    this.defaultValue = defaultValue;
    this.name = name;
    this.type = type;
  }

  /**
   * ctor.
   *
   * @param name the name.
   * @param type the type.
   */
  @NotNull
  public RequiredBuilder(@NotNull final String name, @NotNull final ArgumentType<V> type) {
    this(null, false, name, type);
  }

  @NotNull
  @Override
  public ArgumentNode<V> build() {
    final var result = new ArgumentNode<>(this.getContextRequirement(), this.getDefaultNode(), this.getDefaultValue(),
      this.getDescription(), this.isFork(), this.isDefaultNode(), this.getModifier(), this.getRedirect(),
      this.getRequirements(), this.getCommand(), this.getName(), this.getSuggestions(), this.getType(),
      this.getUsage());
    this.getArguments().forEach(result::addChild);
    return result;
  }

  /**
   * obtains the default value.
   *
   * @return default value.
   */
  @Nullable
  public V getDefaultValue() {
    return this.defaultValue;
  }

  /**
   * obtains the name.
   *
   * @return name.
   */
  @NotNull
  public String getName() {
    return this.name;
  }

  @NotNull
  @Override
  public RequiredBuilder<V> getSelf() {
    return this;
  }

  /**
   * obtains the suggestion override.
   *
   * @return suggestion override.
   */
  @Nullable
  public SuggestionProvider getSuggestions() {
    return this.suggestions;
  }

  /**
   * obtains the type.
   *
   * @return type.
   */
  @NotNull
  public ArgumentType<V> getType() {
    return this.type;
  }

  /**
   * sets the {@link #suggestions}.
   *
   * @param suggestions the suggestion override to set.
   *
   * @return {@code this} for buider chain.
   */
  @NotNull
  public RequiredBuilder<V> suggests(@NotNull final SuggestionProvider suggestions) {
    this.suggestions = suggestions;
    return this.getSelf();
  }
}
