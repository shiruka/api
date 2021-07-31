package net.shiruka.api.event.events.server;

import java.util.List;
import net.shiruka.api.event.events.Cancellable;
import net.shiruka.api.event.events.CommandSenderEvent;
import org.jetbrains.annotations.NotNull;

/**
 * allows plugins to compute tab completion results asynchronously.
 */
public interface AsyncTabCompleteEvent extends Cancellable, CommandSenderEvent {

  /**
   * obtains the completions.
   *
   * @return completions.
   */
  @NotNull
  List<String> getCompletions();

  /**
   * sets the completions.
   *
   * @param completions the completions to set.
   */
  void setCompletions(@NotNull List<String> completions);

  /**
   * obtains the text.
   *
   * @return text.
   */
  @NotNull
  String getText();

  @Override
  default boolean isAsync() {
    return true;
  }

  /**
   * checks if the event is handled.
   *
   * @return {@code true} if completions considered handled.
   */
  boolean isHandled();

  /**
   * sets the handled.
   *
   * @param handled the handled to set.
   */
  void setHandled(boolean handled);
}
