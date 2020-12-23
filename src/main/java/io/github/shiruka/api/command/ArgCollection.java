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

package io.github.shiruka.api.command;

import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine arg manager.
 */
public interface ArgCollection extends List<Arg> {

  /**
   * obtains the previous arg instance.
   *
   * @return previous arg instance.
   */
  @NotNull
  default Optional<Arg> previous() {
    return this.previous(1);
  }

  /**
   * obtains the previous arg instance.
   *
   * @param step the step to how much going on.
   *
   * @return previous arg instance.
   *
   * @throws IllegalArgumentException if the {@code step} is negative.
   */
  @NotNull
  Optional<Arg> previous(int step);
}
