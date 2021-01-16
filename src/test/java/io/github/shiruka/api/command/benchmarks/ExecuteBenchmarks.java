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

package io.github.shiruka.api.command.benchmarks;

import static io.github.shiruka.api.command.Commands.literal;
import com.google.common.collect.Lists;
import io.github.shiruka.api.command.CommandDispatcher;
import io.github.shiruka.api.command.CommandResult;
import io.github.shiruka.api.command.CommandSender;
import io.github.shiruka.api.command.SCommandSender;
import io.github.shiruka.api.command.context.ParseResults;
import io.github.shiruka.api.command.exceptions.CommandSyntaxException;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ExecuteBenchmarks {

  private final CommandSender sender1 = new SCommandSender("null1");

  private final CommandSender sender2 = new SCommandSender("null2");

  private final CommandSender sender3 = new SCommandSender("null3");

  private CommandDispatcher dispatcher;

  private ParseResults forkedRedirect;

  private ParseResults simple;

  private ParseResults singleRedirect;

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public void execute_forked_redirect() throws CommandSyntaxException {
    this.dispatcher.execute(this.forkedRedirect);
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public void execute_simple() throws CommandSyntaxException {
    this.dispatcher.execute(this.simple);
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public void execute_single_redirect() throws CommandSyntaxException {
    this.dispatcher.execute(this.singleRedirect);
  }

  @Setup
  public void setup() {
    this.dispatcher = new CommandDispatcher();
    this.dispatcher.register(literal("command")
      .executes(c -> CommandResult.empty()));
    this.dispatcher.register(literal("redirect")
      .redirect(this.dispatcher.getRoot()));
    this.dispatcher.register(literal("fork")
      .fork(this.dispatcher.getRoot(), o -> Lists.newArrayList(this.sender1, this.sender2, this.sender3)));
    this.simple = this.dispatcher.parse("command", this.sender1);
    this.singleRedirect = this.dispatcher.parse("redirect command", this.sender1);
    this.forkedRedirect = this.dispatcher.parse("fork command", this.sender1);
  }
}
