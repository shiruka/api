package net.shiruka.api.scheduler;

import net.shiruka.api.Shiruka;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine schedulers.
 */
public interface Scheduler {

  /**
   * gets the implementation of Scheduler.
   *
   * @return implementation of Scheduler.
   */
  @NotNull
  static Scheduler get() {
    return Shiruka.provideOrThrow(Scheduler.class);
  }
}
