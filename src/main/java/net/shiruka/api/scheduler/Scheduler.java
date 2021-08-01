package net.shiruka.api.scheduler;

import net.shiruka.api.Shiruka;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine schedulers.
 */
public interface Scheduler {

  /**
   * gets the implementation of async Scheduler.
   *
   * @return implementation of async Scheduler.
   */
  @NotNull
  static Scheduler getAsync() {
    return Shiruka.provideOrThrow(Async.class);
  }

  /**
   * gets the implementation of sync Scheduler.
   *
   * @return implementation of sync Scheduler.
   */
  @NotNull
  static Scheduler getSync() {
    return Shiruka.provideOrThrow(Sync.class);
  }

  /**
   * executes the given task.
   *
   * @param task the task to execute.
   */
  void execute(@NotNull Task task);

  /**
   * creates a task builder.
   *
   * @return a newly created task builder.
   */
  @NotNull
  Task.Builder newBuilder();

  /**
   * a marker interface to determine async schedulers.
   */
  interface Async extends Scheduler {

  }

  /**
   * a marker interface to determine sync schedulers.
   */
  interface Sync extends Scheduler {

  }
}
