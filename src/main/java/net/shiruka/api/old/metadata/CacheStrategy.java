package net.shiruka.api.old.metadata;

/**
 * an enum class that represents possible caching strategies for metadata.
 */
public enum CacheStrategy {
  /**
   * once the metadata value has been evaluated, do not re-evaluate the value until it is manually invalidated.
   */
  CACHE_AFTER_FIRST_EVAL,
  /**
   * re-evaluate the metadata item every time it is requested.
   */
  NEVER_CACHE,
  /**
   * once the metadata value has been evaluated, do not re-evaluate the value in spite of manual invalidation.
   */
  CACHE_ETERNALLY
}
