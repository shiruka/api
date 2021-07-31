package net.shiruka.api.command.sender;

import org.jetbrains.annotations.NotNull;

/**
 * a class that represents buffered command senders.
 */
public final class BufferedCommandSender implements MessageCommandSender {

  /**
   * the buffer.
   */
  private final StringBuffer buffer = new StringBuffer();

  /**
   * obtains the message in the {@link #buffer}.
   *
   * @return message in the buffer.
   */
  @NotNull
  public String getBuffer() {
    return this.buffer.toString();
  }

  /**
   * clears the buffer.
   */
  public void reset() {
    this.buffer.setLength(0);
  }

  @Override
  public void sendMessage(@NotNull final String message) {
    this.buffer.append(message);
    this.buffer.append("\n");
  }
}
