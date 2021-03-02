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

import io.github.portlek.configs.tree.FileConfiguration;
import java.io.File;
import java.io.InputStream;
import java.util.Optional;
import net.shiruka.api.world.ChunkGenerator;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine plugins.
 */
public interface Plugin {

  /**
   * obtains the config.
   *
   * @return config.
   */
  @NotNull
  FileConfiguration getConfig();

  /**
   * obtains the data folder.
   *
   * @return data folder.
   */
  @NotNull
  File getDataFolder();

  /**
   * gets a {@link ChunkGenerator} for use in a default world, as specified in the server configuration.
   *
   * @param worldName the world name to get.
   * @param id the id to get.
   *
   * @return chunkGenerator for use in the default world generation.
   */
  @NotNull
  default Optional<ChunkGenerator> getDefaultWorldGenerator(@NotNull final String worldName,
                                                            @Nullable final String id) {
    return Optional.empty();
  }

  /**
   * obtains the plugin description file interface.
   *
   * @return plugin description.
   */
  @NotNull
  PluginDescriptionFile getDescription();

  /**
   * obtains the logger.
   *
   * @return logger.
   */
  @NotNull
  Logger getLogger();

  /**
   * obtains the plugin name.
   *
   * @return plugin name.
   */
  @NotNull
  default String getName() {
    return this.getDescription().getName();
  }

  /**
   * obtains the plugin loader.
   *
   * @return plugin loader.
   */
  @NotNull
  PluginLoader getPluginLoader();

  /**
   * obtains the resource from the given {@code path}.
   *
   * @param path the path to get.
   *
   * @return resource at {@code path}.
   */
  @NotNull
  InputStream getResource(@NotNull String path);

  /**
   * checks if the plugin is enabled.
   *
   * @return {@code true} if the plugin is enabled.
   */
  boolean isEnabled();

  /**
   * checks if we can still suspect to the logs about things.
   *
   * @return boolean whether we can suspect.
   */
  boolean isSusceptible();

  /**
   * sets susceptible state.
   *
   * @param canSuspect is this plugin still susceptible?
   */
  void setSusceptible(boolean canSuspect);

  /**
   * called when this plugin is disabled.
   */
  void onDisable();

  /**
   * called when this plugin is enabled.
   */
  void onEnable();

  /**
   * called after a plugin is loaded but before it has been enabled.
   */
  void onLoad();

  /**
   * saves the resource at the given {@code resourcePath}.
   *
   * @param resourcePath the resource path to save.
   * @param replace the replace to save.
   *
   * @throws IllegalArgumentException if the resource path is empty, or points to a nonexistent resource.
   */
  void saveResource(@NotNull String resourcePath, boolean replace);
}
