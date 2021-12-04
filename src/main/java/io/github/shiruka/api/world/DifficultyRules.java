package io.github.shiruka.api.world;

/**
 * Difficulty differences occur according to these rules.
 */
public interface DifficultyRules {

  /**
   * @return creation of harmful creatures.
   */
  boolean harmfulCreatureSpawn();

  /**
   * @return makes it rain or not.
   */
  boolean rain();

}
