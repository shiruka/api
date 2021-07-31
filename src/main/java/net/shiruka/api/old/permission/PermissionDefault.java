package net.shiruka.api.old.permission;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * an enum class that represents the possible default values for permissions.
 */
public enum PermissionDefault {
  /**
   * the true.
   */
  TRUE("true"),
  /**
   * the false.
   */
  FALSE("false"),
  /**
   * the op.
   */
  OP("op", "isop", "operator", "isoperator", "admin", "isadmin"),
  /**
   * the not op.
   */
  NOT_OP("!op", "notop", "!operator", "notoperator", "!admin", "notadmin");

  /**
   * the cache.
   */
  private static final Map<String, PermissionDefault> CACHE = new Object2ObjectOpenHashMap<>();

  /**
   * the names.
   */
  @NotNull
  private final String[] names;

  static {
    Arrays.stream(PermissionDefault.values())
      .forEach(value -> Arrays.stream(value.names)
        .forEach(name -> PermissionDefault.CACHE.put(name, value)));
  }

  /**
   * ctor.
   *
   * @param names the names.
   */
  PermissionDefault(@NotNull final String... names) {
    this.names = names;
  }

  /**
   * obtains the permission default instance from the name.
   *
   * @param name the name to get.
   *
   * @return specified value.
   */
  @NotNull
  public static Optional<PermissionDefault> getByName(@NotNull final String name) {
    return Optional.ofNullable(PermissionDefault.CACHE.get(name.toLowerCase(Locale.ROOT).replaceAll("[^a-z!]", "")));
  }

  /**
   * calculates the value of permission default for the given operator value.
   *
   * @param op the op to calculate.
   *
   * @return {@code true} if the default should be true, or {@code false}
   */
  public boolean getValue(final boolean op) {
    switch (this) {
      case TRUE:
        return true;
      case OP:
        return op;
      case NOT_OP:
        return !op;
      case FALSE:
      default:
        return false;
    }
  }

  @NotNull
  @Override
  public String toString() {
    return this.names[0];
  }
}
