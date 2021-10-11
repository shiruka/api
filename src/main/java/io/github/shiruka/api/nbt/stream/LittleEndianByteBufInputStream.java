package io.github.shiruka.api.nbt.stream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link ByteBufInputStream}.
 */
public final class LittleEndianByteBufInputStream extends ByteBufInputStream {

  /**
   * the buffer.
   */
  @NotNull
  private final ByteBuf buffer;

  /**
   * ctor.
   *
   * @param buffer the buffer.
   */
  public LittleEndianByteBufInputStream(@NotNull final ByteBuf buffer) {
    super(buffer);
    this.buffer = buffer;
  }

  @Override
  public char readChar() {
    return Character.reverseBytes(this.buffer.readChar());
  }

  @Override
  public double readDouble() {
    return this.buffer.readDoubleLE();
  }

  @Override
  public float readFloat() {
    return this.buffer.readFloatLE();
  }

  @Override
  public int readInt() {
    return this.buffer.readIntLE();
  }

  @Override
  public long readLong() {
    return this.buffer.readLongLE();
  }

  @Override
  public short readShort() {
    return this.buffer.readShortLE();
  }

  @Override
  public int readUnsignedShort() {
    return this.buffer.readUnsignedShortLE();
  }
}
