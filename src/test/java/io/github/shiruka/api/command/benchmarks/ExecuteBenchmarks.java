// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package io.github.shiruka.api.command.benchmarks;

import static io.github.shiruka.api.command.Commands.literal;
import com.google.common.collect.Lists;
import io.github.shiruka.api.command.CommandDispatcher;
import io.github.shiruka.api.command.CommandResult;
import io.github.shiruka.api.command.CommandSender;
import io.github.shiruka.api.command.context.ParseResults;
import io.github.shiruka.api.command.exceptions.CommandSyntaxException;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ExecuteBenchmarks {

  private final CommandSender sender1 = new CommandSender() {
    @NotNull
    @Override
    public String getName() {
      return "null1";
    }

    @Override
    public void sendMessage(@NotNull final String message) {
      System.out.println(message);
    }
  };

  private final CommandSender sender2 = new CommandSender() {
    @NotNull
    @Override
    public String getName() {
      return "null2";
    }

    @Override
    public void sendMessage(@NotNull final String message) {
      System.out.println(message);
    }
  };

  private final CommandSender sender3 = new CommandSender() {
    @NotNull
    @Override
    public String getName() {
      return "null3";
    }

    @Override
    public void sendMessage(@NotNull final String message) {
      System.out.println(message);
    }
  };

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
