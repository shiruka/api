package net.shiruka.api.pack;

import com.nukkitx.protocol.bedrock.data.ResourcePackType;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;
import net.shiruka.api.util.SemanticVersion;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine smallest piece of resource packs.
 */
public interface Pack extends Closeable {

  @Override
  default void close() throws IOException {
    this.getLoader().close();
  }

  /**
   * obtains the chunk.
   *
   * @param offset the offset to get.
   * @param length the length to get.
   *
   * @return chunk.
   */
  default byte[] getChunk(final int offset, final int length) {
    final byte[] chunk;
    if (this.getSize() - offset > length) {
      chunk = new byte[length];
    } else {
      chunk = new byte[(int) (this.getSize() - offset)];
    }
    try (final var stream = Files.newInputStream(this.getLoader().getPreparedFile().join())) {
      stream.skip(offset);
      stream.read(chunk);
    } catch (final Exception e) {
      throw new IllegalStateException("Unable to read pack chunk!");
    }
    return chunk;
  }

  /**
   * obtains the hash.
   *
   * @return hash.
   */
  byte[] getHash();

  /**
   * obtains the id.
   *
   * @return id.
   */
  @NotNull
  default UUID getId() {
    return this.getManifest().getHeader().getUniqueId();
  }

  /**
   * obtains the loader.
   *
   * @return loader.
   */
  @NotNull
  PackLoader getLoader();

  /**
   * obtains the manifest.
   *
   * @return manifest.
   */
  @NotNull
  PackManifest getManifest();

  /**
   * obtains the namr.
   *
   * @return name.
   */
  @NotNull
  default String getName() {
    return this.getManifest().getHeader().getName();
  }

  /**
   * obtains the size.
   *
   * @return size.
   */
  default long getSize() {
    try {
      return Files.size(this.getLoader().getPreparedFile().join());
    } catch (final IOException e) {
      throw new IllegalStateException("Unable to get size of pack", e);
    }
  }

  /**
   * obtains the type.
   *
   * @return type.
   */
  @NotNull
  ResourcePackType getType();

  /**
   * obtains the version.
   *
   * @return version.
   */
  @NotNull
  default SemanticVersion getVersion() {
    return this.getManifest().getHeader().getVersion();
  }

  /**
   * a functional interface to create {@link Pack}.
   */
  @FunctionalInterface
  interface Factory {

    /**
     * create a new pack instance.
     *
     * @param loader the loader to create.
     * @param manifest the manifest to create.
     * @param module the module to create.
     *
     * @return a new pack instance.
     */
    @NotNull
    Pack create(@NotNull PackLoader loader, @NotNull PackManifest manifest, @NotNull PackManifest.Module module);
  }
}
