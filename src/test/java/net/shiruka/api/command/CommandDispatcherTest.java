package net.shiruka.api.command;

import static net.shiruka.api.command.CommandException.*;
import static net.shiruka.api.command.CommandResult.of;
import static net.shiruka.api.command.Commands.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import com.google.common.collect.Lists;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import net.shiruka.api.command.sender.CommandSender;
import net.shiruka.api.command.suggestion.Suggestion;
import net.shiruka.api.command.suggestion.Suggestions;
import net.shiruka.api.command.tree.LiteralNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.hamcrest.MockitoHamcrest;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
final class CommandDispatcherTest {

  @Mock
  private Command command;

  @Mock
  private CommandSender source;

  private CommandDispatcher subject;

  @Test
  void parse_noSpaceSeparator() {
    this.subject.register(literal("foo")
      .then(arg("bar", integerArg())
        .executes(this.command)));
    try {
      this.subject.execute("foo$", this.source);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(DISPATCHER_UNKNOWN_COMMAND));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @BeforeEach
  void setUp() throws Exception {
    this.subject = new CommandDispatcher();
    when(this.command.run(any())).thenReturn(CommandResult.of(42));
  }

  @Test
  void testCompletionWithErroredFutureReturnsCompletedFuture() throws CommandSyntaxException {
    final var bar = literal("bar").build();
    final var baz = mock(LiteralNode.class);
    when(baz.getName()).thenReturn("baz");
    when(baz.suggestions(any(), any())).thenAnswer(x -> {
      final var future = new CompletableFuture<Suggestions>();
      future.completeExceptionally(new IllegalArgumentException());
      return future;
    });
    this.subject.register(literal("foo").then(bar).then(baz));
    final var parseResults = this.subject.parse("foo b", this.source);
    final var suggestions = CommandDispatcher.getCompletionSuggestions(parseResults).join();
    final var suggestionCollection = suggestions.getSuggestionList().stream()
      .map(Suggestion::getText)
      .collect(Collectors.toList());
    assertThat(Lists.newArrayList("bar"), is(suggestionCollection));
  }

  @Test
  void testCreateAndExecuteCommand() throws Exception {
    this.subject.register(literal("foo").executes(this.command));
    assertThat(this.subject.execute("foo", this.source), is(CommandResult.of(42)));
    verify(this.command).run(any(CommandContext.class));
  }

  @Test
  void testCreateAndExecuteDefaultCommand() throws Exception {
    this.subject.register(literal("foo")
      .then(defaultLiteral("bar")
        .executes(this.command)));
    assertThat(this.subject.execute("foo", this.source), is(CommandResult.of(42)));
    verify(this.command).run(any(CommandContext.class));
  }

  @Test
  void testCreateAndExecuteOffsetCommand() throws Exception {
    this.subject.register(literal("foo").executes(this.command));
    final var result = new TextReader("/foo");
    result.setCursor(1);
    assertThat(this.subject.execute(result, this.source), is(CommandResult.of(42)));
    verify(this.command).run(any(CommandContext.class));
  }

  @Test
  void testCreateAndMergeCommands() throws Exception {
    this.subject.register(literal("base").then(literal("foo").executes(this.command)));
    this.subject.register(literal("base").then(literal("bar").executes(this.command)));
    assertThat(this.subject.execute("base foo", this.source), is(CommandResult.of(42)));
    assertThat(this.subject.execute("base bar", this.source), is(CommandResult.of(42)));
    verify(this.command, times(2)).run(any(CommandContext.class));
  }

  @Test
  void testExecuteAmbiguiousParentSubcommand() throws Exception {
    final var subCommand = mock(Command.class);
    when(subCommand.run(any())).thenReturn(CommandResult.of(42));
    this.subject.register(literal("test")
      .then(arg("incorrect", integerArg())
        .executes(this.command))
      .then(arg("right", integerArg())
        .then(arg("sub", integerArg())
          .executes(subCommand))));
    assertThat(this.subject.execute("test 1 2", this.source), is(CommandResult.of(42)));
    verify(subCommand).run(any(CommandContext.class));
    verify(this.command, never()).run(any());
  }

  @Test
  void testExecuteAmbiguiousParentSubcommandViaRedirect() throws Exception {
    final var subCommand = mock(Command.class);
    when(subCommand.run(any())).thenReturn(CommandResult.of());
    final var real = literal("test")
      .then(arg("incorrect", integerArg())
        .executes(this.command))
      .then(arg("right", integerArg())
        .then(arg("sub", integerArg())
          .executes(subCommand)))
      .build();
    this.subject.register(real);
    this.subject.register(literal("redirect").redirect(real));
    assertThat(this.subject.execute("redirect 1 2", this.source), is(CommandResult.of()));
    verify(subCommand).run(any(CommandContext.class));
    verify(this.command, never()).run(any());
  }

  @Test
  void testExecuteAmbiguousIncorrectArgument() {
    this.subject.register(literal("foo")
      .executes(this.command)
      .then(literal("bar"))
      .then(literal("baz")));
    try {
      this.subject.execute("foo unknown", this.source);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(DISPATCHER_UNKNOWN_ARGUMENT));
      assertThat(ex.getCursor(), is(4));
    }
  }

