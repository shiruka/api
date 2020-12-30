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
import io.github.shiruka.api.console.ConsoleCommandSender;
import io.github.shiruka.api.events.EventFactory;
import io.github.shiruka.api.pack.PackManager;
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
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static CommandManager getCommandManager() {
    return Shiruka.getServer().getInterface(CommandManager.class);
  }

  /**
   * obtains the console command sender instance..
   *
   * @return the console command sender instance.
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static ConsoleCommandSender getConsoleCommandSender() {
    return Shiruka.getServer().getInterface(ConsoleCommandSender.class);
  }

  /**
   * obtains the currently running {@link Server}'s event factory.
   *
   * @return an {@link EventFactory} instance.
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static EventFactory getEventFactory() {
    return Shiruka.getServer().getInterface(EventFactory.class);
  }

  /**
   * obtains the resource pack manager.
   *
   * @return resource pack manager.
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static PackManager getResourcePackManager() {
    return Shiruka.getServer().getInterface(PackManager.class);
  }

  /**
   * obtains the scheduler instance.
   *
   * @return scheduler instance.
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static Scheduler getScheduler() {
    return Shiruka.getServer().getInterface(Scheduler.class);
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
