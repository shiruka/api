package net.shiruka.api.event.events.server.exception;

import net.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * wrapper exception for all cases to which a plugin can be immediately blamed for.
 */
public class ServerPluginException extends ServerException {

  /**
   * the responsible plugin.
   */
  @NotNull
  private final Plugin responsiblePlugin;

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   * @param responsiblePlugin the responsible plugin.
   */
  public ServerPluginException(@NotNull final String message, @NotNull final Throwable cause,
                               @NotNull final Plugin responsiblePlugin) {
    super(message, cause);
    this.responsiblePlugin = responsiblePlugin;
  }

  /**
   * ctor.
   *
   * @param cause the cause.
   * @param responsiblePlugin the responsible plugin.
   */
  public ServerPluginException(@NotNull final Throwable cause, @NotNull final Plugin responsiblePlugin) {
    super(cause);
    this.responsiblePlugin = responsiblePlugin;
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   * @param enableSuppression the enable suppression.
   * @param writableStackTrace the writable stack trace.
   * @param responsiblePlugin the responsible plugin.
   */
  protected ServerPluginException(@NotNull final String message, @NotNull final Throwable cause,
                                  final boolean enableSuppression, final boolean writableStackTrace,
                                  @NotNull final Plugin responsiblePlugin) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.responsiblePlugin = responsiblePlugin;
  }

  /**
   * obtains the responsible plugin.
   *
   * @return responsible plugin.
   */
  @NotNull
  public Plugin getResponsiblePlugin() {
    return this.responsiblePlugin;
  }
}