  @Test
  void testExecuteEmptyCommand() {
    this.subject.register(literal(""));
    try {
      this.subject.execute("", this.source);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(DISPATCHER_UNKNOWN_COMMAND));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void testExecuteImpermissibleCommand() {
    this.subject.register(literal("foo").requires(s -> false));
    try {
      this.subject.execute("foo", this.source);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(DISPATCHER_UNKNOWN_COMMAND));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void testExecuteIncorrectLiteral() {
    this.subject.register(literal("foo").executes(this.command).then(literal("bar")));
    try {
      this.subject.execute("foo baz", this.source);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(DISPATCHER_UNKNOWN_ARGUMENT));
      assertThat(ex.getCursor(), is(4));
    }
  }

  @Test
  void testExecuteInvalidSubcommand() {
    this.subject.register(literal("foo")
      .then(arg("bar", integerArg()))
      .executes(this.command));
    try {
      this.subject.execute("foo bar", this.source);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(READER_EXPECTED_INT));
      assertThat(ex.getCursor(), is(4));
    }
  }

  @Test
  void testExecuteOrphanedSubcommand() {
    this.subject.register(literal("foo")
      .then(arg("bar", integerArg()))
      .executes(this.command));
    try {
      this.subject.execute("foo 5", this.source);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(DISPATCHER_UNKNOWN_COMMAND));
      assertThat(ex.getCursor(), is(5));
    }
  }

  @Test
  void testExecuteRedirected() throws Exception {
    final var modifier = mock(RedirectModifier.class);
    final var source1 = new SimpleCommandSender();
    final var source2 = new SimpleCommandSender();
    when(modifier.apply(MockitoHamcrest.argThat(hasProperty("sender", is(this.source)))))
      .thenReturn(Lists.newArrayList(source1, source2));
    final var concreteNode = literal("actual")
      .executes(this.command)
      .build();
    this.subject.register(concreteNode);
    final var redirectNode = literal("redirected")
      .fork(this.subject.getRoot(), modifier)
      .build();
    this.subject.register(redirectNode);
    final var input = "redirected actual";
    final var parse = this.subject.parse(input, this.source);
    assertThat(parse.getBuilder().getRange().get(input), equalTo("redirected"));
    assertThat(parse.getBuilder().getNodes().size(), is(1));
    assertThat(parse.getBuilder().getRootNode(), equalTo(this.subject.getRoot()));
    assertThat(parse.getBuilder().getNodes().get(0).getRange(), equalTo(parse.getBuilder().getRange()));
    assertThat(parse.getBuilder().getNodes().get(0).getNode(), is(redirectNode));
    assertThat(parse.getBuilder().getSender(), is(this.source));
    final var parent = parse.getBuilder().getChild();
    assertThat(parent, is(notNullValue()));
    assertThat(parent.getRange().get(input), equalTo("actual"));
    assertThat(parent.getNodes().size(), is(1));
    assertThat(parse.getBuilder().getRootNode(), equalTo(this.subject.getRoot()));
    assertThat(parent.getNodes().get(0).getRange(), equalTo(parent.getRange()));
    assertThat(parent.getNodes().get(0).getNode(), is(concreteNode));
    assertThat(parent.getSender(), is(this.source));
    assertThat(this.subject.execute(parse), is(of(2)));
    verify(this.command).run(MockitoHamcrest.argThat(hasProperty("sender", is(source1))));
    verify(this.command).run(MockitoHamcrest.argThat(hasProperty("sender", is(source2))));
  }

  @Test
  void testExecuteRedirectedMultipleTimes() throws Exception {
    final var concreteNode = literal("actual")
      .executes(this.command)
      .build();
    this.subject.register(concreteNode);
    final var redirectNode = literal("redirected")
      .redirect(this.subject.getRoot())
      .build();
    this.subject.register(redirectNode);
    final var input = "redirected redirected actual";
    final var parse = this.subject.parse(input, this.source);
    assertThat(parse.getBuilder().getRange().get(input), equalTo("redirected"));
    assertThat(parse.getBuilder().getNodes().size(), is(1));
    assertThat(parse.getBuilder().getRootNode(), is(this.subject.getRoot()));
    assertThat(parse.getBuilder().getNodes().get(0).getRange(), equalTo(parse.getBuilder().getRange()));
    assertThat(parse.getBuilder().getNodes().get(0).getNode(), is(redirectNode));
    final var child1 = parse.getBuilder().getChild();
    assertThat(child1, is(notNullValue()));
    assertThat(child1.getRange().get(input), equalTo("redirected"));
    assertThat(child1.getNodes().size(), is(1));
    assertThat(child1.getRootNode(), is(this.subject.getRoot()));
    assertThat(child1.getNodes().get(0).getRange(), equalTo(child1.getRange()));
    assertThat(child1.getNodes().get(0).getNode(), is(redirectNode));
    final var child2 = child1.getChild();
    assertThat(child2, is(notNullValue()));
    assertThat(child2.getRange().get(input), equalTo("actual"));
    assertThat(child2.getNodes().size(), is(1));
    assertThat(child2.getRootNode(), is(this.subject.getRoot()));
    assertThat(child2.getNodes().get(0).getRange(), equalTo(child2.getRange()));
    assertThat(child2.getNodes().get(0).getNode(), is(concreteNode));
    assertThat(this.subject.execute(parse), is(CommandResult.of(42)));
    verify(this.command).run(any(CommandContext.class));
  }

  @Test
  void testExecuteSubcommand() throws Exception {
    final var subCommand = mock(Command.class);
    when(subCommand.run(any())).thenReturn(CommandResult.of());
    this.subject.register(literal("foo")
      .then(literal("a"))
      .then(literal("=")
        .executes(subCommand))
      .then(literal("c"))
      .executes(this.command));
    assertThat(this.subject.execute("foo =", this.source), is(CommandResult.of()));
    verify(subCommand).run(any(CommandContext.class));
  }

  @Test
  void testExecuteUnknownCommand() {
    this.subject.register(literal("bar"));
    this.subject.register(literal("baz"));
    try {
      this.subject.execute("foo", this.source);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(DISPATCHER_UNKNOWN_COMMAND));
      assertThat(ex.getCursor(), is(0));
    }
  }

  @Test
  void testExecuteUnknownSubcommand() {
    this.subject.register(literal("foo").executes(this.command));
    try {
      this.subject.execute("foo bar", this.source);
      fail();
    } catch (final CommandSyntaxException ex) {
      assertThat(ex.getType(), is(DISPATCHER_UNKNOWN_ARGUMENT));
      assertThat(ex.getCursor(), is(4));
    }
  }

  @Test
  void testExecute_invalidOther() throws Exception {
    final var wrongCommand = mock(Command.class);
    this.subject.register(literal("w").executes(wrongCommand));
    this.subject.register(literal("world").executes(this.command));
    assertThat(this.subject.execute("world", this.source), is(CommandResult.of(42)));
    verify(wrongCommand, never()).run(any());
    verify(this.command).run(any());
  }

  @Test
  void testFindNodeDoesntExist() {
    assertThat(this.subject.findNode(Lists.newArrayList("foo", "bar")).isPresent(), is(false));
  }

  @Test
  void testFindNodeExists() {
    final var bar = literal("bar").build();
    this.subject.register(literal("foo").then(bar));
    assertThat(this.subject.findNode(Lists.newArrayList("foo", "bar")).get(), is(bar));
  }

  @Test
  void testGetPath() {
    final var bar = literal("bar").build();
    this.subject.register(literal("foo").then(bar));
    assertThat(this.subject.getPath(bar), equalTo(Lists.newArrayList("foo", "bar")));
  }

  @Test
  void testParseIncompleteArgument() {
    this.subject.register(literal("foo")
      .then(arg("bar", integerArg())
        .executes(this.command)));
    final var parse = this.subject.parse("foo ", this.source);
    assertThat(parse.getReader().getRemaining(), equalTo(" "));
    assertThat(parse.getBuilder().getNodes().size(), is(1));
  }

  @Test
  void testParseIncompleteLiteral() {
    this.subject.register(literal("foo")
      .then(literal("bar")
        .executes(this.command)));
    final var parse = this.subject.parse("foo ", this.source);
    assertThat(parse.getReader().getRemaining(), equalTo(" "));
    assertThat(parse.getBuilder().getNodes().size(), is(1));
  }

  @Test
  void testRegisterDefaultNode() {
    assertThrows(IllegalArgumentException.class, () -> this.subject.register(defaultLiteral("foo")));
  }
}
