package net.shiruka.api.command.tree;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import net.shiruka.api.command.CommandNode;
import net.shiruka.api.command.SimpleCommandSender;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.context.CommandContext;
import net.shiruka.api.command.context.CommandContextBuilder;
import net.shiruka.api.command.suggestion.Suggestions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class RootNodeTest extends AbstractCommandNodeTest {

  private RootNode node;

  @Override
  protected CommandNode getCommandNode() {
    return this.node;
  }

  @BeforeEach
  void setUp() {
    this.node = new RootNode();
  }

  @Test
  void testAddChildNoRoot() {
    assertThrows(UnsupportedOperationException.class, () -> this.node.addChild(new RootNode()));
  }

  @Test
  void testParse() throws Exception {
    final var reader = new TextReader("hello world");
    this.node.parse(reader, new CommandContextBuilder(0, new RootNode(), new SimpleCommandSender()));
    assertThat(reader.getCursor(), is(0));
  }

  @Test
  void testSuggestions() throws Exception {
    final var context = mock(CommandContext.class);
    final var result = this.node.suggestions(context, Suggestions.builder("", 0)).join();
    assertThat(result.isEmpty(), is(true));
  }

  @Test
  void testUsage() {
    assertThat(this.node.getSmartUsage(), is(""));
  }
}
