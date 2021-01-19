package io.github.shiruka.api.command.benchmarks;

import static io.github.shiruka.api.command.Commands.literal;
import io.github.shiruka.api.command.CommandDispatcher;
import io.github.shiruka.api.command.CommandResult;
import io.github.shiruka.api.command.sender.CommandSender;
import io.github.shiruka.api.command.SCommandSender;
import java.util.concurrent.TimeUnit;
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
