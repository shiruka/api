package net.shiruka.api.old.command.context;

import net.shiruka.api.old.command.CommandNode;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents suggestion context.
 */
public final class SuggestionContext {

  /**
   * the parent.
   */
  @NotNull
  private final CommandNode parent;

  /**
   * the start position.
   */
  private final int startPos;

  /**
   * ctor.
   *
   * @param parent the parent.
   * @param startPos the start position.
   */
  public SuggestionContext(@NotNull final CommandNode parent, final int startPos) {
    this.parent = parent;
    this.startPos = startPos;
  }

  /**
   * obtains the parent.
   *
   * @return parent.
   */
  @NotNull
  public CommandNode getParent() {
    return this.parent;
  }

  /**
   * obtains the start position.
   *
   * @return start position.
   */
  public int getStartPos() {
    return this.startPos;
  }
}
