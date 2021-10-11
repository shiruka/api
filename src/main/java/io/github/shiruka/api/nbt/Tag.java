package io.github.shiruka.api.nbt;

import io.github.shiruka.api.nbt.array.ByteArrayTag;
import io.github.shiruka.api.nbt.array.IntArrayTag;
import io.github.shiruka.api.nbt.array.LongArrayTag;
import io.github.shiruka.api.nbt.compound.CompoundTagBasic;
import io.github.shiruka.api.nbt.list.ListTagBasic;
import io.github.shiruka.api.nbt.primitive.ByteTag;
import io.github.shiruka.api.nbt.primitive.DoubleTag;
import io.github.shiruka.api.nbt.primitive.EndTag;
import io.github.shiruka.api.nbt.primitive.FloatTag;
import io.github.shiruka.api.nbt.primitive.IntTag;
import io.github.shiruka.api.nbt.primitive.LongTag;
import io.github.shiruka.api.nbt.primitive.ShortTag;
import io.github.shiruka.api.nbt.primitive.StringTag;
import io.github.shiruka.api.nbt.stream.LittleEndianByteBufInputStream;
import io.github.shiruka.api.nbt.stream.LittleEndianByteBufOutputStream;
import io.github.shiruka.api.nbt.stream.LittleEndianDataInputStream;
import io.github.shiruka.api.nbt.stream.LittleEndianDataOutputStream;
import io.github.shiruka.api.nbt.stream.NBTInputStream;
import io.github.shiruka.api.nbt.stream.NBTOutputStream;
import io.github.shiruka.api.nbt.stream.NetworkDataInputStream;
import io.github.shiruka.api.nbt.stream.NetworkDataOutputStream;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine named binary tag.
 */
public interface Tag {

  /**
   * creates an instance of {@link ByteTag}.
   *
   * @return an instance of {@link ByteTag}.
   */
  @NotNull
  static ByteTag createByte() {
    return Tag.createByte(0);
  }

  /**
   * creates an instance of {@link ByteTag}.
   *
   * @param original the original byte.
   *
   * @return an instance of {@link ByteTag}.
   */
  @NotNull
  static ByteTag createByte(final int original) {
    return Tag.createByte((byte) original);
  }

  /**
   * creates an instance of {@link ByteTag}.
   *
   * @param original the original byte.
   *
   * @return an instance of {@link ByteTag}.
   */
  @NotNull
  static ByteTag createByte(final byte original) {
    return new ByteTag(original);
  }

  /**
   * creates an instance of {@link ByteArrayTag}.
   *
   * @param original the original list.
   *
   * @return an instance of {@link ByteArrayTag}.
   */
  @NotNull
  static ByteArrayTag createByteArray(final byte... original) {
    return new ByteArrayTag(original);
  }

  /**
   * creates an instance of {@link CompoundTag}.
   *
   * @return an instance of {@link CompoundTag}.
   */
  @NotNull
  static CompoundTag createCompound() {
    return new CompoundTagBasic();
  }

  /**
   * creates an instance of {@link CompoundTag}.
   *
   * @param original the original map.
   *
   * @return an instance of {@link CompoundTag}.
   */
  @NotNull
  static CompoundTag createCompound(@NotNull final Map<String, Tag> original) {
    return new CompoundTagBasic(original);
  }

  /**
   * creates an instance of {@link DoubleTag}.
   *
   * @return an instance of {@link DoubleTag}.
   */
  @NotNull
  static DoubleTag createDouble() {
    return Tag.createDouble(0.0d);
  }

  /**
   * creates an instance of {@link DoubleTag}.
   *
   * @param original the original double.
   *
   * @return an instance of {@link DoubleTag}.
   */
  @NotNull
  static DoubleTag createDouble(final double original) {
    return new DoubleTag(original);
  }

  /**
   * creates an instance of {@link EndTag}.
   *
   * @return an instance of {@link EndTag}.
   */
  @NotNull
  static EndTag createEnd() {
    return new EndTag();
  }

  /**
   * creates an instance of {@link FloatTag}.
   *
   * @return an instance of {@link FloatTag}.
   */
  @NotNull
  static FloatTag createFloat() {
    return Tag.createFloat(0.0f);
  }

  /**
   * creates an instance of {@link FloatTag}.
   *
   * @param original the original float.
   *
   * @return an instance of {@link FloatTag}.
   */
  @NotNull
  static FloatTag createFloat(final float original) {
    return new FloatTag(original);
  }

