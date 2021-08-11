package io.github.shiruka.api.exception;

import io.github.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * an exception class that represents server plugin enable disable exception.
 */
public final class ServerPluginEnableDisableException extends ServerPluginException {

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   * @param plugin the plugin.
   */
  public ServerPluginEnableDisableException(@NotNull final String message, @NotNull final Throwable cause,
                                            @NotNull final Plugin.Container plugin) {
    super(message, cause, plugin);
  }

  /**
   * ctor.
   *
   * @param cause the cause.
   * @param plugin the plugin.
   */
  public ServerPluginEnableDisableException(@NotNull final Throwable cause, @NotNull final Plugin.Container plugin) {
    super(cause, plugin);
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   * @param enableSuppression the enable suppression.
   * @param writableStackTrace the writable stack trace.
   * @param plugin the plugin.
   */
  public ServerPluginEnableDisableException(@NotNull final String message, @NotNull final Throwable cause,
                                            final boolean enableSuppression, final boolean writableStackTrace,
                                            @NotNull final Plugin.Container plugin) {
    super(message, cause, enableSuppression, writableStackTrace, plugin);
  }
}
