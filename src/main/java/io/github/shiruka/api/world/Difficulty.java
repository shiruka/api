package io.github.shiruka.api.world;

import java.util.Arrays;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains world difficulties.
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum Difficulty {
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
  HARD(3);

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
  public static Optional<Difficulty> byId(final int id) {
    return Arrays
      .stream(Difficulty.values())
      .filter(difficulty -> difficulty.id() == id)
      .findFirst();
  }
}
