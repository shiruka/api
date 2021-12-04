package io.github.shiruka.api.world;

import java.util.Arrays;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine difficulty.
 *
 * every world has a difficulty level.
 * this difficulty level affects some events in the world.
 */
public interface Difficulty {

  /**
   * events in the world work according to these rules.
   */
  DifficultyRules rules();

  /**
   * peaceful, easy, normal, hard or custom.
   */
  DifficultyLevel level();

  /**
   * an enum class that contains world difficulties.
   */
  @Getter
  @Accessors(fluent = true)
  @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
  enum DifficultyLevel {
    /**
     * the peaceful.
     */
    PEACEFUL(0),
    /**
     * the easy.
     */
    EASY(1),
    /**
     * the normal.
     */
    NORMAL(2),
    /**
     * the hard.
     */
    HARD(3),

    /**
     * the custom.
     */
    CUSTOM(4);

    /**
     * the id.
     */
    private final int id;

    /**
     * gets difficulty by id.
     *
     * @param id the id to get.
     *
     * @return difficulty.
     */
    @NotNull
    public static Optional<DifficultyLevel> byId(final int id) {
      return Arrays.stream(DifficultyLevel.values())
        .filter(difficulty -> difficulty.id() == id)
        .findFirst();
    }
  }
}
