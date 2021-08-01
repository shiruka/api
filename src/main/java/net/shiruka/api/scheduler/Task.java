package net.shiruka.api.scheduler;

import java.time.Duration;
import java.util.function.Consumer;
import net.shiruka.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine tasks.
 */
public interface Task {

  /**
   * creates a task builder.
   *
   * @return a newly created task builder.
   */
  @NotNull
  static Task.Builder newBuilder() {
    return Scheduler.get().newBuilder();
  }

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
   * obtains the plugin.
   *
   * @return plugin.
   */
  @NotNull
  Plugin getPlugin();

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
     * sets task async.
     *
     * @param async the async to set.
     *
     * @return creates a clone of {@code this} with the new async value.
     */
    @NotNull
    Builder withAsync(boolean async);

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

    /**
     * sets the plugin of the task.
     *
     * @param plugin the plugin to set.
     *
     * @return creates a clone of {@code this} with the new plugin value.
     */
    @NotNull
    Builder withPlugin(@NotNull Plugin plugin);
  }
}
