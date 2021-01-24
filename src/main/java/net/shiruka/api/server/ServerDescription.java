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

package net.shiruka.api.server;

import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;
import net.shiruka.api.base.GameMode;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents server descriptions.
 */
public final class ServerDescription {

  /**
   * the game mode.
   */
  @NotNull
  private final GameMode gameMode;

  /**
   * the maximum player count.
   */
  private final int maximumPlayerCount;

  /**
   * the protocol version.
   */
  private final int protocolVersion;

  /**
   * the server unique id.
   */
  private final long serverUniqueId;

  /**
   * the version name.
   */
  @NotNull
  private final String versionName;

  /**
   * the description.
   */
  @NotNull
  private String description;

  /**
   * the edition.
   */
  @NotNull
  private Edition edition;

  /**
   * the player count.
   */
  private int playerCount;

  /**
   * the world name.
   */
  @NotNull
  private String worldName;

  /**
   * ctor.
   *
   * @param gameMode the game mode.
   * @param description the description.
   * @param maximumPlayerCount the maximum player count.
   * @param serverUniqueId the server unique id.
   * @param playerCount the player count.
   * @param versionName the version name.
   * @param protocolVersion the protocol version.
   * @param edition the edition.
   * @param worldName the world name.
   */
  public ServerDescription(@NotNull final GameMode gameMode, final int maximumPlayerCount, final int protocolVersion,
                           final long serverUniqueId, @NotNull final String versionName,
                           @NotNull final String description, @NotNull final Edition edition, final int playerCount,
                           @NotNull final String worldName) {
    this.gameMode = gameMode;
    this.maximumPlayerCount = maximumPlayerCount;
    this.protocolVersion = protocolVersion;
    this.serverUniqueId = serverUniqueId;
    this.versionName = versionName;
    this.description = description;
    this.edition = edition;
    this.playerCount = playerCount;
    this.worldName = worldName;
  }

  /**
   * obtains the description.
   *
   * @return description.
   */
  @NotNull
  public String getDescription() {
    return this.description;
  }

  /**
   * sets the description.
   *
   * @param description the description to set.
   */
  public void setDescription(@NotNull final String description) {
    this.description = description;
  }

  /**
   * obtains the edition.
   *
   * @return edition.
   */
  @NotNull
  public Edition getEdition() {
    return this.edition;
  }

  /**
   * sets the edition.
   *
   * @param edition the edition to set.
   */
  public void setEdition(@NotNull final Edition edition) {
    this.edition = edition;
  }

  /**
   * obtains the game mode.
   *
   * @return game mode.
   */
  @NotNull
  public GameMode getGameMode() {
    return this.gameMode;
  }

  /**
   * obtains the maximum player count.
   *
   * @return maximum player count.
   */
  public int getMaximumPlayerCount() {
    return this.maximumPlayerCount;
  }

  /**
   * obtains the player count.
   *
   * @return player count.
   */
  public int getPlayerCount() {
    return this.playerCount;
  }

  /**
   * sets the player count.
   *
   * @param playerCount the player count to set.
   */
  public void setPlayerCount(final int playerCount) {
    this.playerCount = playerCount;
  }

  /**
   * obtains the protocol version.
   *
   * @return protocol version.
   */
  public int getProtocolVersion() {
    return this.protocolVersion;
  }

  /**
   * obtains the server unique id.
   *
   * @return server unique id.
   */
  public long getServerUniqueId() {
    return this.serverUniqueId;
  }

  /**
   * obtains the version name.
   *
   * @return version name.
   */
  @NotNull
  public String getVersionName() {
    return this.versionName;
  }

  /**
   * obtains the world name.
   *
   * @return world name.
   */
  @NotNull
  public String getWorldName() {
    return this.worldName;
  }

  /**
   * sets the world name.
   *
   * @param worldName the world name to set.
   */
  public void setWorldName(@NotNull final String worldName) {
    this.worldName = worldName;
  }

  /**
   * collects the current values and converts a byte array.
   *
   * @return current values as a byte array.
   */
  public byte[] toPacket() {
    final var joiner = new StringJoiner(";", "", ";")
      .add(this.edition.name())
      .add(this.description)
      .add(String.valueOf(this.protocolVersion))
      .add(this.versionName)
      .add(String.valueOf(this.playerCount))
      .add(String.valueOf(this.maximumPlayerCount))
      .add(String.valueOf(this.serverUniqueId))
      .add(this.gameMode.getType())
      .add(this.worldName);
    return joiner.toString().getBytes(StandardCharsets.UTF_8);
  }

  /**
   * an enum class that contains Minecraft game editions.
   */
  public enum Edition {
    /**
     * the minecraft pocket/bedrock edition.
     */
    MCPE,
    /**
     * the minecraft education edition.
     */
    MCEE
  }
}
