package net.shiruka.api.old.base;

/**
 * a class that represents axi aligned bounding boxes.
 */
public final class AxisAlignedBoundingBox {

  /**
   * the max x.
   */
  public final double maxX;

  /**
   * the max y.
   */
  public final double maxY;

  /**
   * the max z.
   */
  public final double maxZ;

  /**
   * the min x.
   */
  public final double minX;

  /**
   * the min y.
   */
  public final double minY;

  /**
   * the min z.
   */
  public final double minZ;

  /**
   * ctor.
   *
   * @param minX the min x.
   * @param minY the min y.
   * @param minZ the min z.
   * @param maxX the max x.
   * @param maxY the max y.
   * @param maxZ the max z.
   */
  public AxisAlignedBoundingBox(final double minX, final double minY, final double minZ,
                                final double maxX, final double maxY, final double maxZ) {
    this.maxX = maxX;
    this.maxY = maxY;
    this.maxZ = maxZ;
    this.minX = minX;
    this.minY = minY;
    this.minZ = minZ;
  }
}
