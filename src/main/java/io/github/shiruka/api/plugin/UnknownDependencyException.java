package io.github.shiruka.api.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * an exception class that thrown when attempting to load an invalid plugin file.
 */
public final class UnknownDependencyException extends RuntimeException {

  /**
   * ctor.
   *
   * @param message the message.
   * @param args the args.
   */
  public UnknownDependencyException(
    @NotNull final String message,
    @NotNull final Object... args
  ) {
    super(message.formatted(args));
  }
}
