package net.shiruka.api.util;

/**
 * an functional interface to avoid runnable functions which have to add try-catch.
 */
@FunctionalInterface
public interface ThrowableRunnable extends Runnable {

  /**
   * runs the function.
   *
   * @throws Exception if the function throws an exception.
   */
  void call() throws Exception;

  @Override
  default void run() {
    try {
      this.call();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }
}
