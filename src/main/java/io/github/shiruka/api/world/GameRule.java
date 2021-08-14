package io.github.shiruka.api.world;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents game rules.
 *
 * @param nbtName the nbt name.
 * @param type the type.
 * @param <T> type of the rule.
 */
@SuppressWarnings("unchecked")
public record GameRule<T>(
  @NotNull String nbtName,
  @NotNull Class<? extends T> type
) {

  /**
   * the game rule map by nbt name.
   */
  private static final Map<String, GameRule<?>> BY_NBT_NAME = new ConcurrentHashMap<>();

  /**
   * gets the game rule by nbt name.
   *
   * @param nbtName the nbt name to get.
   *
   * @return game rule by nbt name.
   */
  @NotNull
  public static Optional<GameRule<?>> byNbtName(@NotNull final String nbtName) {
    return Optional.ofNullable(GameRule.BY_NBT_NAME.get(nbtName));
  }

  /**
   * gets the game rule by nbt name.
   *
   * @param nbtName the nbt name to get.
   * @param <T> type of the game rule.
   *
   * @return game rule by nbt name.
   */
  @NotNull
  public static <T> Optional<GameRule<T>> byNbtNameUnchecked(@NotNull final String nbtName) {
    return Optional.ofNullable(GameRule.BY_NBT_NAME.get(nbtName))
      .map(gameRule -> (GameRule<T>) gameRule);
  }
}
