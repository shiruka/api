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

package io.github.shiruka.api.events;

import io.github.shiruka.api.entity.Player;
import io.github.shiruka.api.event.Listener;
import io.github.shiruka.api.events.player.PlayerAsyncLoginEvent;
import io.github.shiruka.api.events.player.PlayerKickEvent;
import io.github.shiruka.api.events.player.PlayerPreLoginEvent;
import io.github.shiruka.api.language.TranslatedText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine event factory that helps to create and call events.
 */
public interface EventManager {

  /**
   * calls the given event.
   *
   * @param event the event to call.
   */
  void call(@NotNull Event event);

  /**
   * creates a new {@link PlayerAsyncLoginEvent} instance.
   *
   * @param loginData the login data to create.
   *
   * @return a new instance of {@link PlayerAsyncLoginEvent}.
   */
  @NotNull
  PlayerAsyncLoginEvent playerAsyncLogin(@NotNull LoginDataEvent.LoginData loginData);

  /**
   * creates a new {@link PlayerKickEvent} instance.
   *
   * @param player the player to create.
   * @param reason the reason to create.
   * @param text the text to create.
   *
   * @return a new instance of {@link PlayerKickEvent}.
   */
  @NotNull
  PlayerKickEvent playerKick(@NotNull Player player, @NotNull KickEvent.Reason reason, @NotNull TranslatedText text);

  /**
   * creates a new {@link PlayerPreLoginEvent} instance.
   *
   * @param loginData the login data to create.
   *
   * @return a new instance of {@link PlayerPreLoginEvent}.
   */
  @NotNull
  default PlayerPreLoginEvent playerPreLogin(@NotNull final LoginDataEvent.LoginData loginData) {
    return this.playerPreLogin(loginData, null);
  }

  /**
   * creates a new {@link PlayerPreLoginEvent} instance.
   *
   * @param loginData the login data to create.
   * @param kickMessage the kick message to create.
   *
   * @return a new instance of {@link PlayerPreLoginEvent}.
   */
  @NotNull
  PlayerPreLoginEvent playerPreLogin(@NotNull LoginDataEvent.LoginData loginData, @Nullable String kickMessage);

  /**
   * registers the given listener.
   *
   * @param listener the listener to register.
   */
  void register(@NotNull Listener listener);

  /**
   * unregisters the given listener.
   *
   * @param listener the listener to unregister.
   */
  void unregister(@NotNull Listener listener);
}
