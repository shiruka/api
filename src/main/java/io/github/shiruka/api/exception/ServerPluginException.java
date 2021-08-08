package io.github.shiruka.api.exception;

import io.github.shiruka.api.plugin.Plugin;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * an exception class that represents wrapper for server plugin exceptions.
 */
@Getter
public class ServerPluginException extends ServerException {

  /**
   * the plugin.
   */
  @NotNull
  private final Plugin.Container plugin;

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   * @param plugin the plugin.
   */
  public ServerPluginException(@NotNull final String message, @NotNull final Throwable cause,
                               @NotNull final Plugin.Container plugin) {
    super(message, cause);
    this.plugin = plugin;
  }

  /**
   * ctor.
   *
   * @param cause the cause.
   * @param plugin the plugin.
   */
  public ServerPluginException(@NotNull final Throwable cause, @NotNull final Plugin.Container plugin) {
    super(cause);
    this.plugin = plugin;
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
  public ServerPluginException(@NotNull final String message, @NotNull final Throwable cause,
                               final boolean enableSuppression, final boolean writableStackTrace,
                               @NotNull final Plugin.Container plugin) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.plugin = plugin;
  }
}
