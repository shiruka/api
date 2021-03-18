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

package net.shiruka.api.event;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.UUID;
import net.shiruka.api.Shiruka;
import net.shiruka.api.base.ChainData;
import net.shiruka.api.command.sender.CommandSender;
import net.shiruka.api.entity.Player;
import net.shiruka.api.event.events.Event;
import net.shiruka.api.event.events.LoginResultEvent;
import net.shiruka.api.event.events.player.PlayerAsyncLoginEvent;
import net.shiruka.api.event.events.player.PlayerConnectionCloseEvent;
import net.shiruka.api.event.events.player.PlayerKickEvent;
import net.shiruka.api.event.events.player.PlayerLoginEvent;
import net.shiruka.api.event.events.player.PlayerPreLoginEvent;
import net.shiruka.api.event.events.server.AsyncTabCompleteEvent;
import net.shiruka.api.event.events.server.ServerCommandEvent;
import net.shiruka.api.event.events.server.ServerExceptionEvent;
import net.shiruka.api.event.events.server.ServerTickEndEvent;
import net.shiruka.api.event.events.server.ServerTickStartEvent;
import net.shiruka.api.event.events.server.exception.ServerException;
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
   * creates a new {@link AsyncTabCompleteEvent} instance.
   *
   * @param completer the completer to create.
   * @param completions the completions to create.
   * @param text the text to create.
   *
   * @return a new instance of {@link AsyncTabCompleteEvent}.
   */
  @NotNull
  AsyncTabCompleteEvent asyncTabComplete(@NotNull CommandSender completer, @NotNull List<String> completions,
                                         @NotNull String text);

  /**
   * calls the given event.
   *
   * @param event the event to call.
   */
  void call(@NotNull Event event);

  /**
   * creates a new {@link PlayerAsyncLoginEvent} instance.
   *
   * @param chainData the login data to create.
   *
   * @return a new instance of {@link PlayerAsyncLoginEvent}.
   */
  @NotNull
  PlayerAsyncLoginEvent playerAsyncLogin(@NotNull ChainData chainData);

  /**
   * creates a new {@link PlayerConnectionCloseEvent} instance.
   *
   * @param address the address to create.
   * @param name the name to create.
   * @param uniqueId the uniqueId to create.
   * @param xboxUniqueId the xboxUniqueId to create.
   *
   * @return a new instance of {@link PlayerConnectionCloseEvent}.
   */
  @NotNull
  PlayerConnectionCloseEvent playerConnectionClose(@NotNull InetSocketAddress address, @NotNull Text name,
                                                   @NotNull UUID uniqueId, @NotNull String xboxUniqueId);

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
  PlayerKickEvent playerKick(@NotNull Player player, @NotNull LoginResultEvent.LoginResult reason,
                             @NotNull Text kickMessage);

  /**
   * creates a new {@link PlayerKickEvent} instance.
   *
   * @param player the player to create.
   * @param reason the reason to create.
   *
   * @return a new instance of {@link PlayerKickEvent}.
   */
  @NotNull
  default PlayerKickEvent playerKick(@NotNull final Player player, @NotNull final LoginResultEvent.LoginResult reason) {
    return this.playerKick(player, reason,
      TranslatedText.get(EventManager.KEY_MULTIPLAYER_PLAYER_LEFT, player.getName()));
  }

  /**
   * creates a new {@link PlayerLoginEvent} instance.
   *
   * @param player the player to create.
   *
   * @return a new instance of {@link PlayerLoginEvent}.
   */
  @NotNull
  PlayerLoginEvent playerLogin(@NotNull Player player);

  /**
   * creates a new {@link PlayerPreLoginEvent} instance.
   *
   * @param chainData the login data to create.
   *
   * @return a new instance of {@link PlayerPreLoginEvent}.
   */
  @NotNull
  default PlayerPreLoginEvent playerPreLogin(@NotNull final ChainData chainData) {
    return this.playerPreLogin(chainData, null);
  }

  /**
   * creates a new {@link PlayerPreLoginEvent} instance.
   *
   * @param chainData the login data to create.
   * @param kickMessage the kick message to create.
   *
   * @return a new instance of {@link PlayerPreLoginEvent}.
   */
  @NotNull
  PlayerPreLoginEvent playerPreLogin(@NotNull ChainData chainData, @Nullable Text kickMessage);

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
   * creates a new {@link ServerTickEndEvent} instance.
   *
   * @param tick the tick to create.
   * @param duration the duration to create.
   * @param remaining the remaining to create.
   *
   * @return a new instance of {@link ServerTickEndEvent}.
   */
  @NotNull
  ServerTickEndEvent serverTickEnd(int tick, double duration, long remaining);

  /**
   * creates a new {@link ServerTickStartEvent} instance.
   *
   * @param tick the tick to create.
   *
   * @return a new instance of {@link ServerTickStartEvent}.
   */
  @NotNull
  ServerTickStartEvent serverTickStart(int tick);

  /**
   * unregisters the given listener.
   *
   * @param listener the listener to unregister.
   */
  void unregister(@NotNull Listener listener);
}
