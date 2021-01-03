/*
 * Copyright (c) 2020, GoMint, BlackyPaw and geNAZt
 *
 * This code is licensed under the BSD license found in the
 * LICENSE file in the root directory of this source tree.
 */

package io.github.shiruka.api.scheduler;

import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine tasks.
 */
public interface Task {

  /**
   * cancels the task.
   */
  void cancel();

  /**
   * registers a new command to fetch completion of tasks.
   *
   * @param command which should be added to the completion execution list.
   */
  void onComplete(@NotNull Runnable command);

  /**
   * registers a new command to fetch exceptions.
   *
   * @param command which should be used to handle exceptions.
   */
  void onException(@NotNull Predicate<Exception> command);
}
