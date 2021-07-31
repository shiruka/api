package net.shiruka.api.old.command;

import java.util.function.Predicate;
import net.shiruka.api.old.command.sender.CommandSender;

/**
 * a functional interface to determine requirements.
 */
@FunctionalInterface
public interface Requirement extends Predicate<CommandSender> {

}
