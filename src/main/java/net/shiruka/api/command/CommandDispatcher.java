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

import com.google.common.base.Preconditions;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.shiruka.api.command.builder.LiteralBuilder;
import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.context.CommandContextBuilder;
import net.shiruka.api.command.context.ParseResults;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import net.shiruka.api.command.sender.CommandSender;
import net.shiruka.api.command.suggestion.Suggestions;
import net.shiruka.api.command.tree.RootNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents command dispatchers.
 */
public final class CommandDispatcher {

  /**
   * the argument separator.
   */
  private static final String ARGUMENT_SEPARATOR = " ";

  /**
   * the argument separator character.
   */
  private static final char ARGUMENT_SEPARATOR_CHAR = ' ';

  /**
   * the usage optional close.
   */
  private static final String USAGE_OPTIONAL_CLOSE = "]";

  /**
   * the usage optional open.
   */
  private static final String USAGE_OPTIONAL_OPEN = "[";

  /**
   * the usage or.
   */
  private static final String USAGE_OR = "|";

  /**
   * the usage required close.
   */
  private static final String USAGE_REQUIRED_CLOSE = ")";

  /**
   * the usage required open.
   */
  private static final String USAGE_REQUIRED_OPEN = "(";

  /**
   * the root.
   */
  @NotNull
  private final RootNode root;

  /**
   * the result consumer.
   */
  @NotNull
  private ResultConsumer resultConsumer = (context, success, result) -> {
  };

  /**
   * ctor.
   *
   * @param root the root.
   */
  private CommandDispatcher(@NotNull final RootNode root) {
    this.root = root;
  }

  /**
   * ctor.
   */
  public CommandDispatcher() {
    this(new RootNode());
  }

  /**
   * gets suggestions for a parsed input string on what comes next.
   *
   * @param parse the parse to collect.
   *
   * @return a future that will eventually resolve into a {@link Suggestions} object.
   */
  @NotNull
  public static CompletableFuture<Suggestions> getCompletionSuggestions(@NotNull final ParseResults parse) {
    return CommandDispatcher.getCompletionSuggestions(parse, parse.getReader().getTotalLength());
  }

  /**
   * collects completion suggestions.
   *
   * @param parse the parse to collect.
   * @param cursor the cursor to collect.
   *
   * @return collection of completion suggestions.
   */
  @NotNull
  public static CompletableFuture<Suggestions> getCompletionSuggestions(@NotNull final ParseResults parse,
                                                                        final int cursor) {
    final var context = parse.getBuilder();
    final var nodeBeforeCursor = context.findSuggestionContext(cursor);
    final var parent = nodeBeforeCursor.getParent();
    final var start = Math.min(nodeBeforeCursor.getStartPos(), cursor);
    final var fullInput = parse.getReader().getText();
    final var truncatedInput = fullInput.substring(0, cursor);
    //noinspection unchecked
    final CompletableFuture<Suggestions>[] futures = new CompletableFuture[parent.getChildren().size()];
    var i = 0;
    for (final var node : parent.getChildren()) {
      CompletableFuture<Suggestions> future = Suggestions.empty();
      try {
        future = node.suggestions(context.build(truncatedInput), Suggestions.builder(truncatedInput, start));
      } catch (final CommandSyntaxException ignored) {
      }
      futures[i++] = future;
    }
    return CompletableFuture.allOf(futures)
      .handle((voidResult, exception) ->
        Suggestions.merge(fullInput, Stream.of(futures)
          .filter(future -> !future.isCompletedExceptionally())
          .map(CompletableFuture::join)
          .collect(Collectors.toCollection(ArrayList::new))));
  }

  /**
   * checks if the given {@code node} is executable.
   *
   * @param node the node to check.
   *
   * @return {@code true} if the node is executable.
   */
  private static boolean isNodeExecutable(@NotNull CommandNode node) {
    while (node.getCommand() == null && node.getDefaultNode().isPresent()) {
      node = node.getDefaultNode().get();
    }
    return node.getCommand() != null;
  }

