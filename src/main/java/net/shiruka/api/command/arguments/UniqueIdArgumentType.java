package net.shiruka.api.command.arguments;

import java.util.UUID;
import java.util.regex.Pattern;
import net.shiruka.api.command.ArgumentType;
import net.shiruka.api.command.TextReader;
import net.shiruka.api.command.exceptions.CommandSyntaxException;
import org.jetbrains.annotations.NotNull;

/**
 * a simple {@link UUID} argument type implementation for {@link ArgumentType}.
 */
public final class UniqueIdArgumentType implements ArgumentType<UUID> {

  /**
   * unique id pattern.
   */
  private static final Pattern PATTERN = Pattern.compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");

  /**
   * the replace constant.
   */
  private static final String REPLACE = "$1-$2-$3-$4-$5";

  @NotNull
  @Override
  public UUID parse(@NotNull final TextReader reader) throws CommandSyntaxException {
    final var replaced = UniqueIdArgumentType.PATTERN.matcher(reader.readUnquotedText())
      .replaceFirst(UniqueIdArgumentType.REPLACE);
    return UUID.fromString(replaced);
  }
}
