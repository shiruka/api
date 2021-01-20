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
