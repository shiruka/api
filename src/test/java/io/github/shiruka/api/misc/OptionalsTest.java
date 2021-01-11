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

package io.github.shiruka.api.misc;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

final class OptionalsTest {

  private static final String FINAL_RESULT = "Test\nTest-2";

  @Test
  void useAndGetPredicateTest() {
    final var builder = new StringBuilder("Test");
    final var result = Optionals.useAndGet(builder,
      sb -> sb.toString().equals("Test"),
      sb -> sb.append('\n').append("Test-2"));
    MatcherAssert.assertThat(
      "Couldn't use and get the given object!",
      result.toString(),
      new IsEqual<>(OptionalsTest.FINAL_RESULT));
    final var builder2 = new StringBuilder("Test");
    final var result2 = Optionals.useAndGet(builder2,
      sb -> sb.toString().equals("null"),
      sb -> sb.append('\n').append("Test-2"));
    MatcherAssert.assertThat(
      "Couldn't use and get the given object!",
      result2.toString(),
      new IsEqual<>("Test"));
  }

  @Test
  void useAndGetTest() {
    final var builder = new StringBuilder("Test");
    final var result = Optionals.useAndGet(builder, sb ->
      sb.append('\n').append("Test-2"));
    MatcherAssert.assertThat(
      "Couldn't use and get the given object!",
      result.toString(),
      new IsEqual<>(OptionalsTest.FINAL_RESULT));
  }
}
