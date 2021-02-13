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

import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine messages.
 */
public interface GameMessage extends Text {

  /**
   * adds the given {@code siblings}.
   *
   * @param siblings the siblings to add.
   */
  void addSiblings(@NotNull Text... siblings);

  /**
   * adds the given {@code siblings}.
   *
   * @param siblings the siblings to add.
   */
  default void addSiblings(@NotNull final String... siblings) {
    this.addSiblings(Arrays.stream(siblings).map(s -> (Text) () -> s).toArray(Text[]::new));
  }

  /**
   * obtains the siblings.
   *
   * @return siblings.
   */
  @NotNull
  List<Text> getSiblings();
}