  /**
   * creates a nbt reader from the {@link InputStream}.
   *
   * @param stream the stream to create.
   *
   * @return a new instance of {@link NBTInputStream} with {@link GZIPInputStream}.
   *
   * @throws IOException if an I/O error has occurred.
   */
  @NotNull
  static NBTInputStream createGZIPReader(@NotNull final InputStream stream) throws IOException {
    return Tag.createReader(new GZIPInputStream(stream));
  }

  /**
   * creates a nbt writer from the {@link OutputStream}.
   *
   * @param stream the stream to create.
   *
   * @return a new instance of {@link NBTOutputStream} with {@link GZIPOutputStream}.
   *
   * @throws IOException if an I/O error has occurred.
   */
  @NotNull
  static NBTOutputStream createGZIPWriter(@NotNull final OutputStream stream) throws IOException {
    return Tag.createWriter(new GZIPOutputStream(stream));
  }

  /**
   * creates an instance of {@link IntTag}.
   *
   * @return an instance of {@link IntTag}.
   */
  @NotNull
  static IntTag createInt() {
    return Tag.createInt(0);
  }

  /**
   * creates an instance of {@link IntTag}.
   *
   * @param original the original int.
   *
   * @return an instance of {@link IntTag}.
   */
  @NotNull
  static IntTag createInt(final int original) {
    return new IntTag(original);
  }

  /**
   * creates an instance of {@link IntArrayTag}.
   *
   * @param original the original list.
   *
   * @return an instance of {@link IntArrayTag}.
   */
  @NotNull
  static IntArrayTag createIntArray(final int... original) {
    return new IntArrayTag(original);
  }

  /**
   * creates an instance of {@link ListTag}.
   *
   * @param original the original list.
   *
   * @return an instance of {@link ListTag}.
   */
  @NotNull
  static ListTag createList(@NotNull final Tag... original) {
    return Tag.createList(new ObjectArrayList<>(original));
  }

  /**
   * creates an instance of {@link ListTag}.
   *
   * @param original the original list.
   *
   * @return an instance of {@link ListTag}.
   */
  @NotNull
  static ListTag createList(@NotNull final List<Tag> original) {
    return new ListTagBasic(original, original.stream()
      .map(Tag::getType)
      .findFirst()
      .orElse(TagTypes.END));
  }

  /**
   * creates an instance of {@link LongTag}.
   *
   * @return an instance of {@link LongTag}.
   */
  @NotNull
  static LongTag createLong() {
    return Tag.createLong(0L);
  }

  /**
   * creates an instance of {@link LongTag}.
   *
   * @param original the original long.
   *
   * @return an instance of {@link LongTag}.
   */
  @NotNull
  static LongTag createLong(final long original) {
    return new LongTag(original);
  }

  /**
   * creates an instance of {@link LongArrayTag}.
   *
   * @param original the original list.
   *
   * @return an instance of {@link LongArrayTag}.
   */
  @NotNull
  static LongArrayTag createLongArray(final long... original) {
    return new LongArrayTag(original);
  }

  /**
   * creates a nbt reader from the {@link InputStream}.
   *
   * @param stream the stream to create.
   *
   * @return a new instance of {@link NBTInputStream} with {@link NetworkDataInputStream}.
   */
  @NotNull
  static NBTInputStream createNetworkReader(@NotNull final InputStream stream) {
    return new NBTInputStream(new NetworkDataInputStream(stream));
  }

  /**
   * creates a nbt writer from the {@link OutputStream}.
   *
   * @param stream the stream to create.
   *
   * @return a new instance of {@link NBTOutputStream} with {@link NetworkDataOutputStream}.
   */
  @NotNull
  static NBTOutputStream createNetworkWriter(@NotNull final OutputStream stream) {
    return new NBTOutputStream(new NetworkDataOutputStream(stream));
  }

  /**
   * creates an instance of {@link NumberTag}.
   *
   * @param original the original number.
   *
   * @return an instance of {@link NumberTag}.
   */
  @NotNull
  static NumberTag createNumber(@NotNull final Number original) {
    if (original instanceof Byte) {
      return new ByteTag(original.byteValue());
    } else if (original instanceof Short) {
      return new ShortTag(original.shortValue());
    } else if (original instanceof Integer) {
      return new IntTag(original.intValue());
    } else if (original instanceof Long) {
      return new LongTag(original.longValue());
    } else if (original instanceof Float) {
      return new FloatTag(original.floatValue());
    }
    return new DoubleTag(original.doubleValue());
  }

