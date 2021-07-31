package net.shiruka.api.command;

import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import net.shiruka.api.command.sender.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * a functional interface to determine redirect modifiers.
 */
@FunctionalInterface
public interface SingleRedirectModifier {

  /**
   * applies the parameters.
   *
   * @param context the context to apply.
   *
   * @return sender.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  @NotNull
  CommandSender apply(@NotNull CommandContext context) throws CommandSyntaxException;
}
