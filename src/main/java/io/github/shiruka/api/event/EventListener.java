package io.github.shiruka.api.event;

import net.kyori.event.EventSubscriber;
import net.kyori.event.PostOrders;
import org.apache.commons.lang3.function.FailableConsumer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents event listener.
 *
 * @param run the run.
 * @param postOrder the post order.
 * @param acceptsCancelled the accepts cancelled.
 * @param <E> type of the event class.
 *
 * @see PostOrders
 */
public record EventListener<E extends Event>(
  @NotNull FailableConsumer<E, Throwable> run,
  int postOrder,
  boolean acceptsCancelled
)
  implements EventSubscriber<E> {
  /**
   * ctor.
   *
   * @param run the run.
   * @param postOrder the post order.
   *
   * @see PostOrders
   */
  public EventListener(
    @NotNull final FailableConsumer<E, Throwable> run,
    final int postOrder
  ) {
    this(run, postOrder, true);
  }

  /**
   * ctor.
   *
   * @param run the run.
   *
   * @see PostOrders#NORMAL
   */
  public EventListener(@NotNull final FailableConsumer<E, Throwable> run) {
    this(run, PostOrders.NORMAL);
  }

  @Override
  public void on(@NonNull final E event) throws Throwable {
    this.run.accept(event);
  }
}
