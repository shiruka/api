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
  void disablePlugin(@NotNull Plugin plugin, boolean closeClassloader);

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
