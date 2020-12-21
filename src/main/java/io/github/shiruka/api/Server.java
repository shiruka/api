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

import io.github.shiruka.api.events.EventFactory;
import io.github.shiruka.api.scheduler.Scheduler;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine Shiru ka's servers.
 */
public interface Server {

  /**
   * obtains the event factory instance.
   *
   * @return an event factory.
   */
  @NotNull
  EventFactory getEventFactory();

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
   * obtains the scheduler instance.
   *
   * @return a scheduler.
   */
  @NotNull
  Scheduler getScheduler();

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
   * runs the given input.
   * <p>
   * it can start with {@code /} or not. it does not matter.
   *
   * @param command the command to run.
   */
  void runCommand(@NotNull String command);

  /**
   * initiates the server.
   */
  void startServer();

  /**
   * closes the server.
   */
  void stopServer();
}
