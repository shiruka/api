package net.shiruka.api.old.plugin.java;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;
import net.shiruka.api.old.plugin.Plugin;
import net.shiruka.api.old.plugin.PluginDescriptionFile;
import net.shiruka.api.old.plugin.PluginLoader;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an abstract plugin class that developers have to extends to create plugins for Shiru ka.
 *
 * @todo #1:60m Implement JavaPlugin methods.
 */
public abstract class JavaPlugin implements Plugin {

  /**
   * the logger.
   */
  @Nullable
  private Logger logger;

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
