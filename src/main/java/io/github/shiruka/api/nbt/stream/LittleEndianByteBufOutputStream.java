package io.github.shiruka.api.nbt.stream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link ByteBufOutputStream}.
 */
public final class LittleEndianByteBufOutputStream extends ByteBufOutputStream {

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
  public LittleEndianByteBufOutputStream(@NotNull final ByteBuf buffer) {
    super(buffer);
    this.buffer = buffer;
  }

  @Override
  public void writeChar(final int v) {
    this.buffer.writeChar(Character.reverseBytes((char) v));
  }

  @Override
  public void writeDouble(final double v) {
    this.buffer.writeDoubleLE(v);
  }

  @Override
  public void writeFloat(final float v) {
    this.buffer.writeFloatLE(v);
  }

  @Override
  public void writeInt(final int v) {
    this.buffer.writeIntLE(v);
  }

  @Override
  public void writeLong(final long v) {
    this.buffer.writeLongLE(v);
  }

  @Override
  public void writeShort(final int v) {
    this.buffer.writeShortLE(v);
  }

  @Override
  public void writeUTF(@NotNull final String s) throws IOException {
    final byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
    this.writeShort(bytes.length);
    this.write(bytes);
  }
}