  /**
   * parses the given node.
   *
   * @param node the node to parse.
   * @param originalReader the original reader to parse.
   * @param builder the builder to parse.
   *
   * @return the parse result.
   */
  @NotNull
  private static ParseResults parseNodes(@NotNull final CommandNode node, @NotNull final TextReader originalReader,
                                         @NotNull final CommandContextBuilder builder) {
    final var sender = builder.getSender();
    final var errors = new AtomicReference<Map<CommandNode, CommandSyntaxException>>();
    final var potentials = new AtomicReference<List<ParseResults>>();
    final var cursor = originalReader.getCursor();
    for (final var child : node.getRelevantNodes(originalReader)) {
      if (!child.canUse(sender)) {
        continue;
      }
      final var context = builder.copy();
      final var reader = new TextReader(originalReader);
      try {
        try {
          child.parse(reader, context);
        } catch (final RuntimeException ex) {
          throw CommandException.DISPATCHER_PARSE_EXCEPTION.createWithContext(reader, ex.getMessage());
        }
        if (reader.canRead() &&
          reader.peek() != CommandDispatcher.ARGUMENT_SEPARATOR_CHAR) {
          throw CommandException.DISPATCHER_EXPECTED_ARGUMENT_SEPARATOR.createWithContext(reader);
        }
      } catch (final CommandSyntaxException ex) {
        if (errors.get() == null) {
          errors.set(new LinkedHashMap<>());
        }
        errors.get().put(child, ex);
        reader.setCursor(cursor);
        continue;
      }
      context.withCommand(child.getCommand());
      if (reader.canRead(child.getRedirect() == null ? 2 : 1) || child.getDefaultNode().isPresent()) {
        reader.skip();
        if (child.getRedirect() != null) {
          final var parse = CommandDispatcher.parseNodes(child.getRedirect(), reader,
            new CommandContextBuilder(reader.getCursor(), child.getRedirect(), sender));
          context.withChild(parse.getBuilder());
          return new ParseResults(context, parse.getReader(), parse.getExceptions());
        }
        final var parse = CommandDispatcher.parseNodes(child, reader, context);
        if (potentials.get() == null) {
          potentials.set(new ArrayList<>(1));
        }
        potentials.get().add(parse);
      } else {
        if (potentials.get() == null) {
          potentials.set(new ArrayList<>(1));
        }
        potentials.get().add(new ParseResults(context, reader, Collections.emptyMap()));
      }
    }
    if (potentials.get() == null) {
      return new ParseResults(builder, originalReader, errors.get() == null
        ? Collections.emptyMap()
        : errors.get());
    }
    if (potentials.get().size() <= 1) {
      return potentials.get().get(0);
    }
    potentials.get().sort((a, b) -> {
      if (!a.getReader().canRead() && b.getReader().canRead()) {
        return -1;
      }
      if (a.getReader().canRead() && !b.getReader().canRead()) {
        return 1;
      }
      if (a.getExceptions().isEmpty() && !b.getExceptions().isEmpty()) {
        return -1;
      }
      if (!a.getExceptions().isEmpty() && b.getExceptions().isEmpty()) {
        return 1;
      }
      return 0;
    });
    return potentials.get().get(0);
  }

