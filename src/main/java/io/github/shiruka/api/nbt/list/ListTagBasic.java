package io.github.shiruka.api.nbt.list;

import com.google.common.base.Preconditions;
import io.github.shiruka.api.nbt.ListTag;
import io.github.shiruka.api.nbt.Tag;
import io.github.shiruka.api.nbt.TagTypes;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link ListTag}.
 */
public final class ListTagBasic implements ListTag {

  /**
   * the list id.
   */
  @NotNull
  private TagTypes listType;

  /**
   * the original.
   */
  @NotNull
  private List<Tag> original;

  /**
   * ctor.
   *
   * @param original the original.
   * @param listType the list type.
   */
  public ListTagBasic(@NotNull final List<Tag> original, @NotNull final TagTypes listType) {
    this.original = Collections.unmodifiableList(original);
    this.listType = listType;
  }

  @Override
  public void add(@NotNull final Tag tag) {
    this.edit(tags -> {
      final var endType = TagTypes.END;
      Preconditions.checkArgument(tag.getType() != endType,
        "Cannot add a %s to a %s", endType, TagTypes.LIST);
      if (this.getListType() != endType) {
        Preconditions.checkArgument(tag.getType() == this.listType,
          "Trying to add tag of type %s to list of %s", tag.getType(), this.listType);
      }
      tags.add(tag);
    }, tag.getType());
  }

  @NotNull
  @Override
  public List<Tag> all() {
    return Collections.unmodifiableList(this.original);
  }

  @NotNull
  @Override
  public TagTypes getListType() {
    return this.listType;
  }

  @NotNull
  @Override
  public Stream<Tag> stream() {
    return this.original.stream();
  }

  @Override
  public boolean contains(@NotNull final Tag tag) {
    return this.original.contains(tag);
  }

  @NotNull
  @Override
  public Optional<Tag> get(final int key) {
    return Optional.ofNullable(this.original.get(key));
  }

  @Override
  public void remove(final int key) {
    this.edit(tags -> tags.remove(key), TagTypes.NONE);
  }

  @Override
  public void set(final int key, @NotNull final Tag tag) {
    this.edit(tags -> tags.set(key, tag), tag.getType());
  }

  @Override
  public int hashCode() {
    return this.original.hashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    return this == obj ||
      obj instanceof ListTagBasic list && this.original.equals(list.original);
  }

  @Override
  public String toString() {
    return this.original.toString();
  }

  @NotNull
  @Override
  public Iterator<Tag> iterator() {
    final var iterator = this.original.iterator();
    return new Iterator<>() {
      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }

      @Override
      public Tag next() {
        return iterator.next();
      }

      @Override
      public void forEachRemaining(final Consumer<? super Tag> action) {
        iterator.forEachRemaining(action);
      }
    };
  }

  @Override
  public void forEach(@NotNull final Consumer<? super Tag> action) {
    this.original.forEach(action);
  }

  @NotNull
  @Override
  public Spliterator<Tag> spliterator() {
    return Spliterators.spliterator(this.original, Spliterator.ORDERED | Spliterator.IMMUTABLE);
  }

  /**
   * edits with the given consumer.
   *
   * @param consumer the consumer to edit.
   * @param type the type to edit.
   */
  private void edit(@NotNull final Consumer<List<Tag>> consumer, @NotNull final TagTypes type) {
    final var tags = new ObjectArrayList<>(this.original);
    consumer.accept(tags);
    if (type != TagTypes.NONE && this.listType == TagTypes.END) {
      this.original = tags;
      this.listType = type;
    } else {
      this.original = tags;
    }
  }
}
