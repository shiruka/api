package io.github.shiruka.api.event;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Map;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * encapsulates the outcome of a {@link EventController#call(Event)} call.
 */
public final class PostResult {

  /**
   * succeed result.
   */
  private static final PostResult SUCCESS = new PostResult();

  /**
   * the exceptions.
   */
  @NotNull
  private final Map<EventSubscriber, Throwable> exceptions;

  /**
   * ctor.
   *
   * @param exceptions the exceptions.
   */
  private PostResult(@NotNull final Map<EventSubscriber, Throwable> exceptions) {
    this.exceptions = exceptions;
  }

  /**
   * ctor.
   */
  private PostResult() {
    this(Collections.emptyMap());
  }

  /**
   * marks that exceptions were thrown by subscribers.
   *
   * @param exceptions the exceptions that were thrown.
   *
   * @return a {@code this} indicating failure.
   */
  @NotNull
  public static PostResult failure(@NotNull final Map<EventSubscriber, Throwable> exceptions) {
    Preconditions.checkState(!exceptions.isEmpty(), "no exceptions present");
    return new PostResult(exceptions);
  }

  /**
   * marks that no exceptions were thrown by subscribers.
   *
   * @return a {@code this} indicating success.
   */
  @NotNull
  public static PostResult success() {
    return PostResult.SUCCESS;
  }

  /**
   * raises a {@link CompositeException} if the posting was not {@link #wasSuccessful() successful}.
   *
   * @throws CompositeException if posting was not successful.
   */
  public void raise() {
    if (!this.wasSuccessful()) {
      throw new CompositeException(this);
    }
  }

  @Override
  public String toString() {
    if (this.wasSuccessful()) {
      return "PostResult{type=success}";
    }
    return "PostResult{" +
      "type=failure" +
      "exceptions=" + this.exceptions.values() +
      "}";
  }

  /**
   * gets if the {@link EventController#call(Event)} call was successful.
   *
   * @return if the call was successful.
   */
  private boolean wasSuccessful() {
    return this.exceptions.isEmpty();
  }

  /**
   * exception encapsulating a combined {@link #failure(Map) failure}.
   */
  @Getter
  public static final class CompositeException extends RuntimeException {

    /**
     * the result.
     */
    @NotNull
    private final transient PostResult result;

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
      this.result.exceptions.values()
        .forEach(Throwable::printStackTrace);
    }
  }
}
