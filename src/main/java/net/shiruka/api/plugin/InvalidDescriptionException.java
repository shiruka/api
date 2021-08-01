package net.shiruka.api.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * an exception class that thrown when something wrong with the plugin file.
 */
final class InvalidDescriptionException extends Exception {

  /**
   * ctor.
   *
   * @param message the message.
   * @param args the args.
   */
  InvalidDescriptionException(@NotNull final String message, @NotNull final Object... args) {
    super(String.format(message, args));
  }
}
