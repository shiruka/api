package net.shiruka.api.old.command.context;

import java.util.Collections;
import java.util.Map;
import net.shiruka.api.old.command.CommandNode;
import net.shiruka.api.old.command.TextReader;
import net.shiruka.api.old.command.exceptions.CommandSyntaxException;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents parse results.
 */
public final class ParseResults {

  /**
   * the builder.
   */
  @NotNull
  private final CommandContextBuilder builder;

  /**
   * the exceptions.
   */
  @NotNull
  private final Map<CommandNode, CommandSyntaxException> exceptions;

  /**
   * the reader.
   */
  @NotNull
  private final TextReader reader;

  /**
   * ctor.
   *
   * @param builder the builder.
   * @param reader the reader.
   * @param exceptions the exceptions.
   */
  public ParseResults(@NotNull final CommandContextBuilder builder, @NotNull final TextReader reader,
                      @NotNull final Map<CommandNode, CommandSyntaxException> exceptions) {
    this.builder = builder;
    this.reader = reader;
    this.exceptions = Collections.unmodifiableMap(exceptions);
  }

  /**
   * ctor.
   *
   * @param builder the builder.
   */
  public ParseResults(@NotNull final CommandContextBuilder builder) {
    this(builder, new TextReader(""), Collections.emptyMap());
  }

  /**
   * obtains the builder.
   *
   * @return builder.
   */
  @NotNull
  public CommandContextBuilder getBuilder() {
    return this.builder;
  }

  /**
   * obtains the exceptions.
   *
   * @return exceptions.
   */
  @NotNull
  public Map<CommandNode, CommandSyntaxException> getExceptions() {
    return this.exceptions;
  }

  /**
   * obtains the reader.
   *
   * @return reader.
   */
  @NotNull
  public TextReader getReader() {
    return this.reader;
  }
}
