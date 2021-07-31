package net.shiruka.api.pack;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine resource pack managers.
 */
public interface PackManager extends Closeable {

  /**
   * closes the registration of resource packs.
   */
  void closeRegistration();

  /**
   * get resource pack loader instance from the given path.
   *
   * @param path the path to get.
   *
   * @return resource pack loader.
   *
   * @throws IOException if an I/O error has occurred.
   */
  @NotNull
  Optional<PackLoader> getLoader(@NotNull Path path) throws IOException;

  /**
   * gets resource pack manifest instance from the given loader.
   *
   * @param loader the loader to get.
   *
   * @return resource pack manifest.
   */
  @NotNull
  Optional<PackManifest> getManifest(@NotNull PackLoader loader);

  /**
   * obtains the pack from the given unique id.
   *
   * @param uniqueId the unique id to obtain.
   *
   * @return obtained pack.
   */
  @NotNull
  Optional<Pack> getPackById(@NotNull UUID uniqueId);

  /**
   * obtains the pack from the given entry.
   *
   * @param entry the entry to get.
   *
   * @return obtained pack.
   */
  @NotNull
  Optional<Pack> getPackByIdVersion(@NotNull String entry);

  /**
   * obtains the pack info packet.
   *
   * @return pack info packet.
   */
  @NotNull
  Object getPackInfo();

  /**
   * obtains the pack stack packet.
   *
   * @return pack stack packet.
   */
  @NotNull
  Object getPackStack();

  /**
   * obtains the loaded packs.
   *
   * @return loaded packs.
   */
  @NotNull
  Map<String, Pack> getPacks();

  /**
   * loads pack from the given path.
   *
   * @param path the path to load.
   *
   * @throws IllegalStateException if no suitable loader found, if manifest not found, if the specified {@link
   *   PackType} is no supported.
   */
  void loadPack(@NotNull Path path);

  /**
   * loads packs from the given directory.
   *
   * @param directory the directory to load.
   */
  void loadPacks(@NotNull Path directory);

  /**
   * registers the given pack loader class.
   *
   * @param cls the class to register.
   * @param factory the factory to register.
   *
   * @throws IllegalArgumentException if the given cls is already registered.
   */
  void registerLoader(@NotNull Class<? extends PackLoader> cls, @NotNull PackLoader.Factory factory);

  /**
   * registers the given pack class.
   *
   * @param type the type to register.
   * @param factory the factory to register.
   *
   * @throws IllegalArgumentException if the given cls is already registered.
   */
  void registerPack(@NotNull PackType type, @NotNull Pack.Factory factory);
}
