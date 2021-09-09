package io.github.shiruka.api.scheduler;

import io.github.shiruka.api.plugin.Plugin;
import java.util.function.Consumer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * an interface to determine tasks.
 */
public interface Task {

  /**
   * creates an async task builder.
   *
   * @return a newly created async task builder.
   */
  @NotNull
  static Task.Builder asyncBuilder() {
    return Scheduler.async().newBuilder();
  }

  /**
   * creates a sync task builder.
   *
   * @return a newly created sync task builder.
   */
  @NotNull
  static Task.Builder syncBuilder() {
    return Scheduler.sync().newBuilder();
  }

  /**
   * obtains the delay.
   *
   * @return delay.
   */
  long delay();

  /**
   * executes the task.
   */
  @NotNull
  default ScheduledTask execute() {
    return this.scheduler().execute(this);
  }

  /**
   * obtains the interval.
   *
   * @return interval.
   */
  long interval();

  /**
   * checks if the task is async.
   *
   * @return {@code true } if the task is async.
   */
  default boolean isAsync() {
    return this.scheduler() instanceof Scheduler.Async;
  }

  /**
   * checks if the task is sync.
   *
   * @return {@code true } if the task is sync.
   */
  default boolean isSync() {
    return this.scheduler() instanceof Scheduler.Sync;
  }

  /**
   * obtains the job.
   *
   * @return the job
   */
  @NotNull
  Consumer<ScheduledTask> job();

  /**
   * obtains the name.
   *
   * @return name.
   */
  @NotNull
  String name();

  /**
   * obtains the plugin.
   *
   * @return plugin.
   */
  @NotNull
  Plugin.Container plugin();

  /**
   * obtains the scheduler.
   *
   * @return scheduler.
   */
  @NotNull
  Scheduler scheduler();

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
     * obtains the delay.
     *
     * @return delay.
     */
    long delay();

    /**
     * obtains the interval.
     *
     * @return interval.
     */
    long interval();

    /**
     * obtains the job.
     *
     * @return job.
     */
    @NotNull
    Consumer<ScheduledTask> job();

    /**
     * obtains the name.
     *
     * @return name.
     */
    @NotNull
    String name();

    /**
     * obtains the plugin.
     *
     * @return plugin.
     */
    @NotNull
    Plugin.Container plugin();

    /**
     * obtains the scheduler.
     *
     * @return scheduler.
     */
    @NotNull
    Scheduler scheduler();

    /**
     * sets the delay of the task.
     *
     * @param delay the delay to set.
     *
     * @return creates a clone of {@code this} with the new delay value.
     */
    @NotNull
    Builder withDelay(long delay);

    /**
     * sets the interval of the task.
     *
     * @param interval the interval to set.
     *
     * @return creates a clone of {@code this} with the new interval value.
     */
    @NotNull
    Builder withInterval(long interval);

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
    Builder withPlugin(@NotNull Plugin.Container plugin);

    /**
     * an implementation for task builder interface.
     */
    @Accessors(fluent = true)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    final class Impl implements Builder {

      /**
       * the scheduler.
       */
      @Getter
      @NotNull
      private final Scheduler scheduler;

      /**
       * the delay.
       */
      @With(onParam_ = {
        @Range(from = 0, to = Long.MAX_VALUE)
      })
      @Getter
      private long delay = -1L;

      /**
       * the interval.
       */
      @With(onParam_ = {
        @Range(from = 0, to = Long.MAX_VALUE)
      })
      @Getter
      private long interval = -1L;

      /**
       * the job.
       */
      @With
      @Getter
      @NotNull
      private Consumer<ScheduledTask> job;

      /**
       * the name.
       */
      @With
      @Getter
      @NotNull
      private String name;

      /**
       * the plugin.
       */
      @With
      @Getter
      @NotNull
      private Plugin.Container plugin;

      /**
       * ctor.
       *
       * @param scheduler the scheduler.
       */
      Impl(@NotNull final Scheduler scheduler) {
        this.scheduler = scheduler;
      }

      @NotNull
      @Override
      public Task build() {
        return new Task.Impl(this);
      }
    }
  }

  /**
   * an implementation for task.
   */
  @Accessors(fluent = true)
  final class Impl implements Task {

    /**
     * the delay.
     */
    @Getter
    private final long delay;

    /**
     * the interval.
     */
    @Getter
    private final long interval;

    /**
     * the job.
     */
    @Getter
    @NotNull
    private final Consumer<ScheduledTask> job;

    /**
     * the name.
     */
    @Getter
    @NotNull
    private final String name;

    /**
     * the plugin.
     */
    @Getter
    @NotNull
    private final Plugin.Container plugin;

    /**
     * the scheduler.
     */
    @Getter
    @NotNull
    private final Scheduler scheduler;

    /**
     * ctor.
     *
     * @param builder the builder.
     */
    Impl(@NotNull final Task.Builder builder) {
      this.scheduler = builder.scheduler();
      this.plugin = builder.plugin();
      this.delay = builder.delay();
      this.interval = builder.interval();
      this.job = builder.job();
      this.name = builder.name();
    }
  }
}
