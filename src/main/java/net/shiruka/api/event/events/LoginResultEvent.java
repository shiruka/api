package net.shiruka.api.event.events;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine login result events.
 */
public interface LoginResultEvent {

  /**
   * obtains the login result.
   *
   * @return login result.
   */
  @NotNull
  LoginResult getLoginResult();

  /**
   * sets the login result.
   *
   * @param result the result to set.
   */
  void setLoginResult(@NotNull LoginResult result);

  /**
   * an enum class to determine login result.
   */
  enum LoginResult {

    /**
     * the player is allowed to log in.
     */
    ALLOWED,
    /**
     * the player is not allowed to log in, due to the server being full.
     */
    KICK_FULL,
    /**
     * the player is not allowed to log in, due to them being banned.
     */
    KICK_BANNED,
    /**
     * the player is not allowed to log in, due to them not being on the white list.
     */
    KICK_WHITELIST,
    /**
     * the player is not allowed to log in, for reasons undefined.
     */
    KICK_OTHER
  }
}
