package io.github.shiruka.api.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * a class that contains utility methods for {@link DataInput} and {@link DataOutput} classes.
 */
@UtilityClass
public class VarInts {

  /**
   * reads the integer from the given input.
   *
   * @param input the input to read.
   *
   * @return the given input's integer value.
   *
   * @throws IOException if something went wrong when reading to the input.
   */
  public int readInt(@NotNull final DataInput input) throws IOException {
    final var n = (int) VarInts.decodeUnsigned(input);
    return n >>> 1 ^ -(n & 1);
  }

  /**
   * reads the long from the given input.
   *
   * @param input the input to read.
   *
   * @return the given input's long value.
   *
   * @throws IOException if something went wrong when reading the input.
   */
  public long readLong(@NotNull final DataInput input) throws IOException {
    final var n = VarInts.decodeUnsigned(input);
    return n >>> 1 ^ -(n & 1);
  }

  /**
   * reads the integer from the given input.
   *
   * @param input the input to read.
   *
   * @return the given input's integer value.
   *
   * @throws IOException if something went wrong when reading the input.
   */
  public int readUnsignedInt(@NotNull final DataInput input) throws IOException {
    return (int) VarInts.decodeUnsigned(input);
  }

  /**
   * writes the given integer into the given output.
   *
   * @param output the output to write.
   * @param integer the integer to write.
   *
   * @throws IOException if something went wrong when writing to the output..
   */
  public void writeInt(@NotNull final DataOutput output, final int integer) throws IOException {
    VarInts.encodeUnsigned(output, (long) integer << 1 ^ integer >> 31);
  }

  /**
   * writes the given long into the given output.
   *
   * @param output the output to write.
   * @param longInteger the long integer to write.
   *
   * @throws IOException if something went wrong when writing to the output.
   */
  public void writeLong(@NotNull final DataOutput output, final long longInteger) throws IOException {
    VarInts.encodeUnsigned(output, longInteger << 1 ^ longInteger >> 63);
  }

  /**
   * writes the given integer into the given output.
   *
   * @param output the output to write.
   * @param integer the integer to write.
   *
   * @throws IOException if something went wrong when writing to the output.
   */
  public void writeUnsignedInt(@NotNull final DataOutput output, final long integer) throws IOException {
    VarInts.encodeUnsigned(output, integer);
  }

  /**
   * decodes the long value from the given input.
   *
   * @param input the input to decode.
   *
   * @return decoded lon value from the given input.
   *
   * @throws IOException if something went wrong when decoding the given input.
   */
  private long decodeUnsigned(@NotNull final DataInput input) throws IOException {
    var result = 0;
    for (var shift = 0; shift < 64; shift += 7) {
      final var b = input.readByte();
      result |= (long) (b & 0x7F) << shift;
      if ((b & 0x80) == 0) {
        return result;
      }
    }
    throw new ArithmeticException("VarInt was too large");
  }

  /**
   * encodes the given value into the output.
   *
   * @param output the output to encode.
   * @param value the value to encode.
   *
   * @throws IOException if something went wrong when encoding to the output.
   */
  private void encodeUnsigned(@NotNull final DataOutput output, final long value) throws IOException {
    var tempValue = value;
    while (true) {
      if ((tempValue & ~0x7FL) == 0) {
        output.writeByte((int) tempValue);
        return;
      }
      output.writeByte((byte) ((int) tempValue & 0x7F | 0x80));
      tempValue >>>= 7;
    }
  }
}
