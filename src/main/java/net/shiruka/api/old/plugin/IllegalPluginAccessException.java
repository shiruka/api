package net.shiruka.api.old.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * thrown when a plugin attempts to interact with the server when it is not enabled.
 */
public class IllegalPluginAccessException extends RuntimeException {

  /**
   * ctor.
   */
  public IllegalPluginAccessException() {
  }

  /**
   * ctor.
   *
   * @param message the message.
   */
  public IllegalPluginAccessException(@NotNull final String message) {
    super(message);
  }
}
