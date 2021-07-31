package net.shiruka.api.scheduler;

import java.time.Duration;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine tasks.
 */
public interface Task {

  /**
   * obtains the delay.
   *
   * @return delay.
   */
  @NotNull
  Duration getDelay();

  /**
   * obtains the interval.
   *
   * @return interval.
   */
  @NotNull
  Duration getInterval();

  /**
   * obtains the job.
   *
   * @return the job
   */
  @NotNull
  Consumer<ScheduledTask> getJob();

  /**
   * obtains the name.
   *
   * @return name.
   */
  @NotNull
  String getName();

  /**
   * an interface to determine builders for {@link Task}.
   */
  interface Builder {

    /**
     * builds the task.
     *
     * @return built task.
     *
     * @throws IllegalStateException if the job or plugin set.
     */
    @NotNull
    Task build();

    /**
     * sets the delay of the task.
     *
     * @param delay the delay to set.
     *
     * @return creates a clone of {@code this} with the new delay value.
     */
    @NotNull
    Builder withDelay(@NotNull Duration delay);

    /**
     * sets the interval of the task.
     *
     * @param interval the interval to set.
     *
     * @return creates a clone of {@code this} with the new interval value.
     */
    @NotNull
    Builder withInterval(@NotNull Duration interval);

    /**
     * sets the job of the task.
     *
     * @param job the job to set.
     *
     * @return creates a clone of {@code this} with the new job value.
     */
    @NotNull
    Builder withJob(@NotNull Consumer<ScheduledTask> job);

    /**
     * sets the name of the task.
     *
     * @param name the name to set.
     *
     * @return creates a clone of {@code this} with the new name value.
     */
    @NotNull
    Builder withName(@NotNull String name);
  }
}
