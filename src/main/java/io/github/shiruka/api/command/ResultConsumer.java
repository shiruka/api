// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package io.github.shiruka.api.command;

import io.github.shiruka.api.command.context.CommandContext;
import org.jetbrains.annotations.NotNull;

/**
 * a functional interface to determine result consumers.
 */
@FunctionalInterface
public interface ResultConsumer {

  /**
   * runs the a command ran completely.
   *
   * @param context the context to use.
   * @param success the success value to use.
   * @param result the result to use.
   */
  void onCommandComplete(@NotNull CommandContext context, boolean success, @NotNull CommandResult result);
}
