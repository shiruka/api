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

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine servers.
 */
public interface Server {

  /**
   * obtains the given class's implementation.
   *
   * @param cls the class to get.
   * @param <I> type of the interface.
   *
   * @return given class's implementation.
   *
   * @throws IllegalArgumentException if the given class's implementation not found.
   */
  @NotNull <I> I getInterface(@NotNull Class<I> cls);

  /**
   * obtains maximum player count of the server.
   *
   * @return maximum player count to join the server.
   */
  int getMaxPlayerCount();

  /**
   * obtains current player count of the server.
   *
   * @return current player count of the server.
   */
  int getPlayerCount();

  /**
   * obtains server's descriptions a.k.a. MOTD.
   *
   * @return server's descriptions.
   */
  @NotNull
  String getServerDescription();

  /**
   * obtains server's shutdown statement.
   *
   * @return {@code true} if the server is in the shutdown state.
   */
  boolean isInShutdownState();

  /**
   * obtains server's running statement.
   *
   * @return {@code true} if the server is running.
   */
  boolean isRunning();

  /**
   * registers the given class and it's implementation.
   *
   * @param cls the class to register.
   * @param implementation the implementation to register.
   * @param <I> type of the interface.
   */
  <I> void registerInterface(@NotNull Class<I> cls, @NotNull I implementation);

  /**
   * initiates the server.
   *
   * @param startTime the start time to start.
   */
  void startServer(long startTime);

  /**
   * closes the server.
   */
  void stopServer();

  /**
   * unregisters the given class and it's implementation.
   *
   * @param cls the class to unregister.
   * @param <I> type of the interface.
   */
  <I> void unregisterInterface(@NotNull Class<I> cls);
}