  /**
   * executes the given input with the sender.
   *
   * @param command the command to execute.
   * @param sender the sender to execute
   *
   * @return executed command result.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  @NotNull
  public CommandResult execute(@NotNull final String command, @NotNull final CommandSender sender)
    throws CommandSyntaxException {
    return this.execute(new TextReader(command), sender);
  }

  /**
   * executes the given parse result.
   *
   * @param parse the parse to execute.
   *
   * @return the executed command result.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  @NotNull
  public CommandResult execute(@NotNull final ParseResults parse) throws CommandSyntaxException {
    if (parse.getReader().canRead()) {
      if (parse.getExceptions().size() == 1) {
        throw parse.getExceptions().values().iterator().next();
      }
      if (parse.getBuilder().getRange().isEmpty()) {
        throw CommandException.DISPATCHER_UNKNOWN_COMMAND.createWithContext(parse.getReader());
      }
      throw CommandException.DISPATCHER_UNKNOWN_ARGUMENT.createWithContext(parse.getReader());
    }
    final var result = CommandResult.empty();
    final var successfulForks = CommandResult.empty();
    var forked = false;
    var foundCommand = false;
    final var command = parse.getReader().getText();
    final var original = parse.getBuilder().build(command);
    var contexts = Collections.singletonList(original);
    final var next = new AtomicReference<List<CommandContext>>();
    while (contexts != null) {
      for (final var context : contexts) {
        final var child = context.getChild();
        if (child != null) {
          forked |= context.isFork();
          if (!child.hasNodes()) {
            continue;
          }
          foundCommand = true;
          final var modifier = context.getRedirectModifier();
          if (modifier == null) {
            if (next.get() == null) {
              next.set(new ArrayList<>(1));
            }
            next.get().add(child.copyFor(context.getSender()));
            continue;
          }
          try {
            final var results = modifier.apply(context);
            if (!results.isEmpty()) {
              if (next.get() == null) {
                next.set(new ArrayList<>(results.size()));
              }
              results.forEach(sender -> next.get().add(child.copyFor(sender)));
            }
          } catch (final CommandSyntaxException ex) {
            this.resultConsumer.onCommandComplete(context, false, CommandResult.empty());
            if (!forked) {
              throw ex;
            }
          }
          continue;
        }
        if (context.getCommand() == null) {
          continue;
        }
        foundCommand = true;
        try {
          final var value = context.getCommand().run(context);
          result.merge(value);
          this.resultConsumer.onCommandComplete(context, true, value);
          successfulForks.merge(CommandResult.of());
        } catch (final CommandSyntaxException ex) {
          this.resultConsumer.onCommandComplete(context, false, CommandResult.empty());
          if (!forked) {
            throw ex;
          }
        }
      }
      contexts = next.get();
      next.set(null);
    }
    if (!foundCommand) {
      this.resultConsumer.onCommandComplete(original, false, CommandResult.empty());
      throw CommandException.DISPATCHER_UNKNOWN_COMMAND.createWithContext(parse.getReader());
    }
    return forked ? successfulForks : result;
  }

  /**
   * executes the given input with the sender.
   *
   * @param input the input to execute.
   * @param sender the sender to execute.
   *
   * @return the executed command result.
   *
   * @throws CommandSyntaxException if something is wrong in the command syntax.
   */
  @NotNull
  public CommandResult execute(@NotNull final TextReader input, @NotNull final CommandSender sender)
    throws CommandSyntaxException {
    return this.execute(this.parse(input, sender));
  }

  /**
   * finds a node by its path
   *
   * @param path a generated path to a node.
   *
   * @return the node at the given path, or {@code null} if not found.
   */
  @NotNull
  public Optional<CommandNode> findNode(@NotNull final Collection<String> path) {
    CommandNode node = this.root;
    for (final var name : path) {
      final var optional = node.getChild(name);
      if (optional.isEmpty()) {
        return Optional.empty();
      }
      node = optional.get();
    }
    return Optional.of(node);
  }

  /**
   * gets all possible executable commands following the given node.
   *
   * @param node target node to get child usage strings for.
   * @param sender a custom "source" object, usually representing the originator of this command.
   * @param restricted if true, commands that the {@code source} cannot access will not be mentioned.
   *
   * @return array of full usage strings under the target node.
   */
  @NotNull
  public String[] getAllUsage(@NotNull final CommandNode node, @NotNull final CommandSender sender,
                              final boolean restricted) {
    final var result = new ArrayList<String>();
    this.getAllUsage(node, sender, result, "", restricted);
    return result.toArray(new String[0]);
  }

  /**
   * finds a valid path to a given node on the command tree.
   *
   * @param target the target node you are finding a path for.
   *
   * @return a path to the resulting node, or an empty list if it was not found.
   */
  @NotNull
  public Collection<String> getPath(@NotNull final CommandNode target) {
    final var nodes = new ArrayList<List<CommandNode>>();
    this.addPaths(this.root, nodes, new ArrayList<>());
    return nodes.stream()
      .filter(list -> list.get(list.size() - 1) == target)
      .findFirst()
      .map(list -> (List<String>) list.stream()
        .filter(node -> node != this.root)
        .map(CommandNode::getName)
        .collect(Collectors.toCollection(() -> new ArrayList<>(list.size()))))
      .orElse(Collections.emptyList());
  }

