package net.shiruka.api.text;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Throws;

final class ChatColorTest {

  @Test
  void getColorChar() {
    MatcherAssert.assertThat(
      "Couldn't get the correct color character!",
      ChatColor.BLACK.getColorChar(),
      new IsEqual<>('0'));
  }

  @Test
  void of() {
    MatcherAssert.assertThat(
      "Couldn't find the correct ChatColor instance.",
      ChatColor.of('0'),
      new IsEqual<>(ChatColor.BLACK));
    MatcherAssert.assertThat(
      "Couldn't find the correct ChatColor instance.",
      ChatColor.of('1'),
      new IsEqual<>(ChatColor.DARK_BLUE));
    MatcherAssert.assertThat(
      "Couldn't find the correct ChatColor instance.",
      ChatColor.of('2'),
      new IsEqual<>(ChatColor.DARK_GREEN));
    MatcherAssert.assertThat(
      "ChatColor's of method with the wrong character didn't throw.",
      () -> ChatColor.of('z'),
      new Throws<>(IllegalArgumentException.class));
  }
}
