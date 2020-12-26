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

import io.github.shiruka.api.command.CommandManager;
import io.github.shiruka.api.command.CommandSender;
import io.github.shiruka.api.events.EventFactory;
import io.github.shiruka.api.scheduler.Scheduler;
import org.jetbrains.annotations.NotNull;

/**
 * a class that contains utility methods for Shiru ka server.
 */
public interface Shiruka {

  /**
   * obtains the command manager instance.
   *
   * @return a {@link CommandManager} instance.
   */
  @NotNull
  static CommandManager getCommandManager() {
    return Shiruka.getServer().getCommandManager();
  }

  /**
   * obtains the console command sender instance..
   *
   * @return the console command sender instance.
   */
  @NotNull
  static CommandSender getConsoleCommandSender() {
    return Shiruka.getServer().getConsoleCommandSender();
  }

  /**
   * obtains the currently running {@link Server}'s event factory.
   *
   * @return an {@link EventFactory} instance.
   */
  @NotNull
  static EventFactory getEventFactory() {
    return Shiruka.getServer().getEventFactory();
  }

  /**
   * obtains the scheduler instance.
   *
   * @return scheduler instance.
   */
  @NotNull
  static Scheduler getScheduler() {
    return Shiruka.getServer().getScheduler();
  }

  /**
   * obtains the currently running {@link Server} instance.
   *
   * @return a {@link Server} instance.
   */
  @NotNull
  static Server getServer() {
    return Implementation.getServer();
  }

  /**
   * sets the server from the given parameters.
   *
   * @param server the server to set.
   */
  static void setServer(@NotNull final Server server) {
    Implementation.setServer(server);
  }
}
