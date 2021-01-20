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

package net.shiruka.api.command.benchmarks;

import static net.shiruka.api.command.Commands.literal;
import java.util.concurrent.TimeUnit;
import net.shiruka.api.command.CommandDispatcher;
import net.shiruka.api.command.CommandResult;
import net.shiruka.api.command.SCommandSender;
import net.shiruka.api.command.sender.CommandSender;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ParsingBenchmarks {

  private final CommandSender sender1 = new SCommandSender("null1");

  private CommandDispatcher subject;

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public void parse_() {
    this.subject.parse("c", this.sender1);
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public void parse_a1i() {
    this.subject.parse("a 1 i", this.sender1);
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public void parse_c() {
    this.subject.parse("c", this.sender1);
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  public void parse_k1i() {
    this.subject.parse("k 1 i", this.sender1);
  }

  @Setup
  public void setup() {
    this.subject = new CommandDispatcher();
    this.subject.register(literal("a")
      .then(literal("1")
        .then(literal("i")
          .executes(c -> CommandResult.empty()))
        .then(literal("ii")
          .executes(c -> CommandResult.empty())))
      .then(
        literal("2")
          .then(literal("i")
            .executes(c -> CommandResult.empty()))
          .then(literal("ii")
            .executes(c -> CommandResult.empty()))));
    this.subject.register(literal("b")
      .then(literal("1")
        .executes(c -> CommandResult.empty())));
    this.subject.register(literal("c")
      .executes(c -> CommandResult.empty()));
    this.subject.register(literal("d")
      .requires(s -> false)
      .executes(c -> CommandResult.empty()));
    this.subject.register(literal("e")
      .executes(c -> CommandResult.empty())
      .then(literal("1")
        .executes(c -> CommandResult.empty())
        .then(literal("i")
          .executes(c -> CommandResult.empty()))
        .then(literal("ii")
          .executes(c -> CommandResult.empty()))));
    this.subject.register(literal("f")
      .then(literal("1")
        .then(literal("i")
          .executes(c -> CommandResult.empty()))
        .then(literal("ii")
          .executes(c -> CommandResult.empty())
          .requires(s -> false)))
      .then(literal("2")
        .then(literal("i")
          .executes(c -> CommandResult.empty())
          .requires(s -> false))
        .then(literal("ii")
          .executes(c -> CommandResult.empty()))));
    this.subject.register(literal("g")
      .executes(c -> CommandResult.empty())
      .then(literal("1")
        .then(literal("i")
          .executes(c -> CommandResult.empty()))));
    final var h = literal("h")
      .executes(c -> CommandResult.empty())
      .then(literal("1")
        .then(literal("i")
          .executes(c -> CommandResult.empty())))
      .then(literal("2")
        .then(literal("i")
          .then(literal("ii")
            .executes(c -> CommandResult.empty()))))
      .then(literal("3")
        .executes(c -> CommandResult.empty()))
      .build();
    this.subject.register(h);
    this.subject.register(literal("i")
      .executes(c -> CommandResult.empty())
      .then(literal("1")
        .executes(c -> CommandResult.empty()))
      .then(literal("2")
        .executes(c -> CommandResult.empty())));
    this.subject.register(literal("j")
      .redirect(this.subject.getRoot()));
    this.subject.register(literal("k")
      .redirect(h));
  }
}
