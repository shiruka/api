package net.shiruka.api.old.text;

import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine messages.
 */
public interface GameMessage extends Text {

  /**
   * adds the given {@code siblings}.
   *
   * @param siblings the siblings to add.
   */
  void addSiblings(@NotNull Text... siblings);

  /**
   * adds the given {@code siblings}.
   *
   * @param siblings the siblings to add.
   */
  default void addSiblings(@NotNull final String... siblings) {
    this.addSiblings(Arrays.stream(siblings).map(s -> (Text) () -> s).toArray(Text[]::new));
  }

  /**
   * obtains the siblings.
   *
   * @return siblings.
   */
  @NotNull
  List<Text> getSiblings();
}
