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

package net.shiruka.api.command.suggestion;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import net.shiruka.api.command.TextRange;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents suggestions.
 */
public final class Suggestions {

  /**
   * the empty suggestion.
   */
  private static final Suggestions EMPTY = new Suggestions(TextRange.at(0), new ObjectArrayList<>());

  /**
   * the range.
   */
  @NotNull
  private final TextRange range;

  /**
   * the suggestions.
   */
  @NotNull
  private final List<Suggestion> suggestionList;

  /**
   * ctor.
   *
   * @param range the range.
   * @param suggestions the suggestions.
   */
  public Suggestions(@NotNull final TextRange range, @NotNull final ObjectList<Suggestion> suggestions) {
    this.range = range;
    this.suggestionList = ObjectLists.unmodifiable(suggestions);
  }

  /**
   * creates a new {@link Builder} instance.
   *
   * @param input the input to create.
   * @param start the start to create.
   *
   * @return a new builder instance.
   */
  @NotNull
  public static Builder builder(@NotNull final String input, final int start) {
    return new Builder(input, start);
  }

  /**
   * creates a new instance of {@code this}.
   *
   * @param command the command to create.
   * @param suggestions the suggestions to create.
   *
   * @return a new suggestions instance.
   */
  @NotNull
  public static Suggestions create(@NotNull final String command, @NotNull final Collection<Suggestion> suggestions) {
    if (suggestions.isEmpty()) {
      return Suggestions.EMPTY;
    }
    final var start = new AtomicInteger(Integer.MAX_VALUE);
    final var end = new AtomicInteger(Integer.MIN_VALUE);
    suggestions.forEach(suggestion -> {
      start.set(Math.min(suggestion.getRange().getStart(), start.get()));
      end.set(Math.max(suggestion.getRange().getEnd(), end.get()));
    });
    final var range = TextRange.between(start.get(), end.get());
    final var texts = new ObjectOpenHashSet<Suggestion>();
    for (final var suggestion : suggestions) {
      texts.add(suggestion.expand(command, range));
    }
    final var sorted = new ObjectArrayList<>(texts);
    sorted.sort(Suggestion::compareToIgnoreCase);
    return new Suggestions(range, sorted);
  }

  /**
   * obtains an empty instance of {@code this}.
   *
   * @return an empty suggestion instance.
   */
  @NotNull
  public static CompletableFuture<Suggestions> empty() {
    return CompletableFuture.completedFuture(Suggestions.EMPTY);
  }

  /**
   * merges the given command and input.
   *
   * @param command the command to merge.
   * @param input the input to merge.
   *
   * @return merged suggestions.
   */
  @NotNull
  public static Suggestions merge(@NotNull final String command, @NotNull final Collection<Suggestions> input) {
    if (input.isEmpty()) {
      return Suggestions.EMPTY;
    }
    if (input.size() == 1) {
      return input.iterator().next();
    }
    final var texts = new ObjectOpenHashBigSet<Suggestion>();
    input.stream()
      .map(Suggestions::getSuggestionList)
      .forEach(texts::addAll);
    return Suggestions.create(command, texts);
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

  /**
   * obtains the suggestion list.
   *
   * @return suggestion list.
   */
  @NotNull
  public List<Suggestion> getSuggestionList() {
    return this.suggestionList;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.range, this.suggestionList);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    final var that = (Suggestions) obj;
    return this.range.equals(that.range) &&
      this.suggestionList.equals(that.suggestionList);
  }

  @Override
  public String toString() {
    return "Suggestions{" +
      "range=" + this.range +
      ", suggestionList=" + this.suggestionList +
      '}';
  }

  /**
   * checks if {@link #suggestionList} is empty.
   *
   * @return {@code true} if {@link #suggestionList} is empty.}
   */
  public boolean isEmpty() {
    return this.suggestionList.isEmpty();
  }

  /**
   * a class that represents suggestion builder.
   */
  public static final class Builder {

    /**
     * the input.
     */
    @NotNull
    private final String input;

    /**
     * the remaining.
     */
    @NotNull
    private final String remaining;

    /**
     * the result.
     */
    @NotNull
    private final List<Suggestion> result = new ObjectArrayList<>();

    /**
     * the start.
     */
    private final int start;

    /**
     * ctor.
     *
     * @param input the input.
     * @param start the start.
     */
    Builder(@NotNull final String input, final int start) {
      this.input = input;
      this.start = start;
      this.remaining = input.substring(start);
    }

    /**
     * builds the suggestions.
     *
     * @return built suggestions.
     */
    @NotNull
    public Suggestions build() {
      return Suggestions.create(this.input, this.result);
    }

    /**
     * builds the suggested suggestions.
     *
     * @return completed suggestions.
     */
    @NotNull
    public CompletableFuture<Suggestions> buildFuture() {
      return CompletableFuture.completedFuture(this.build());
    }

    /**
     * obtains the input.
     *
     * @return input.
     */
    @NotNull
    public String getInput() {
      return this.input;
    }

    /**
     * obtains the remaining.
     *
     * @return remaining.
     */
    @NotNull
    public String getRemaining() {
      return this.remaining;
    }

    /**
     * obtains the start.
     *
     * @return start.
     */
    public int getStart() {
      return this.start;
    }

    /**
     * resets the builder.
     *
     * @return reset builder.
     */
    @NotNull
    public Builder restart() {
      return new Builder(this.input, this.start);
    }

    /**
     * adds the given suggestion.
     *
     * @param suggestion the suggestion to add.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder suggest(@NotNull final String suggestion) {
      if (suggestion.equals(this.remaining)) {
        return this;
      }
      this.result.add(new Suggestion(TextRange.between(this.start, this.input.length()), suggestion));
      return this;
    }

    /**
     * adds the given suggestion.
     *
     * @param suggestion the suggestion to add.
     *
     * @return {@code this} for builder chain.
     */
    @NotNull
    public Builder suggest(final int suggestion) {
      this.result.add(new IntegerSuggestion(TextRange.between(this.start, this.input.length()), suggestion));
      return this;
    }
  }
}
