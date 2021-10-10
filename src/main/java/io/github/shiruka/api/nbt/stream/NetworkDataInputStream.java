package io.github.shiruka.api.nbt.stream;

import io.github.shiruka.api.nbt.VarInts;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link LittleEndianDataInputStream}.
 */
public final class NetworkDataInputStream extends LittleEndianDataInputStream {

  /**
   * ctor.
   *
   * @param stream the stream.
   */
  public NetworkDataInputStream(@NotNull final InputStream stream) {
    super(stream);
  }

  /**
   * ctor.
   *
   * @param stream the stream.
   */
  public NetworkDataInputStream(@NotNull final DataInputStream stream) {
    super(stream);
  }

  @Override
  public int readInt() throws IOException {
    return VarInts.readInt(this.stream);
  }

  @Override
  public long readLong() throws IOException {
    return VarInts.readLong(this.stream);
  }

  @NotNull
  @Override
  public String readUTF() throws IOException {
    final var length = VarInts.readUnsignedInt(this.stream);
    final var bytes = new byte[length];
    this.readFully(bytes);
    return new String(bytes, StandardCharsets.UTF_8);
  }
}
