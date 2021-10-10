package io.github.shiruka.api.nbt.compound;

import io.github.shiruka.api.nbt.CompoundTag;
import io.github.shiruka.api.nbt.Tag;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link CompoundTag}.
 *
 * @param original the original.
 */
public final record CompoundTagBasic(
  @NotNull Map<String, Tag> original
) implements CompoundTag {

  /**
   * ctor.
   */
  public CompoundTagBasic() {
    this(new Object2ObjectOpenHashMap<>());
  }

  @NotNull
  @Override
  public Map<String, Tag> all() {
    return Collections.unmodifiableMap(this.original);
  }

  @Override
  public boolean contains(@NotNull final Tag tag) {
    return this.original.containsValue(tag);
  }

  @Override
  public boolean containsKey(@NotNull final String key) {
    return this.original.containsKey(key);
  }

  @NotNull
  @Override
  public Optional<Tag> get(@NotNull final String key) {
    return Optional.ofNullable(this.original.get(key));
  }

  @Override
  public void remove(@NotNull final String key) {
    this.original.remove(key);
  }

  @Override
  public void set(@NotNull final String key, @NotNull final Tag tag) {
    this.original.put(key, tag);
  }

  @Override
  public int size() {
    return this.original.size();
  }
}
