package io.github.shiruka.api.event;

import com.google.common.base.Preconditions;
import io.github.shiruka.api.event.events.Event;
import java.util.Collections;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * an interface ot determine post results of {@link EventController#call(Event)} call.
 */
public interface PostResult {
  /**
   * marks that exceptions were thrown by subscribers.
   *
   * @param exceptions the exceptions that were thrown.
   *
   * @return a {@code this} indicating failure.
   */
  @NotNull
  static PostResult failure(
    @NotNull final Map<EventSubscriber, Throwable> exceptions
  ) {
    Preconditions.checkState(!exceptions.isEmpty(), "no exceptions present");
    return new PostResult.Failure(exceptions);
  }

  /**
   * marks that no exceptions were thrown by subscribers.
   *
   * @return a {@code this} indicating success.
   */
  @NotNull
  static PostResult success() {
    return Success.INSTANCE;
  }

  /**
   * obtains the exceptions.
   *
   * @return exceptions.
   */
  @NotNull
  Map<EventSubscriber, Throwable> exceptions();

  /**
   * raises a {@link CompositeException} if the posting was not {@link #wasSuccessful() successful}.
   *
   * @throws CompositeException if posting was not successful.
   */
  void raise();

  /**
   * gets if the {@link EventController#call(Event)} call was successful.
   *
   * @return if the call was successful.
   */
  boolean wasSuccessful();

  /**
   * exception encapsulating a combined {@link #failure(Map) failure}.
   */
  @Getter
  @Accessors(fluent = true)
  final class CompositeException extends RuntimeException {

    /**
     * the result.
     */
    @NotNull
    private final PostResult result;

    /**
     * ctor.
     *
     * @param result the result
     */
    CompositeException(@NotNull final PostResult result) {
      super("Exceptions occurred whilst posting to subscribers");
      this.result = result;
    }

    /**
     * prints all of the stack traces involved in the composite exception.
     *
     * @see Exception#printStackTrace()
     */
    public void printAllStackTraces() {
      this.printStackTrace();
      for (final var throwable : this.result.exceptions().values()) {
        throwable.printStackTrace();
      }
    }
  }

  /**
   * a record class that represents failure for {@link PostResult}.
   */
  @Getter
  @Accessors(fluent = true)
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  final class Failure implements PostResult {

    /**
     * the exceptions.
     */
    @NotNull
    private final Map<EventSubscriber, Throwable> exceptions;

    @Override
    public void raise() throws CompositeException {
      throw new CompositeException(this);
    }

    @Override
    public boolean wasSuccessful() {
      return this.exceptions.isEmpty();
    }

    @Override
    public String toString() {
      return "PostResult.failure(" + this.exceptions + ")";
    }
  }

  /**
   * a class that represents success for {@link PostResult}.
   */
  final class Success implements PostResult {

    /**
     * the instance.
     */
    private static final Success INSTANCE = new Success();

    @NotNull
    @Override
    public Map<EventSubscriber, Throwable> exceptions() {
      return Collections.emptyMap();
    }

    @Override
    public void raise() {}

    @Override
    public boolean wasSuccessful() {
      return true;
    }

    @Override
    public String toString() {
      return "PostResult.success()";
    }
  }
}
