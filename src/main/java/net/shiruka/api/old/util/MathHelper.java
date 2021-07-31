package net.shiruka.api.old.util;

/**
 * an utility class that contains math methods.
 */
public final class MathHelper {

  /**
   * ctor.
   */
  private MathHelper() {
  }

  /**
   * floors the given number.
   *
   * @param number the number to floor.
   *
   * @return floored number.
   */
  public static int floor(final double number) {
    final var floor = (int) number;
    return floor < (double) floor ? floor - 1 : floor;
  }
}
