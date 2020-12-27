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

package io.github.shiruka.api.world;

import io.github.shiruka.api.world.options.Dimension;
import io.github.shiruka.api.world.options.WorldCreateSpec;
import java.nio.file.Path;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * a class that handles the world lifecycle, creating, loading, and saving worlds.
 */
public interface WorldLoader {

  /**
   * creates a new world using the given parameters as the world properties.
   *
   * @param name the name of the new world.
   * @param spec the world options.
   *
   * @return the new world.
   */
  @NotNull
  World create(@NotNull String name, @NotNull WorldCreateSpec spec);

  /**
   * removes the given world by unloading and then deleting the associated files.
   *
   * @param world the world to delete.
   *
   * @return {@code true} if the operation resulted in a  change, {@code false} if it failed or the world does
   *   not exist.
   */
  boolean delete(@NotNull World world);

  /**
   * obtains the world from the set of loaded worlds, or if it has not loaded yet, attempt to load the world.
   *
   * @param name the name of the world.
   *
   * @return the world, or if it doesn't exist, throw an  exception.
   */
  @NotNull
  World get(@NotNull String name);

  /**
   * obtains the default world that new players will join upon connection to the server.
   *
   * @return the default world.
   */
  @NotNull
  World getDefaultWorld();

  /**
   * obtains all of the worlds that are currently loaded on the server.
   *
   * @return all of the loaded worlds.
   */
  @NotNull
  Map<String, World> getWorlds();

  /**
   * loads method for shortcutting NBT decoding.
   *
   * @param name the name of the world to be loaded.
   * @param directory the directory folder.
   * @param dimension the dimension to load.
   *
   * @return the world, once it has loaded.
   */
  @NotNull
  World load(@NotNull final String name, @NotNull final Path directory, @NotNull final Dimension dimension);

  /**
   * loads all worlds.
   */
  void loadAll();
}
