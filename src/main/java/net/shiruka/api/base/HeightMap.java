package net.shiruka.api.base;

/**
 * an enum class that represents height maps.
 */
public enum HeightMap {
  /**
   * the highest block that blocks motion or contains a fluid.
   */
  MOTION_BLOCKING,
  /**
   * the highest block that blocks motion or contains a fluid or is in the.
   */
  MOTION_BLOCKING_NO_LEAVES,
  /**
   * the highest non-air block, solid block.
   */
  OCEAN_FLOOR,
  /**
   * the highest block that is neither air nor contains a fluid, for world generation.
   */
  OCEAN_FLOOR_WG,
  /**
   * the highest non-air block.
   */
  WORLD_SURFACE,
  /**
   * the highest non-air block, for world generation.
   */
  WORLD_SURFACE_WG,
}
