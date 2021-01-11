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

package io.github.shiruka.api.events;

import io.github.shiruka.api.geometry.Skin;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine login data events.
 */
public interface LoginDataEvent extends Event {

  /**
   * obtains the login data.
   *
   * @return login data.
   */
  @NotNull
  LoginData loginData();

  /**
   * an interface to determine chain data.
   */
  interface ChainData {

    /**
     * obtains the current input mode.
     *
     * @return current input mode.
     */
    int currentInputMode();

    /**
     * obtains the default input mode.
     *
     * @return default input mode.
     */
    int defaultInputMode();

    /**
     * obtains the device id.
     *
     * @return device id.
     */
    @NotNull
    String deviceId();

    /**
     * obtains the device model.
     *
     * @return device mode.
     */
    @NotNull
    String deviceModel();

    /**
     * obtains the device OS.
     *
     * @return device OS.
     */
    int deviceOS();

    /**
     * obtains the game version.
     *
     * @return game version.
     */
    @NotNull
    String gameVersion();

    /**
     * obtains the gui scale.
     *
     * @return gui scale.
     */
    int guiScale();

    /**
     * obtains the id.
     *
     * @return id.
     */
    long id();

    /**
     * obtains the language code.
     *
     * @return language code.
     */
    @NotNull
    String languageCode();

    /**
     * obtains the public key.
     *
     * @return public key.
     */
    @NotNull
    String publicKey();

    /**
     * obtains the server address
     *
     * @return server address.
     */
    @NotNull
    String serverAddress();

    /**
     * obtains the skin.
     *
     * @return skin.
     */
    @NotNull
    Skin skin();

    /**
     * obtains the ui profile.
     *
     * @return ui profile.
     */
    int uiProfile();

    /**
     * obtains the unique id.
     *
     * @return unique id.
     */
    @NotNull
    UUID uniqueId();

    /**
     * obtains the username.
     *
     * @return username.
     */
    @NotNull
    String username();

    /**
     * obtains the xbox authed.
     *
     * @return xbox authed.
     */
    boolean xboxAuthed();

    /**
     * obtains the xuid.
     *
     * @return xuid.
     */
    @NotNull
    String xuid();
  }

  /**
   * an interface to determine login data.
   */
  interface LoginData {

    /**
     * obtains the chain data.
     *
     * @return chain data.
     */
    @NotNull
    ChainData chainData();
  }
}
