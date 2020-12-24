/*
 * MIT License
 *
 * Copyright (c) 2020 Shiru ka
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

package io.github.shiruka.api.command;

import io.github.shiruka.api.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine command context.
 */
public interface CommandContext {

  /**
   * obtains the sender.
   *
   * @return sender.
   */
  @NotNull
  CommandSender sender();

  /**
   * obtains the sender as {@link Player} if the {@link #sender()} is a {@link Player}.
   *
   * @return the sender as player.
   *
   * @throws IllegalStateException if {@link #sender()} is not a {@link Player}.
   */
  @NotNull
  default Player senderAsPlayer() {
    if (!(this.sender() instanceof Player)) {
      throw new IllegalStateException("The command sender is not a Player!");
    }
    return (Player) this.sender();
  }
}
