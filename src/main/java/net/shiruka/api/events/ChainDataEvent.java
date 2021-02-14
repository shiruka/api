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

package net.shiruka.api.events;

import java.util.UUID;
import net.shiruka.api.geometry.Skin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine events that have chain data value.
 */
public interface ChainDataEvent extends Event {

  /**
   * obtains the chain data.
   *
   * @return chain data.
   */
  @NotNull
  ChainData getChainData();

  /**
   * an interface to determine chain data.
   */
  interface ChainData {

    /**
     * obtains the current input mode.
     *
     * @return current input mode.
     */
    int getCurrentInputMode();

    /**
     * obtains the default input mode.
     *
     * @return default input mode.
     */
    int getDefaultInputMode();

    /**
     * obtains the device id.
     *
     * @return device id.
     */
    @NotNull
    String getDeviceId();

    /**
     * obtains the device model.
     *
     * @return device mode.
     */
    @NotNull
    String getDeviceModel();

    /**
     * obtains the device OS.
     *
     * @return device OS.
     */
    int getDeviceOS();

    /**
     * obtains the game version.
     *
     * @return game version.
     */
    @NotNull
    String getGameVersion();

    /**
     * obtains the gui scale.
     *
     * @return gui scale.
     */
    int getGuiScale();

    /**
     * obtains the id.
     *
     * @return id.
     */
    long getId();

    /**
     * obtains the language code.
     *
     * @return language code.
     */
    @NotNull
    String getLanguageCode();

    /**
     * obtains the public key.
     *
     * @return public key.
     */
    @NotNull
    String getPublicKey();

    /**
     * obtains the server address.
     *
     * @return server address.
     */
    @NotNull
    String getServerAddress();

    /**
     * obtains the skin.
     *
     * @return skin.
     */
    @NotNull
    Skin getSkin();

    /**
     * obtains the ui profile.
     *
     * @return ui profile.
     */
    int getUiProfile();

    /**
     * obtains the unique id.
     *
     * @return unique id.
     */
    @NotNull
    UUID getUniqueId();

    /**
     * obtains the username.
     *
     * @return username.
     */
    @NotNull
    String getUsername();

    /**
     * obtains the xbox authed.
     *
     * @return xbox authed.
     */
    boolean getXboxAuthed();

    /**
     * obtains the xbox unique id.
     *
     * @return xbox unique id.
     */
    @Nullable
    String getXboxUniqueId();
  }
}
