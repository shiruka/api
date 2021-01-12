/*
 * MIT License
 *
 * Copyright (c) 2021 Shiru ka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.shiruka.api.language;

import io.github.shiruka.api.Shiruka;
import io.github.shiruka.api.entity.Player;
import java.util.Optional;
import org.cactoos.Text;
import org.jetbrains.annotations.NotNull;

public interface TranslatableText extends Text {

  @Override
  default String asString() {
    return this.toString();
  }

  /**
   * gives the translated string.
   *
   * @param player the player to get.
   * @param params the params to get.
   *
   * @return translated string.
   */
  @NotNull
  default Optional<String> translate(@NotNull final Player player, @NotNull final Object... params) {
    return Shiruka.getLanguageManager().getLanguage(player.getChainData().languageCode())
      .map(language -> this.translate(language, params));
  }

  /**
   * gives the translated string.
   *
   * @param language the language to get.
   * @param params the params to get.
   *
   * @return translated string.
   */
  @NotNull
  String translate(@NotNull Language language, @NotNull Object... params);
}
