package net.shiruka.api.old.command;

import java.util.Collection;
import net.shiruka.api.old.command.context.CommandContext;
import net.shiruka.api.old.command.exceptions.CommandSyntaxException;
import net.shiruka.api.old.command.sender.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * a functional interface to determine redirect modifiers.
 */
@FunctionalInterface
public interface RedirectModifier {

  /**
   * applies the parameters.
   *
   * @param context the context to apply.
   *
   * @return sender list.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  @NotNull
  Collection<CommandSender> apply(@NotNull CommandContext context) throws CommandSyntaxException;
}
