package io.github.shiruka.api.plugin;

/**
 * an exception class that thrown when attempting to load an invalid plugin file.
 */
public final class InvalidPluginException extends Exception {

  /**
   * ctor.
   *
   * @param cause the cause.
   */
  public InvalidPluginException(final Throwable cause) {
    super(cause);
  }
}
