package net.shiruka.api.plugin;

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
   * loads the plugin in the specified file.
   *
   * @param file the file containing the plugin to load.
   *
   * @return the plugin loaded, or null if it was invalid.
   *
   * @throws InvalidPluginException thrown when the specified file is not a valid plugin.
   * @throws InvalidDescriptionException thrown when the specified file contains an invalid description.
   * @throws UnknownDependencyException if a required dependency could not be resolved.
   */
  @NotNull
  Optional<Plugin> loadPlugin(@NotNull File file) throws InvalidPluginException, InvalidDescriptionException,
    UnknownDependencyException;

  /**
   * loads the plugins contained within the specified directory.
   *
   * @param directory the directory to check for plugins
   *
   * @return a list of all plugins loaded.
   */
  @NotNull
  Plugin[] loadPlugins(@NotNull File directory);
}