  /**
   * creates a nbt reader from the {@link InputStream}.
   *
   * @param stream the stream to create.
   *
   * @return a new instance of {@link NBTInputStream} with {@link DataInputStream}.
   */
  @NotNull
  static NBTInputStream createReader(@NotNull final InputStream stream) {
    return new NBTInputStream(new DataInputStream(stream));
  }

  /**
   * creates a nbt reader from the {@link InputStream}.
   *
   * @param stream the stream to create.
   *
   * @return a new instance of {@link NBTInputStream} with {@link LittleEndianDataInputStream}.
   */
  @NotNull
  static NBTInputStream createReaderLE(@NotNull final InputStream stream) {
    return new NBTInputStream(new LittleEndianDataInputStream(stream));
  }

  /**
   * creates a nbt reader from the {@link ByteBufOutputStream}.
   *
   * @param buffer the buffer to create.
   *
   * @return a new instance of {@link NBTInputStream} with {@link LittleEndianByteBufInputStream}.
   */
  @NotNull
  static NBTInputStream createReaderLE(@NotNull final ByteBuf buffer) {
    return new NBTInputStream(new LittleEndianByteBufInputStream(buffer));
  }

  /**
   * creates an instance of {@link ShortTag}.
   *
   * @return an instance of {@link ShortTag}.
   */
  @NotNull
  static ShortTag createShort() {
    return Tag.createShort(0);
  }

  /**
   * creates an instance of {@link ShortTag}.
   *
   * @param original the original short.
   *
   * @return an instance of {@link ShortTag}.
   */
  @NotNull
  static ShortTag createShort(final int original) {
    return Tag.createShort((short) original);
  }

  /**
   * creates an instance of {@link ShortTag}.
   *
   * @param original the original short.
   *
   * @return an instance of {@link ShortTag}.
   */
  @NotNull
  static ShortTag createShort(final short original) {
    return new ShortTag(original);
  }

  /**
   * creates an instance of {@link StringTag}.
   *
   * @param original the original string.
   *
   * @return an instance of {@link StringTag}.
   */
  @NotNull
  static StringTag createString(@NotNull final String original) {
    return new StringTag(original);
  }

  /**
   * creates an instance of {@link StringTag}.
   *
   * @return an instance of {@link StringTag}.
   */
  @NotNull
  static StringTag createString() {
    return Tag.createString("");
  }

  /**
   * creates a nbt writer from the {@link OutputStream}.
   *
   * @param stream the stream to create.
   *
   * @return a new instance of {@link NBTOutputStream} with {@link DataOutputStream}.
   */
  @NotNull
  static NBTOutputStream createWriter(@NotNull final OutputStream stream) {
    return new NBTOutputStream(new DataOutputStream(stream));
  }

  /**
   * creates a nbt writer from the {@link OutputStream}.
   *
   * @param stream the stream to create.
   *
   * @return a new instance of {@link NBTOutputStream} with {@link LittleEndianDataOutputStream}.
   */
  @NotNull
  static NBTOutputStream createWriterLE(@NotNull final OutputStream stream) {
    return new NBTOutputStream(new LittleEndianDataOutputStream(stream));
  }

  /**
   * creates a nbt writer from the {@link ByteBufOutputStream}.
   *
   * @param buffer the buffer to create.
   *
   * @return a new instance of {@link NBTOutputStream} with {@link LittleEndianByteBufOutputStream}.
   */
  @NotNull
  static NBTOutputStream createWriterLE(@NotNull final ByteBuf buffer) {
    return new NBTOutputStream(new LittleEndianByteBufOutputStream(buffer));
  }

