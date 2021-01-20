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

package net.shiruka.api.plugin.java;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;
import net.shiruka.api.config.config.YamlConfig;
import net.shiruka.api.plugin.Plugin;
import net.shiruka.api.plugin.PluginDescriptionFile;
import net.shiruka.api.plugin.PluginLoader;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an abstract plugin class that developers have to extends to create plugins for Shiru ka.
 */
public abstract class JavaPlugin implements Plugin {

  /**
   * the default YAML configuration of the plugin.
   */
  @Nullable
  private YamlConfig config;

  /**
   * the logger.
   */
  @Nullable
  private Logger logger;

  @NotNull
  @Override
  public final YamlConfig getConfig() {
    return Objects.requireNonNull(this.config, "The plugin not initiated yet!");
  }

  @NotNull
  @Override
  public final File getDataFolder() {
    return null;
  }

  @NotNull
  @Override
  public final PluginDescriptionFile getDescription() {
    return null;
  }

  @NotNull
  @Override
  public final Logger getLogger() {
    return Objects.requireNonNull(this.logger, "The plugin not initiated yet!");
  }

  @NotNull
  @Override
  public final PluginLoader getPluginLoader() {
    return null;
  }

  @NotNull
  @Override
  public final InputStream getResource(@NotNull final String path) {
    return null;
  }

  @Override
  public final boolean isEnabled() {
    return false;
  }

  @Override
  public final boolean isSusceptible() {
    return false;
  }

  @Override
  public final void setSusceptible(final boolean canSuspect) {
  }

  @Override
  public void onDisable() {
  }

  @Override
  public void onEnable() {
  }

  @Override
  public void onLoad() {
  }

  @Override
  public final void saveResource(@NotNull final String resourcePath, final boolean replace) {
  }

  final void setLogger(@NotNull final Logger logger) {
    this.logger = logger;
  }

  final void init(@NotNull final JavaPluginLoader loader, @Nullable final PluginDescriptionFile description,
                  @NotNull final File dataFolder, @NotNull final File file,
                  @NotNull final JavaPluginClassLoader classLoader) {
  }
}