  /**
   * obtains the result consumer.
   *
   * @return result consumer.
   */
  @NotNull
  public ResultConsumer getResultConsumer() {
    return this.resultConsumer;
  }

  /**
   * sets the result consumer.
   *
   * @param resultConsumer the resultConsumer to set.
   */
  public void setResultConsumer(@NotNull final ResultConsumer resultConsumer) {
    this.resultConsumer = resultConsumer;
  }

  /**
   * gets the root of this command tree.
   *
   * @return root of the command tree.
   */
  @NotNull
  public RootNode getRoot() {
    return this.root;
  }

  /**
   * gets the possible executable commands from a specified node.
   *
   * @param node target node to get child usage strings for.
   * @param sender a custom sender object, usually representing the originator of this command.
   *
   * @return array of full usage strings under the target node.
   */
  @NotNull
  public Map<CommandNode, String> getSmartUsage(@NotNull final CommandNode node, @NotNull final CommandSender sender) {
    final var result = new LinkedHashMap<CommandNode, String>();
    final var optional = node.getCommand() != null;
    node.getChildren().forEach(child -> {
      final var usage = this.getSmartUsage(child, sender, optional, false);
      if (usage != null) {
        result.put(child, usage);
      }
    });
    return result;
  }

  /**
   * parses the given input into {@link ParseResults}.
   *
   * @param command the command to parse.
   * @param sender the sender to parse.
   *
   * @return parsing result.
   */
  @NotNull
  public ParseResults parse(@NotNull final String command, @NotNull final CommandSender sender) {
    return this.parse(new TextReader(command), sender);
  }

  /**
   * parses a given command.
   *
   * @param command a command string to parse.
   * @param sender a custom "sender" object, usually representing the originator of this command.
   *
   * @return the result of parsing this command.
   */
  @NotNull
  public ParseResults parse(@NotNull final TextReader command, @NotNull final CommandSender sender) {
    return CommandDispatcher.parseNodes(this.root, command,
      new CommandContextBuilder(command.getCursor(), this.root, sender));
  }

  /**
   * registers the given commands.
   *
   * @param commands the commands to registers.
   */
  public void register(@NotNull final CommandNode... commands) {
    Stream.of(commands)
      .filter(commandNode -> {
        Preconditions.checkArgument(!commandNode.isDefaultNode(), "You cannot register default nodes!");
        return true;
      })
      .forEach(this.root::addChild);
  }

  /**
   * registers the given builders.
   *
   * @param builders the builders to registers.
   */
  public void register(@NotNull final LiteralBuilder... builders) {
    this.register(Arrays.stream(builders)
      .map(LiteralBuilder::build)
      .toArray(CommandNode[]::new));
  }

  /**
   * unregisters the given commands.
   *
   * @param commands the commands to unregister.
   */
  public void unregister(@NotNull final String... commands) {
    Arrays.asList(commands).forEach(this.root::removeChild);
  }

  /**
   * unregister the given commands.
   *
   * @param commands the commands to unregister.
   */
  public void unregister(@NotNull final CommandNode... commands) {
    this.unregister(Arrays.stream(commands)
      .map(CommandNode::getName)
      .toArray(String[]::new));
  }

  /**
   * adds the given node to paths.
   *
   * @param node the node to add.
   * @param result the result to add.
   * @param parents the parents to add.
   */
  private void addPaths(@NotNull final CommandNode node, @NotNull final List<List<CommandNode>> result,
                        @NotNull final List<CommandNode> parents) {
    final var current = new ArrayList<>(parents);
    current.add(node);
    result.add(current);
    node.getChildren().forEach(child -> this.addPaths(child, result, current));
  }

