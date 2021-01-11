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

package io.github.shiruka.api;

import com.google.common.base.Preconditions;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that contains Shiru ka's implementations.
 */
final class Implementation {

  /**
   * the lock used for writing the impl field.
   */
  private static final Object LOCK = new Object();

  /**
   * the server implementation.
   */
  @Nullable
  private static Server server;

  /**
   * ctor.
   */
  private Implementation() {
  }

  /**
   * obtains the current {@link Server} singleton.
   *
   * @return the server instance being ran.
   */
  @NotNull
  static Server getServer() {
    return Objects.requireNonNull(Implementation.server, "Cannot get the Server before it initialized!");
  }

  /**
   * sets the {@link Server} singleton to the given server instance.
   *
   * @param server the server to set.
   */
  static void setServer(@NotNull final Server server) {
    Preconditions.checkArgument(Implementation.server == null,
      "Cannot set the server after it initialized!");
    synchronized (Implementation.LOCK) {
      if (Implementation.server == null) {
        Implementation.server = server;
      }
    }
  }
}
