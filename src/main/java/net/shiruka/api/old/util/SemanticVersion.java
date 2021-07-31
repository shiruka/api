package net.shiruka.api.old.util;

import lombok.Data;

/**
 * a class that represents semantic versions.
 */
@Data
public final class SemanticVersion {

  /**
   * the major.
   */
  private int major;

  /**
   * the minor.
   */
  private int minor;

  /**
   * the patch.
   */
  private int patch;
}
