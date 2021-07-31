package net.shiruka.api.old.base;

import java.util.UUID;
import net.shiruka.api.old.geometry.Skin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine chain data.
 */
public interface ChainData {

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
   * obtains the xbox unique id.
   *
   * @return xbox unique id.
   */
  @Nullable
  String getXboxUniqueId();

  /**
   * obtains the xbox authed.
   *
   * @return xbox authed.
   */
  boolean isXboxAuthed();
}
