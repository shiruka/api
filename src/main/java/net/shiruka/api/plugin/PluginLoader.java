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

package net.shiruka.api.plugin;

import java.io.File;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine plugin loaders, which handles direct access to specific types of plugins.
 */
public interface PluginLoader {

  /**
   * disables the specified plugin.
   *
   * @param plugin the plugin to disable.
   * @param closeClassloader the close classloader to disable.
   */
  void disablePlugin(@NotNull final Plugin plugin, final boolean closeClassloader);

  /**
   * disables the specified plugin.
   *
   * @param plugin the plugin to disable.
   */
  default void disablePlugin(@NotNull final Plugin plugin) {
    this.disablePlugin(plugin, false);
  }

  /**
   * enables the specified plugin.
   *
   * @param plugin the plugin to enable.
   */
  void enablePlugin(@NotNull Plugin plugin);

  /**
   * loads a {@link PluginDescriptionFile} from the specified file.
   *
   * @param file the file to load.
   *
   * @return a new plugin description file instance loaded from the plugin.yml in the specified file.
   *
   * @throws InvalidDescriptionException if the plugin description file could not be created.
   */
  @NotNull
  PluginDescriptionFile getPluginDescription(@NotNull File file) throws InvalidDescriptionException;

  /**
   * returns a list of all filename filters expected by {@code this} plugin loader.
   *
   * @return filters.
   */
  @NotNull
  Pattern[] getPluginFileFilters();

  /**
   * loads the plugin contained in the specified file.
   *
   * @param file the file to load.
   *
   * @return plugin that was contained in the specified file.
   *
   * @throws InvalidPluginException thrown when the specified file is not a plugin.
   * @throws UnknownDependencyException if a required dependency could not be found.
   */
  @NotNull
  Plugin loadPlugin(@NotNull File file) throws InvalidPluginException, UnknownDependencyException;
}
