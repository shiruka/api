package io.github.shiruka.api.nbt.stream;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link DataOutput}.
 */
public class LittleEndianDataOutputStream implements DataOutput, Closeable {

  /**
   * the stream.
   */
  @NotNull
  protected final DataOutputStream stream;

  /**
   * ctor.
   *
   * @param stream the stream.
   */
  public LittleEndianDataOutputStream(@NotNull final OutputStream stream) {
    this.stream = new DataOutputStream(stream);
  }

  /**
   * ctor.
   *
   * @param stream the stream.
   */
  public LittleEndianDataOutputStream(@NotNull final DataOutputStream stream) {
    this.stream = stream;
  }

  @Override
  public final void close() throws IOException {
    this.stream.close();
  }

  @Override
  public final void write(final int b) throws IOException {
    this.stream.write(b);
  }

  @Override
  public final void write(final byte @NotNull [] b) throws IOException {
    this.stream.write(b);
  }

  @Override
  public final void write(final byte @NotNull [] b, final int off, final int len) throws IOException {
    this.stream.write(b, off, len);
  }

  @Override
  public final void writeBoolean(final boolean v) throws IOException {
    this.stream.writeBoolean(v);
  }

  @Override
  public final void writeByte(final int v) throws IOException {
    this.stream.writeByte(v);
  }

  @Override
  public final void writeShort(final int v) throws IOException {
    this.stream.writeShort(Short.reverseBytes((short) v));
  }

  @Override
  public final void writeChar(final int v) throws IOException {
    this.stream.writeChar(Character.reverseBytes((char) v));
  }

  @Override
  public void writeInt(final int v) throws IOException {
    this.stream.writeInt(Integer.reverseBytes(v));
  }

  @Override
  public void writeLong(final long v) throws IOException {
    this.stream.writeLong(Long.reverseBytes(v));
  }

  @Override
  public final void writeFloat(final float v) throws IOException {
    this.stream.writeInt(Integer.reverseBytes(Float.floatToIntBits(v)));
  }

  @Override
  public final void writeDouble(final double v) throws IOException {
    this.stream.writeLong(Long.reverseBytes(Double.doubleToLongBits(v)));
  }

  @Override
  public final void writeBytes(@NotNull final String s) throws IOException {
    this.stream.writeBytes(s);
  }

  @Override
  public final void writeChars(@NotNull final String s) throws IOException {
    this.stream.writeChars(s);
  }

  @Override
  public void writeUTF(@NotNull final String s) throws IOException {
    final var bytes = s.getBytes(StandardCharsets.UTF_8);
    this.writeShort(bytes.length);
    this.write(bytes);
  }
}
