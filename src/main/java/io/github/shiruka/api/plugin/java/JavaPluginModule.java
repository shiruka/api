package io.github.shiruka.api.plugin.java;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import io.github.shiruka.api.plugin.Plugin;
import java.io.File;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents java plugin module.
 *
 * @param dataFolder the data folder.
 * @param description the description.
 * @param logger the logger.
 * @param pluginFile the plugin file.
 */
public record JavaPluginModule(
  @NotNull File dataFolder,
  @NotNull Plugin.Description description,
  @NotNull Logger logger,
  @NotNull File pluginFile
) implements Module {

  @Override
  public void configure(final Binder binder) {
    binder.bind(Logger.class).toInstance(this.logger);
    binder.bind(Plugin.Description.class).toInstance(this.description);
    binder.bind(File.class).annotatedWith(Names.named("dataFolder")).toInstance(this.dataFolder);
    binder.bind(File.class).annotatedWith(Names.named("pluginFile")).toInstance(this.pluginFile);
  }
}
