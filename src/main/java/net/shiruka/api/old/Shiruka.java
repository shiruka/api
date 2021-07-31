package net.shiruka.api.old;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.Collection;
import net.shiruka.api.old.command.CommandManager;
import net.shiruka.api.old.command.sender.ConsoleCommandSender;
import net.shiruka.api.old.entity.Player;
import net.shiruka.api.old.event.EventManager;
import net.shiruka.api.old.language.LanguageManager;
import net.shiruka.api.old.pack.PackManager;
import net.shiruka.api.old.permission.PermissionManager;
import net.shiruka.api.old.plugin.PluginManager;
import net.shiruka.api.old.scheduler.Scheduler;
import net.shiruka.api.old.world.WorldManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * a class that contains utility methods for Shiru ka server.
 */
public interface Shiruka {

  /**
   * the json mapper.
   */
  JsonMapper JSON_MAPPER = new JsonMapper();

  /**
   * the yaml mapper.
   */
  YAMLMapper YAML_MAPPER = new YAMLMapper();

  /**
   * obtains the command manager instance.
   *
   * @return command manager instance.
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static CommandManager getCommandManager() {
    return Shiruka.getServer().getInterface(CommandManager.class);
  }

  /**
   * obtains the console command sender instance.
   *
   * @return the console command sender instance.
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static ConsoleCommandSender getConsoleCommandSender() {
    return Shiruka.getServer().getInterface(ConsoleCommandSender.class);
  }

  /**
   * obtains the event manager instance.
   *
   * @return event manager instance.
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static EventManager getEventManager() {
    return Shiruka.getServer().getInterface(EventManager.class);
  }

  /**
   * obtains the language manager instance.
   *
   * @return language manager instance.
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static LanguageManager getLanguageManager() {
    return Shiruka.getServer().getInterface(LanguageManager.class);
  }

  /**
   * obtains the server logger instance.
   *
   * @return server logger.
   */
  @NotNull
  static Logger getLogger() {
    return Shiruka.getServer().getLogger();
  }

  /**
   * obtains the online players.
   *
   * @return online players.
   */
  @NotNull
  static Collection<? extends Player> getOnlinePlayers() {
    return Shiruka.getServer().getOnlinePlayers();
  }

  /**
   * obtains the pack manager.
   *
   * @return resource pack manager.
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static PackManager getPackManager() {
    return Shiruka.getServer().getInterface(PackManager.class);
  }

  /**
   * obtains the permission manager instance.
   *
   * @return permission manager instance.
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static PermissionManager getPermissionManager() {
    return Shiruka.getServer().getInterface(PermissionManager.class);
  }

  /**
   * obtains the plugin manager.
   *
   * @return plugin manager instance.
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static PluginManager getPluginManager() {
    return Shiruka.getServer().getInterface(PluginManager.class);
  }

  /**
   * obtains the scheduler instance.
   *
   * @return scheduler instance.
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static Scheduler getScheduler() {
    return Shiruka.getServer().getInterface(Scheduler.class);
  }

  /**
   * obtains the currently running {@link Server} instance.
   *
   * @return a {@link Server} instance.
   */
  @NotNull
  static Server getServer() {
    return Implementation.getServer();
  }

  /**
   * sets the server from the given parameters.
   *
   * @param server the server to set.
   */
  static void setServer(@NotNull final Server server) {
    Implementation.setServer(server);
  }

  /**
   * obtains the world manager instance.
   *
   * @return world manager instance.
   *
   * @throws IllegalArgumentException if the implementation not found.
   */
  @NotNull
  static WorldManager getWorldManager() {
    return Shiruka.getServer().getInterface(WorldManager.class);
  }

  /**
   * checks the current thread against the expected primary thread for the server.
   *
   * @return {@code true} if the current thread matches the expected primary thread, otherwise {@code false}.
   */
  static boolean isPrimaryThread() {
    return Shiruka.getServer().isPrimaryThread();
  }

  /**
   * checks if the server is in the shutdown statement.
   *
   * @return {@code true} if the server is in the shutdown statement.
   */
  static boolean isStopping() {
    return Shiruka.getServer().isStopping();
  }
}
