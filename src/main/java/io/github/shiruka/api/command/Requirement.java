// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package io.github.shiruka.api.command;

import java.util.function.Predicate;

/**
 * a functional interface to determine requirements.
 */
@FunctionalInterface
public interface Requirement extends Predicate<CommandSender> {

}
