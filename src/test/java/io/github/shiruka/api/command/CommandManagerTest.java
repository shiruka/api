/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.shiruka.api.command;

import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.command.arguments.ArgumentType;
import io.github.shiruka.api.plugin.Plugin;

final class CommandManagerTest {

  private static final Plugin PLUGIN = new Plugin() {
  };

  void create() {
    final var built = CommandManager.literal("heal")
      .playerOnly()
      .requires(sender -> {
        return true;
      })
      .executes(context -> {
        return CommandResult.succeed();
      })
      .then(CommandManager.arg("test", ArgumentType.stringArg())
        .requires(sender -> {
          return true;
        })
        .executes(context -> {
          return CommandResult.succeed();
        }));
    Shiruka.getCommandManager().register(CommandManagerTest.PLUGIN, built);
  }
}
