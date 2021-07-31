package net.shiruka.api.old.base;

/**
 * represents the type of a block or an item.
 */
public enum Material {
  /**
   * the air.
   */
  AIR(0);

  /**
   * the id.
   */
  private final int id;

  /**
   * ctor.
   *
   * @param id the id.
   */
  Material(final int id) {
    this.id = id;
  }

  /**
   * obtains the id.
   *
   * @return the id.
   */
  public int getId() {
    return this.id;
  }
}
