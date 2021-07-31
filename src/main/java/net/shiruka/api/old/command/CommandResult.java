package net.shiruka.api.old.command;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine command results.
 *
 * @todo #1:15m Implement succeed and failed correctly method.
 */
public interface CommandResult {

  /**
   * an empty command result instance.
   *
   * @return empty result.
   */
  @NotNull
  static CommandResult empty() {
    return CommandResult.of(0);
  }

  /**
   * a command result.
   *
   * @return command result.
   */
  @NotNull
  static CommandResult of() {
    return CommandResult.of(1);
  }

  /**
   * a command result.
   *
   * @param amount the amount to create
   *
   * @return command result.
   */
  @NotNull
  static CommandResult of(final int amount) {
    return new SimpleCommandResult(amount);
  }

  /**
   * merges the given result.
   *
   * @param result the result to merge.
   *
   * @return a new merged result.
   */
  @NotNull
  CommandResult merge(@NotNull CommandResult result);

  /**
   * obtains the result.
   *
   * @return result.
   */
  int result();

  /**
   * a simple command result implementation.
   */
  final class SimpleCommandResult implements CommandResult {

    /**
     * the result.
     */
    private int result;

    /**
     * ctor.
     *
     * @param result the result.
     */
    public SimpleCommandResult(final int result) {
      this.result = result;
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.result);
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null || this.getClass() != obj.getClass()) {
        return false;
      }
      final var that = (SimpleCommandResult) obj;
      return this.result == that.result;
    }

    @Override
    public String toString() {
      return "SimpleCommandResult{" +
        "result=" + this.result +
        '}';
    }

    @NotNull
    @Override
    public CommandResult merge(@NotNull final CommandResult result) {
      this.result += result.result();
      return this;
    }

    @Override
    public int result() {
      return this.result;
    }
  }
}
