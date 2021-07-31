package net.shiruka.api.old.command;

import java.util.Arrays;
import java.util.Map;
import net.shiruka.api.old.Shiruka;
import net.shiruka.api.old.command.builder.LiteralBuilder;
import net.shiruka.api.old.command.sender.CommandSender;
import net.shiruka.api.old.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine command manager.
 */
public interface CommandManager {

  /**
   * runs the given input.
   * <p>
   * {@code command} can start with {@code /} or not. it does not matter.
   *
   * @param command the command to run.
   */
  default void execute(@NotNull final String command) {
    this.execute(command, Shiruka.getConsoleCommandSender());
  }

  /**
   * runs the given input.
   * <p>
   * {@code command} can start with {@code /} or not. it does not matter.
   *
   * @param command the command to run.
   * @param sender the sender to execute.
   */
  void execute(@NotNull String command, @NotNull CommandSender sender);

  /**
   * registers the given commands.
   *
   * @param plugin the plugin to register.
   * @param commands the commands to register.
   *
   * @throws IllegalArgumentException if any of given command is already registered.
   */
  void register(@NotNull Plugin plugin, @NotNull CommandNode... commands);

  /**
   * registers the given commands.
   *
   * @param plugin the plugin to register.
   * @param builders the builders to register.
   *
   * @throws IllegalArgumentException if any of given command is already registered.
   */
  default void register(@NotNull final Plugin plugin, @NotNull final LiteralBuilder... builders) {
    this.register(plugin, Arrays.stream(builders)
      .map(LiteralBuilder::build)
      .toArray(CommandNode[]::new));
  }

  /**
   * obtains the registered command map.
   *
   * @param plugin the plugin to obtain.
   *
   * @return registered command map.
   */
  @NotNull
  Map<String, CommandNode> registered(@NotNull Plugin plugin);

  /**
   * unregisters the given commands.
   *
   * @param commands the commands to unregister.
   */
  void unregister(@NotNull String... commands);

  /**
   * unregister the given commands.
   *
   * @param commands the commands to unregister.
   */
  default void unregister(@NotNull final CommandNode... commands) {
    this.unregister(Arrays.stream(commands)
      .map(CommandNode::getName)
      .toArray(String[]::new));
  }
}
