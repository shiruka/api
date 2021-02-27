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

package net.shiruka.api.base;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import net.shiruka.api.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents game profiles of players.
 */
public final class GameProfile {

  /**
   * the name.
   */
  @NotNull
  private final Text name;

  /**
   * the unique id.
   */
  @NotNull
  private final UUID uniqueId;

  /**
   * the xbox unique id.
   */
  @Nullable
  private final String xboxUniqueId;

  /**
   * ctor.
   *
   * @param name the name.
   * @param uniqueId the unique id.
   * @param xboxUniqueId the xbox id.
   */
  public GameProfile(@NotNull final Text name, @NotNull final UUID uniqueId, @Nullable final String xboxUniqueId) {
    this.name = name;
    this.uniqueId = uniqueId;
    this.xboxUniqueId = xboxUniqueId;
  }

  /**
   * creates a new game profile instance from given {@code map}.
   *
   * @param map the map to create.
   *
   * @return a new game profile instance.
   */
  @NotNull
  public static Optional<GameProfile> deserialize(@NotNull final Map<String, Object> map) {
    try {
      final var name = (String) map.get("name");
      final var uniqueId = UUID.fromString((String) map.get("unique-id"));
      final var xboxUniqueId = map.getOrDefault("xbox-unique-id", null);
      final var parsedXboxId = xboxUniqueId instanceof String ? (String) xboxUniqueId : null;
      return Optional.of(new GameProfile(() -> name, uniqueId, parsedXboxId));
    } catch (final Exception e) {
      // ignored
    }
    return Optional.empty();
  }

  /**
   * obtains the name.
   *
   * @return name.
   */
  @NotNull
  public Text getName() {
    return this.name;
  }

  /**
   * the obtains the unique id.
   *
   * @return unique id.
   */
  @NotNull
  public UUID getUniqueId() {
    return this.uniqueId;
  }

  /**
   * obtains the xbox unique id.
   *
   * @return xbox unique id.
   */
  @Nullable
  public String getXboxUniqueId() {
    return this.xboxUniqueId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.uniqueId, this.xboxUniqueId);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    final GameProfile that = (GameProfile) obj;
    return this.name.equals(that.name) &&
      this.uniqueId.equals(that.uniqueId) &&
      Objects.equals(this.xboxUniqueId, that.xboxUniqueId);
  }

  @Override
  public String toString() {
    return "GameProfile{" +
      "name='" + this.name + '\'' +
      ", uniqueId=" + this.uniqueId +
      ", xboxUniqueId='" + this.xboxUniqueId + '\'' +
      '}';
  }

  /**
   * converts {@code this} to a {@link Map}.
   *
   * @return serialized entry.
   */
  @NotNull
  public Map<String, Object> serialize() {
    final var map = new Object2ObjectOpenHashMap<String, Object>();
    map.put("name", this.name.asString());
    map.put("unique-id", this.uniqueId.toString());
    map.put("xbox-unique-id", this.xboxUniqueId);
    return map;
  }
}
