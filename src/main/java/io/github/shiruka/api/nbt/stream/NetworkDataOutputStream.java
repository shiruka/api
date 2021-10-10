package io.github.shiruka.api.nbt.stream;

import io.github.shiruka.api.nbt.VarInts;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link LittleEndianDataOutputStream}.
 */
public final class NetworkDataOutputStream extends LittleEndianDataOutputStream {

  /**
   * ctor.
   *
   * @param stream the stream.
   */
  public NetworkDataOutputStream(@NotNull final OutputStream stream) {
    super(stream);
  }

  /**
   * ctor.
   *
   * @param stream the stream.
   */
  public NetworkDataOutputStream(@NotNull final DataOutputStream stream) {
    super(stream);
  }

  @Override
  public void writeInt(final int v) throws IOException {
    VarInts.writeInt(this.stream, v);
  }

  @Override
  public void writeLong(final long v) throws IOException {
    VarInts.writeLong(this.stream, v);
  }

  @Override
  public void writeUTF(@NotNull final String s) throws IOException {
    final var bytes = s.getBytes(StandardCharsets.UTF_8);
    VarInts.writeUnsignedInt(this.stream, bytes.length);
    this.write(bytes);
  }
}
