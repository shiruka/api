package net.shiruka.api.old.command.suggestion;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import net.shiruka.api.old.command.TextRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
   * @param remainingLowercase the remaining lowercase to create.
   *
   * @return a new builder instance.
   */
  @NotNull
  public static Builder builder(@NotNull final String input, final int start,
                                @Nullable final String remainingLowercase) {
    return new Builder(input, start, remainingLowercase);
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
    return Suggestions.builder(input, start, null);
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
     * the remaining lowercase.
     */
    @Nullable
    private String remainingLowercase;

    /**
     * ctor.
     *
     * @param input the input.
     * @param start the start.
     * @param remainingLowercase the remaining lowercase.
     */
    Builder(@NotNull final String input, final int start, @Nullable final String remainingLowercase) {
      this.input = input;
      this.start = start;
      this.remaining = input.substring(start);
      this.remainingLowercase = remainingLowercase;
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
     * obtains the remaining lowercase.
     *
     * @return remaining lowercase.
     */
    @NotNull
    public String getRemainingToLowerCase() {
      if (this.remainingLowercase == null) {
        this.remainingLowercase = this.remaining.toLowerCase(Locale.ROOT);
      }
      return this.remainingLowercase;
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
      return Suggestions.builder(this.input, this.start);
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
