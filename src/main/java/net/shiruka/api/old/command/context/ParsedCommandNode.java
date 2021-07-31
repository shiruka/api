package net.shiruka.api.old.command.context;

import java.util.Objects;
import net.shiruka.api.old.command.CommandNode;
import net.shiruka.api.old.command.TextRange;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents parsed command nodes.
 */
public final class ParsedCommandNode {

  /**
   * the node.
   */
  @NotNull
  private final CommandNode node;

  /**
   * the range.
   */
  @NotNull
  private final TextRange range;

  /**
   * ctor.
   *
   * @param node the node.
   * @param range the range.
   */
  public ParsedCommandNode(@NotNull final CommandNode node, @NotNull final TextRange range) {
    this.node = node;
    this.range = range;
  }

  /**
   * obtains the node.
   *
   * @return node.
   */
  @NotNull
  public CommandNode getNode() {
    return this.node;
  }

  /**
   * obtains the range.
   *
   * @return range.
   */
  @NotNull
  public TextRange getRange() {
    return this.range;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.node, this.range);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    final var that = (ParsedCommandNode) obj;
    return Objects.equals(this.node, that.node) &&
      Objects.equals(this.range, that.range);
  }

  @Override
  public String toString() {
    return this.node + "@" + this.range;
  }
}