  /**
   * an instance of {@code this} as a {@link ArrayTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link ArrayTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link ArrayTag}.
   */
  @NotNull
  default ArrayTag<?> asArray() {
    throw new IllegalStateException(String.format("%s cannot cast as a ArrayTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link ByteTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link ByteTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link ByteTag}.
   */
  @NotNull
  default ByteTag asByte() {
    throw new IllegalStateException(String.format("%s cannot cast as a ByteTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link ByteArrayTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link ByteArrayTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link ByteArrayTag}.
   */
  @NotNull
  default ByteArrayTag asByteArray() {
    throw new IllegalStateException(String.format("%s cannot cast as a ByteArrayTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link CompoundTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link CompoundTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link CompoundTag}.
   */
  @NotNull
  default CompoundTag asCompound() {
    throw new IllegalStateException(String.format("%s cannot cast as a CompoundTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link DoubleTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link DoubleTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link DoubleTag}.
   */
  @NotNull
  default DoubleTag asDouble() {
    throw new IllegalStateException(String.format("%s cannot cast as a DoubleTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link FloatTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link FloatTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link FloatTag}.
   */
  @NotNull
  default FloatTag asFloat() {
    throw new IllegalStateException(String.format("%s cannot cast as a FloatTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link IntTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link IntTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link IntTag}.
   */
  @NotNull
  default IntTag asInt() {
    throw new IllegalStateException(String.format("%s cannot cast as a IntTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link IntArrayTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link IntArrayTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link IntArrayTag}.
   */
  @NotNull
  default IntArrayTag asIntArray() {
    throw new IllegalStateException(String.format("%s cannot cast as a IntArrayTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link ListTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link ListTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link ListTag}.
   */
  @NotNull
  default ListTag asList() {
    throw new IllegalStateException(String.format("%s cannot cast as a ListTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link LongTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link LongTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link LongTag}.
   */
  @NotNull
  default LongTag asLong() {
    throw new IllegalStateException(String.format("%s cannot cast as a LongTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link LongArrayTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link LongArrayTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link LongArrayTag}.
   */
  @NotNull
  default LongArrayTag asLongArray() {
    throw new IllegalStateException(String.format("%s cannot cast as a LongArrayTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link NumberTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link NumberTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link NumberTag}.
   */
  @NotNull
  default NumberTag asNumber() {
    throw new IllegalStateException(String.format("%s cannot cast as a NumberTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link PrimitiveTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link PrimitiveTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link PrimitiveTag}.
   */
  @NotNull
  default PrimitiveTag<?> asPrimitive() {
    throw new IllegalStateException(String.format("%s cannot cast as a PrimitiveTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link ShortTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link ShortTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link ShortTag}.
   */
  @NotNull
  default ShortTag asShort() {
    throw new IllegalStateException(String.format("%s cannot cast as a ShortTag!",
      this.getClass()));
  }

  /**
   * an instance of {@code this} as a {@link StringTag}.
   *
   * @return an autoboxed instance of {@code this} as {@link StringTag}.
   *
   * @throws IllegalStateException if {@code this} is not a {@link StringTag}.
   */
  @NotNull
  default StringTag asString() {
    throw new IllegalStateException(String.format("%s cannot cast as a StringTag!",
      this.getClass()));
  }

  /**
   * obtains type of the tag.
   *
   * @return type of the tag.
   */
  @NotNull
  TagTypes getType();

  /**
   * checks if {@code this} is a {@link ArrayTag}.
   *
   * @return {@code true} if {@code this} is a {@link ArrayTag}.
   */
  default boolean isArray() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link ByteTag}.
   *
   * @return {@code true} if {@code this} is a {@link ByteTag}.
   */
  default boolean isByte() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link ByteArrayTag}.
   *
   * @return {@code true} if {@code this} is a {@link ByteArrayTag}.
   */
  default boolean isByteArray() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link CompoundTag}.
   *
   * @return {@code true} if {@code this} is a {@link CompoundTag}.
   */
  default boolean isCompound() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link DoubleTag}.
   *
   * @return {@code true} if {@code this} is a {@link DoubleTag}.
   */
  default boolean isDouble() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link FloatTag}.
   *
   * @return {@code true} if {@code this} is a {@link FloatTag}.
   */
  default boolean isFloat() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link IntTag}.
   *
   * @return {@code true} if {@code this} is a {@link IntTag}.
   */
  default boolean isInt() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link IntArrayTag}.
   *
   * @return {@code true} if {@code this} is a {@link IntArrayTag}.
   */
  default boolean isIntArray() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link ListTag}.
   *
   * @return {@code true} if {@code this} is a {@link ListTag}.
   */
  default boolean isList() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link LongTag}.
   *
   * @return {@code true} if {@code this} is a {@link LongTag}.
   */
  default boolean isLong() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link LongArrayTag}.
   *
   * @return {@code true} if {@code this} is a {@link LongArrayTag}.
   */
  default boolean isLongArray() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link NumberTag}.
   *
   * @return {@code true} if {@code this} is a {@link NumberTag}.
   */
  default boolean isNumber() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link PrimitiveTag}.
   *
   * @return {@code true} if {@code this} is a {@link PrimitiveTag}.
   */
  default boolean isPrimitive() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link ShortTag}.
   *
   * @return {@code true} if {@code this} is a {@link ShortTag}.
   */
  default boolean isShort() {
    return false;
  }

  /**
   * checks if {@code this} is a {@link StringTag}.
   *
   * @return {@code true} if {@code this} is a {@link StringTag}.
   */
  default boolean isString() {
    return false;
  }
}
