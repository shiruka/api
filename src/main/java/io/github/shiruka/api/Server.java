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

package io.github.shiruka.api;

import io.github.shiruka.common.fragment.FragmentDescription;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine Shiru ka's servers.
 */
public interface Server {

  /**
   * filters the given fragment descriptions.
   *
   * @param descriptions the descriptions to filter.
   *
   * @return the filtered description map.
   */
  @NotNull
  default Map<String, FragmentDescription> filterFragments(
    @NotNull final Map<String, FragmentDescription> descriptions) {
    return descriptions;
  }

  /**
   * runs the given input.
   * <p>
   * it can start with {@code /} or not. it does not matter.
   *
   * @param command the command to run.
   */
  void runCommand(@NotNull String command);

  /**
   * obtains server's shutdown statement.
   *
   * @return {@code true} if the server is in the shutdown state.
   */
  boolean isInShutdownState();
}
