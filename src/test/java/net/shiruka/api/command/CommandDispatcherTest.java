/*
 * MIT License
 *
 * Copyright (c) 2021 Shiru ka
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

package net.shiruka.api.command;

import static net.shiruka.api.command.Commands.*;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import org.junit.jupiter.api.Test;

final class CommandDispatcherTest {

  @Test
  void create() throws CommandSyntaxException {
    final var commandSender = new SCommandSender("null");
    final var built = literal("heal")
//      .playerOnly()
      .requires(sender -> true)
      .executes(context -> {
        context.getSender().sendMessage("Main command.");
        return CommandResult.succeed();
      })
      .then(arg("test", termArg("red", "blue"))
        .requires(sender -> true)
        .executes(context -> {
          final var value = getString(context, "test");
          if (!value.equalsIgnoreCase("red")) {
            context.getSender().sendMessage("blue");
            return CommandResult.succeed();
          }
          context.getSender().sendMessage("red");
          return CommandResult.succeed();
        }));
    final var dispatcher = new CommandDispatcher();
    dispatcher.register(built);
    dispatcher.execute("heal red", commandSender);
  }
}
