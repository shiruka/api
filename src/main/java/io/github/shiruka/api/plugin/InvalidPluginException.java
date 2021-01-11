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

package io.github.shiruka.api.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * an exception that thrown when attempting to load an invalid plugin file.
 */
public class InvalidPluginException extends Exception {

  /**
   * ctor.
   *
   * @param cause Exception that triggered this Exception
   */
  public InvalidPluginException(@NotNull final Throwable cause) {
    super(cause);
  }

  /**
   * ctor.
   */
  public InvalidPluginException() {
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   */
  public InvalidPluginException(@NotNull final String message, @NotNull final Throwable cause) {
    super(message, cause);
  }

  /**
   * ctor.
   *
   * @param message the message.
   */
  public InvalidPluginException(@NotNull final String message) {
    super(message);
  }
}
