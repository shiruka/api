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

package io.github.shiruka.api.plugin;

import java.io.File;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine plugin managers.
 */
public interface PluginManager {

  /**
   * disables and removes all plugins.
   */
  void clearPlugins();

  /**
   * disables the specified plugin.
   *
   * @param plugin the plugin to disable.
   */
  void disablePlugin(@NotNull Plugin plugin);

  /**
   * disables the specified plugin.
   *
   * @param plugin the plugin to disable
   * @param closeClassloader the close classloader to disable.
   */
  void disablePlugin(@NotNull Plugin plugin, boolean closeClassloader);

  /**
   * disables all the loaded plugins.
   */
  void disablePlugins();

  /**
   * enables the specified plugin.
   *
   * @param plugin the plugin to enable.
   */
  void enablePlugin(@NotNull Plugin plugin);

  /**
   * checks if the given plugin is loaded and returns it when applicable.
   *
   * @param name the name to get..
   *
   * @return plugin.
   */
  @NotNull
  Optional<Plugin> getPlugin(@NotNull String name);

  /**
   * gets a list of all currently loaded plugins.
   *
   * @return array of plugins.
   */
  @NotNull
  Plugin[] getPlugins();

  /**
   * checks if the given plugin is enabled or not.
   *
   * @param name the name to check.
   *
   * @return {@code true} if the plugin is enabled, otherwise {@code false}.
   */
  boolean isPluginEnabled(@NotNull String name);

  /**
   * checks if the given plugin is enabled or not.
   *
   * @param plugin the plugin to check
   *
   * @return {@code true} if the plugin is enabled, otherwise {@code false}
   */
  boolean isPluginEnabled(@NotNull Plugin plugin);

  /**
   * Loads the plugin in the specified file
   *
   * @param file File containing the plugin to load
   *
   * @return The Plugin loaded, or null if it was invalid
   *
   * @throws InvalidPluginException Thrown when the specified file is not a valid plugin
   * @throws InvalidDescriptionException Thrown when the specified file contains an invalid description
   * @throws UnknownDependencyException If a required dependency could not be resolved
   */
  @NotNull
  Optional<Plugin> loadPlugin(@NotNull File file) throws InvalidPluginException, InvalidDescriptionException,
    UnknownDependencyException;

  /**
   * Loads the plugins contained within the specified directory
   *
   * @param directory Directory to check for plugins
   *
   * @return A list of all plugins loaded
   */
  @NotNull
  Plugin[] loadPlugins(@NotNull File directory);
}
