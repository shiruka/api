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

package net.shiruka.api.events;

import net.shiruka.api.Shiruka;
import net.shiruka.api.command.sender.CommandSender;
import net.shiruka.api.entity.Player;
import net.shiruka.api.event.Listener;
import net.shiruka.api.events.player.PlayerAsyncLoginEvent;
import net.shiruka.api.events.player.PlayerKickEvent;
import net.shiruka.api.events.player.PlayerPreLoginEvent;
import net.shiruka.api.events.server.ServerCommandEvent;
import net.shiruka.api.events.server.ServerExceptionEvent;
import net.shiruka.api.events.server.ServerTickEvent;
import net.shiruka.api.events.server.exception.ServerException;
import net.shiruka.api.text.Text;
import net.shiruka.api.text.TranslatedText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine event factory that helps to create and call events.
 */
public interface EventManager {

  /**
   * the key of the player left message.
   */
  String KEY_MULTIPLAYER_PLAYER_LEFT = "multiplayer.player.left";

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
   * @param kickMessage the kickMessage to create.
   *
   * @return a new instance of {@link PlayerKickEvent}.
   */
  @NotNull
  PlayerKickEvent playerKick(@NotNull Player player, @NotNull KickEvent.Reason reason, @NotNull Text kickMessage);

  /**
   * creates a new {@link PlayerKickEvent} instance.
   *
   * @param player the player to create.
   * @param reason the reason to create.
   *
   * @return a new instance of {@link PlayerKickEvent}.
   */
  @NotNull
  default PlayerKickEvent playerKick(@NotNull final Player player, @NotNull final KickEvent.Reason reason) {
    return this.playerKick(player, reason,
      TranslatedText.get(EventManager.KEY_MULTIPLAYER_PLAYER_LEFT, player.getName()));
  }

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
  PlayerPreLoginEvent playerPreLogin(@NotNull LoginDataEvent.LoginData loginData, @Nullable Text kickMessage);

  /**
   * registers the given listener.
   *
   * @param listener the listener to register.
   */
  void register(@NotNull Listener listener);

  /**
   * creates a new {@link ServerCommandEvent} instance.
   *
   * @param sender the sender to create.
   * @param command the command to create.
   *
   * @return a new instance of {@link ServerCommandEvent}.
   */
  @NotNull
  ServerCommandEvent serverCommand(@NotNull CommandSender sender, @NotNull String command);

  /**
   * creates a new {@link ServerExceptionEvent} instance.
   *
   * @param serverException the server exception to create.
   *
   * @return a new instance of {@link ServerExceptionEvent}.
   */
  @NotNull
  default ServerExceptionEvent serverException(@NotNull final ServerException serverException) {
    return this.serverException(serverException, !Shiruka.isPrimaryThread());
  }

  /**
   * creates a new {@link ServerExceptionEvent} instance.
   *
   * @param serverException the server exception to create.
   * @param isAsync the is async to create.
   *
   * @return a new instance of {@link ServerExceptionEvent}.
   */
  @NotNull
  ServerExceptionEvent serverException(@NotNull ServerException serverException, boolean isAsync);

  /**
   * creates a new {@link ServerTickEvent} instance.
   *
   * @param tick the tick to create.
   *
   * @return a new instance of {@link ServerTickEvent}.
   */
  @NotNull
  ServerTickEvent serverTick(int tick);

  /**
   * unregisters the given listener.
   *
   * @param listener the listener to unregister.
   */
  void unregister(@NotNull Listener listener);
}
