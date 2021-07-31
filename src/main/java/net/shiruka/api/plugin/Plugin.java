package net.shiruka.api.plugin;

import java.io.File;
import java.io.InputStream;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine plugins.
 */
public interface Plugin {

  /**
   * obtains the data folder.
   *
   * @return data folder.
   */
  @NotNull
  File getDataFolder();

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