  /**
   * collects and adds all usages to the given result.
   *
   * @param node the node to collect.
   * @param sender the sender to collect.
   * @param result the result to collect.
   * @param prefix the prefix to collect.
   * @param restricted the restricted to collect.
   */
  private void getAllUsage(@NotNull final CommandNode node, @NotNull final CommandSender sender,
                           @NotNull final List<String> result, @NotNull final String prefix, final boolean restricted) {
    if (restricted && !node.canUse(sender)) {
      return;
    }
    if (CommandDispatcher.isNodeExecutable(node)) {
      result.add(prefix);
    }
    if (node.getRedirect() != null) {
      final var redirect = node.getRedirect() == this.root ? "..." : "-> " + node.getRedirect().getUsage();
      result.add(prefix.isEmpty()
        ? node.getUsage() + CommandDispatcher.ARGUMENT_SEPARATOR + redirect
        : prefix + CommandDispatcher.ARGUMENT_SEPARATOR + redirect);
    } else if (!node.getChildren().isEmpty()) {
      node.getChildren().forEach(child -> this.getAllUsage(child, sender, result, prefix.isEmpty()
        ? child.getUsage()
        : prefix + CommandDispatcher.ARGUMENT_SEPARATOR + child.getUsage(), restricted));
    }
  }

  /**
   * obtains the smart usage of the given node.
   *
   * @param node the node to get.
   * @param sender the sender to get.
   * @param optional the optional to get.
   * @param deep the deep to get.
   *
   * @return the smart usage.
   */
  @Nullable
  private String getSmartUsage(@NotNull final CommandNode node, @NotNull final CommandSender sender,
                               final boolean optional, final boolean deep) {
    if (!node.canUse(sender)) {
      return null;
    }
    final String self = optional
      ? CommandDispatcher.USAGE_OPTIONAL_OPEN + node.getUsage() + CommandDispatcher.USAGE_OPTIONAL_CLOSE
      : node.getUsage();
    final var childOptional = CommandDispatcher.isNodeExecutable(node);
    final var open = childOptional
      ? CommandDispatcher.USAGE_OPTIONAL_OPEN
      : CommandDispatcher.USAGE_REQUIRED_OPEN;
    final var close = childOptional
      ? CommandDispatcher.USAGE_OPTIONAL_CLOSE
      : CommandDispatcher.USAGE_REQUIRED_CLOSE;
    if (deep) {
      return self;
    }
    if (node.getRedirect() != null) {
      final var redirect = node.getRedirect() == this.root
        ? "..."
        : "-> " + node.getRedirect().getUsage();
      return self + CommandDispatcher.ARGUMENT_SEPARATOR + redirect;
    }
    final var children = node.getChildren().stream()
      .filter(c -> c.canUse(sender))
      .collect(Collectors.toList());
    if (children.size() == 1) {
      final var usage = this.getSmartUsage(children.iterator().next(), sender, childOptional, childOptional);
      if (usage != null) {
        return self + CommandDispatcher.ARGUMENT_SEPARATOR + usage;
      }
      return self;
    }
    if (children.size() <= 1) {
      return self;
    }
    final var childUsage = children.stream()
      .map(child -> this.getSmartUsage(child, sender, childOptional, true))
      .filter(Objects::nonNull)
      .collect(Collectors.toCollection(LinkedHashSet::new));
    if (childUsage.size() == 1) {
      final var usage = childUsage.iterator().next();
      return self + CommandDispatcher.ARGUMENT_SEPARATOR + (childOptional
        ? CommandDispatcher.USAGE_OPTIONAL_OPEN + usage + CommandDispatcher.USAGE_OPTIONAL_CLOSE
        : usage);
    }
    if (childUsage.size() <= 1) {
      return self;
    }
    final var builder = new StringBuilder(open);
    final var count = new AtomicInteger();
    children.forEach(child -> {
      if (count.get() > 0) {
        builder.append(CommandDispatcher.USAGE_OR);
      }
      builder.append(child.getUsage());
      count.getAndIncrement();
    });
    if (count.get() <= 0) {
      return self;
    }
    builder.append(close);
    return self + CommandDispatcher.ARGUMENT_SEPARATOR + builder.toString();
  }
}
