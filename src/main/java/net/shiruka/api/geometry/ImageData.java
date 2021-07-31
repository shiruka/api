package net.shiruka.api.geometry;

import com.google.common.base.Preconditions;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents image data.
 */
@Getter
@ToString(exclude = {"image"})
@EqualsAndHashCode(doNotUseGetters = true)
public final class ImageData {

  /**
   * an empty instance of {@code this}.
   */
  private static final ImageData EMPTY = new ImageData(0, 0, new byte[0]);

  /**
   * the height.
   */
  private final int height;

  /**
   * the image.
   */
  private final byte[] image;

  /**
   * the width.
   */
  private final int width;

  /**
   * ctor.
   *
   * @param width the width.
   * @param height the height.
   * @param image the image.
   */
  private ImageData(final int width, final int height, final byte[] image) {
    this.width = width;
    this.height = height;
    this.image = image.clone();
  }

  /**
   * obtains the empty image data.
   *
   * @return empty image data.
   */
  @NotNull
  public static ImageData empty() {
    return ImageData.EMPTY;
  }

  /**
   * creates a new instance of {@code this} from the given {@link BufferedImage}.
   *
   * @param image the image to create.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  public static ImageData from(@NotNull final BufferedImage image) {
    final var outputStream = new ByteArrayOutputStream();
    for (var y = 0; y < image.getHeight(); y++) {
      for (var x = 0; x < image.getWidth(); x++) {
        final var color = new Color(image.getRGB(x, y), true);
        outputStream.write(color.getRed());
        outputStream.write(color.getGreen());
        outputStream.write(color.getBlue());
        outputStream.write(color.getAlpha());
      }
    }
    image.flush();
    return new ImageData(image.getWidth(), image.getHeight(), outputStream.toByteArray());
  }

  /**
   * creates a new instance of {@code this} from the given bytes of images.
   *
   * @param image the image of bytes to create.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  public static ImageData of(final byte[] image) {
    switch (image.length) {
      case 0:
        return ImageData.EMPTY;
      case Skin.SINGLE_SKIN_SIZE:
        return new ImageData(64, 32, image);
      case Skin.DOUBLE_SKIN_SIZE:
        return new ImageData(64, 64, image);
      case Skin.SKIN_128_64_SIZE:
        return new ImageData(128, 64, image);
      case Skin.SKIN_128_128_SIZE:
        return new ImageData(128, 128, image);
      default:
        throw new IllegalArgumentException("Invalid legacy skin");
    }
  }

  /**
   * creates a new instance of {@code this} from the given parameters.
   *
   * @param width the width to create.
   * @param height the height to create.
   * @param image the image to create.
   *
   * @return a new instance of {@code this}.
   */
  @NotNull
  public static ImageData of(final int width, final int height, final byte[] image) {
    return new ImageData(width, height, image);
  }

  /**
   * checks image legacy cape size.
   *
   * @throws IllegalArgumentException if image length not equals to zero and image length is not equals to
   *   {@link Skin#SINGLE_SKIN_SIZE}
   */
  public void checkLegacyCapeSize() {
    Preconditions.checkArgument(this.image.length == 0 || this.image.length == Skin.SINGLE_SKIN_SIZE,
      "Invalid legacy cape");
  }

  /**
   * checks image legacy skin size.
   *
   * @throws IllegalArgumentException if image length not equals to {@link Skin#SINGLE_SKIN_SIZE} or
   *   {@link Skin#DOUBLE_SKIN_SIZE} or {@link Skin#SKIN_128_64_SIZE} or {@link Skin#SKIN_128_128_SIZE}.
   */
  public void checkLegacySkinSize() {
    switch (this.image.length) {
      case Skin.SINGLE_SKIN_SIZE:
      case Skin.DOUBLE_SKIN_SIZE:
      case Skin.SKIN_128_64_SIZE:
      case Skin.SKIN_128_128_SIZE:
        return;
      default:
        throw new IllegalArgumentException("Invalid legacy skin");
    }
  }
}
