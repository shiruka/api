package net.shiruka.api.command;

import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import org.jetbrains.annotations.NotNull;

/**
 * a functional interface to determine commands.
 */
@FunctionalInterface
public interface Command {

  /**
   * runs the command with the given {@code context}.
   *
   * @param context the context to run.
   *
   * @return ran command result.
   *
   * @throws CommandSyntaxException if something goes wrong when running the command.
   */
  @NotNull
  CommandResult run(@NotNull CommandContext context) throws CommandSyntaxException;
}
