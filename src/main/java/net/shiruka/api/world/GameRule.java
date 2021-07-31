package net.shiruka.api.world;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents game rules.
 *
 * @param <T> type of the game rule's value.
 */
public final class GameRule<T> {

  /**
   * the game rule cache by id.
   */
  private static final Map<String, GameRule<?>> BY_ID = new Object2ObjectOpenHashMap<>();

  /**
   * the description.
   */
  @NotNull
  @Getter
  private final String description;

  /**
   * the id.
   */
  @NotNull
  @Getter
  private final String id;

  /**
   * the persistent class.
   */
  @NotNull
  private final Class<T> persistentClass;

  /**
   * ctor.
   *
   * @param id the id.
   * @param description the description.
   */
  private GameRule(@NotNull final String id, @NotNull final String description) {
    try {
      //noinspection unchecked
      this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
        .getActualTypeArguments()[0];
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
    this.description = description;
    this.id = id;
    GameRule.BY_ID.put(this.id, this);
  }

  /**
   * gets the game rules by id.
   *
   * @param persistentClass the persistent class to get.
   * @param id the id to get.
   * @param <T> type of the game rule's value.
   *
   * @return game rule by id.
   */
  @NotNull
  public static <T> Optional<GameRule<T>> getById(@NotNull final Class<T> persistentClass, @NotNull final String id) {
    //noinspection unchecked
    return Optional.ofNullable(GameRule.BY_ID.get(id))
      .filter(gameRule -> gameRule.persistentClass.equals(persistentClass))
      .map(gameRule -> (GameRule<T>) gameRule);
  }

  /**
   * gets the game rules by id.
   *
   * @param id the id to get.
   *
   * @return game rule by id.
   */
  @NotNull
  public static Optional<GameRule<?>> getById(@NotNull final String id) {
    return Optional.ofNullable(GameRule.BY_ID.get(id));
  }

  /**
   * checks if {@link #persistentClass} is assignable from the given object's class.
   *
   * @param object the object to check.
   *
   * @return {@code true} if {@link #persistentClass} is assignable from the given object's class.
   */
  public boolean isTypeOfValue(@NotNull final Object object) {
    return this.persistentClass.isAssignableFrom(object.getClass());
  }
}
