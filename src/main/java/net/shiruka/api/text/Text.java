package net.shiruka.api.text;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine texts.
 */
public interface Text {

  /**
   * creates an empty text.
   *
   * @return empty text.
   */
  @NotNull
  static Text empty() {
    return () -> "";
  }

  /**
   * obtains the content.
   *
   * @return content.
   */
  @NotNull
  String asString();
}
