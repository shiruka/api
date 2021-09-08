package io.github.shiruka.api.scheduler;

import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.plugin.Plugin;
import java.time.Duration;
import java.util.Optional;
import java.util.function.Consumer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
  static Task.Builder newAsyncBuilder() {
    return Scheduler.async().newBuilder();
  }

  /**
   * creates a sync task builder.
   *
   * @return a newly created sync task builder.
   */
  @NotNull
  static Task.Builder newSyncBuilder() {
    return Scheduler.sync().newBuilder();
  }

  /**
   * obtains the delay.
   *
   * @return delay.
   */
  @NotNull
  Duration delay();

  /**
   * executes the task.
   */
  default void execute() {
    this.scheduler().execute(this);
  }

  /**
   * obtains the interval.
   *
   * @return interval.
   */
  @NotNull
  Duration interval();

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
  @Nullable
  Plugin plugin();

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
    @NotNull
    Duration delay();

    /**
     * obtains the interval.
     *
     * @return interval.
     */
    @NotNull
    Duration interval();

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
    Optional<Plugin> plugin();

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
    Builder withPlugin(@Nullable Plugin plugin);

    /**
     * an implementation for task builder interface.
     */
    @Accessors(fluent = true)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    final class Impl implements Builder {

      /**
       * the scheduler.
       */
      @NotNull
      private final Scheduler scheduler;

      /**
       * the delay.
       */
      @With
      @Getter
      @NotNull
      private Duration delay;

      /**
       * the interval.
       */
      @With
      @Getter
      @NotNull
      private Duration interval;

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
      @Nullable
      private Plugin plugin;

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
        return Shiruka.taskFactory().create(this);
      }

      @NotNull
      @Override
      public Optional<Plugin> plugin() {
        return Optional.ofNullable(this.plugin);
      }
    }
  }

  /**
   * an interface to determine task providers.
   */
  interface Factory {

    /**
     * creates a task from the builder.
     *
     * @param builder the builder to create.
     *
     * @return a newly built task.
     */
    @NotNull
    Task create(@NotNull Builder builder);
  }
}
