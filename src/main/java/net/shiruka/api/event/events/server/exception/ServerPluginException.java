/*
 * MIT License
 *
 * Copyright (c) 2021 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

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
