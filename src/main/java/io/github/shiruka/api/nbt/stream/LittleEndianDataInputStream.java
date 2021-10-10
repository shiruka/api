package io.github.shiruka.api.nbt.stream;

import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link DataInput}.
 */
public class LittleEndianDataInputStream implements DataInput, Closeable {

  /**
   * the stream.
   */
  @NotNull
  protected final DataInputStream stream;

  /**
   * ctor.
   *
   * @param stream the stream.
   */
  public LittleEndianDataInputStream(@NotNull final InputStream stream) {
    this.stream = new DataInputStream(stream);
  }

  /**
   * ctor.
   *
   * @param stream the stream.
   */
  public LittleEndianDataInputStream(@NotNull final DataInputStream stream) {
    this.stream = stream;
  }

  @Override
  public final void close() throws IOException {
    this.stream.close();
  }

  @Override
  public final void readFully(final byte @NotNull [] b) throws IOException {
    this.stream.readFully(b);
  }

  @Override
  public final void readFully(final byte @NotNull [] b, final int off, final int len) throws IOException {
    this.stream.readFully(b, off, len);
  }

  @Override
  public final int skipBytes(final int n) throws IOException {
    return this.stream.skipBytes(n);
  }

  @Override
  public final boolean readBoolean() throws IOException {
    return this.stream.readBoolean();
  }

  @Override
  public final byte readByte() throws IOException {
    return this.stream.readByte();
  }

  @Override
  public final int readUnsignedByte() throws IOException {
    return this.stream.readUnsignedByte();
  }

  @Override
  public final short readShort() throws IOException {
    return Short.reverseBytes(this.stream.readShort());
  }

  @Override
  public final int readUnsignedShort() throws IOException {
    return Short.toUnsignedInt(Short.reverseBytes(this.stream.readShort()));
  }

  @Override
  public final char readChar() throws IOException {
    return Character.reverseBytes(this.stream.readChar());
  }

  @Override
  public int readInt() throws IOException {
    return Integer.reverseBytes(this.stream.readInt());
  }

  @Override
  public long readLong() throws IOException {
    return Long.reverseBytes(this.stream.readLong());
  }

  @Override
  public final float readFloat() throws IOException {
    return Float.intBitsToFloat(Integer.reverseBytes(this.stream.readInt()));
  }

  @Override
  public final double readDouble() throws IOException {
    return Double.longBitsToDouble(Long.reverseBytes(this.stream.readLong()));
  }

  @Override
  @Deprecated
  public final String readLine() throws IOException {
    return this.stream.readLine();
  }

  @NotNull
  @Override
  public String readUTF() throws IOException {
    final var bytes = new byte[this.readUnsignedShort()];
    this.readFully(bytes);
    return new String(bytes, StandardCharsets.UTF_8);
  }
}
