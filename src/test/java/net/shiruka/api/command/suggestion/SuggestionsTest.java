package net.shiruka.api.command.suggestion;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import net.shiruka.api.command.TextRange;
import org.junit.jupiter.api.Test;

final class SuggestionsTest {

  @Test
  void merge_empty() {
    final var merged = Suggestions.merge("foo b", Collections.emptyList());
    assertThat(merged.isEmpty(), is(true));
  }

  @Test
  void merge_multiple() {
    final var a = new Suggestions(TextRange.at(5), ObjectArrayList.of(
      new Suggestion(TextRange.at(5), "ar"),
      new Suggestion(TextRange.at(5), "az"),
      new Suggestion(TextRange.at(5), "Az")));
    final var b = new Suggestions(TextRange.between(4, 5), ObjectArrayList.of(
      new Suggestion(TextRange.between(4, 5), "foo"),
      new Suggestion(TextRange.between(4, 5), "qux"),
      new Suggestion(TextRange.between(4, 5), "apple"),
      new Suggestion(TextRange.between(4, 5), "Bar")));
    final var merged = Suggestions.merge("foo b", ObjectArrayList.of(a, b));
    assertThat(merged.getSuggestionList(), equalTo(ObjectArrayList.of(
      new Suggestion(TextRange.between(4, 5), "apple"),
      new Suggestion(TextRange.between(4, 5), "bar"),
      new Suggestion(TextRange.between(4, 5), "Bar"),
      new Suggestion(TextRange.between(4, 5), "baz"),
      new Suggestion(TextRange.between(4, 5), "bAz"),
      new Suggestion(TextRange.between(4, 5), "foo"),
      new Suggestion(TextRange.between(4, 5), "qux"))));
  }

  @Test
  void merge_single() {
    final var suggestions = new Suggestions(
      TextRange.at(5),
      ObjectArrayList.of(new Suggestion(TextRange.at(5), "ar")));
    final var merged = Suggestions.merge("foo b", Collections.singleton(suggestions));
    assertThat(merged, equalTo(suggestions));
  }
}
