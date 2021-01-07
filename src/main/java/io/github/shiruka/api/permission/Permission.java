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

package io.github.shiruka.api.permission;

import com.google.common.base.Preconditions;
import io.github.shiruka.api.Shiruka;
import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents a unique permission that may be attached to a {@link Permissible}.
 */
public final class Permission {

  /**
   * the default permission.
   */
  static final PermissionDefault DEFAULT_PERMISSION = PermissionDefault.OP;

  /**
   * the children.
   */
  private final Map<String, Boolean> children;

  /**
   * the name.
   */
  @NotNull
  private final String name;

  /**
   * the default value.
   */
  @NotNull
  private PermissionDefault defaultValue;

  /**
   * the description.
   */
  @NotNull
  private String description;

  /**
   * ctor.
   *
   * @param name the name.
   * @param description the description.
   * @param defaultValue the default value.
   * @param children the children.
   */
  public Permission(@NotNull final String name, @NotNull final String description,
                    @NotNull final PermissionDefault defaultValue, @NotNull final Map<String, Boolean> children) {
    this.name = name;
    this.description = description;
    this.defaultValue = defaultValue;
    //noinspection AssignmentOrReturnOfFieldWithMutableType
    this.children = children;
    this.recalculatePermissibles();
  }

  /**
   * ctor.
   *
   * @param name the name.
   * @param description the description.
   */
  public Permission(@NotNull final String name, @NotNull final String description) {
    this(name, description, Permission.DEFAULT_PERMISSION, new LinkedHashMap<>());
  }

  /**
   * ctor.
   *
   * @param name the name.
   * @param defaultValue the default value.
   */
  public Permission(@NotNull final String name, @NotNull final PermissionDefault defaultValue) {
    this(name, "", defaultValue, new LinkedHashMap<>());
  }

  /**
   * ctor.
   *
   * @param name the name.
   * @param description the description.
   * @param defaultValue the default value.
   */
  public Permission(@NotNull final String name, @NotNull final String description,
                    @NotNull final PermissionDefault defaultValue) {
    this(name, description, defaultValue, new LinkedHashMap<>());
  }

  /**
   * ctor.
   *
   * @param name the name.
   * @param description the description.
   * @param children the children.
   */
  public Permission(@NotNull final String name, @NotNull final String description,
                    @NotNull final Map<String, Boolean> children) {
    this(name, description, Permission.DEFAULT_PERMISSION, children);
  }

  /**
   * ctor.
   *
   * @param name the name.
   * @param defaultValue the default value.
   * @param children the children.
   */
  public Permission(@NotNull final String name, @NotNull final PermissionDefault defaultValue,
                    @NotNull final Map<String, Boolean> children) {
    this(name, "", defaultValue, children);
  }

  /**
   * ctor.
   *
   * @param name the name.
   * @param children the children.
   */
  public Permission(@NotNull final String name, @NotNull final Map<String, Boolean> children) {
    this(name, Permission.DEFAULT_PERMISSION, children);
  }

  /**
   * ctor.
   *
   * @param name the name.
   */
  public Permission(@NotNull final String name) {
    this(name, new LinkedHashMap<>());
  }

  /**
   * loads a permission from a map of data, usually used from retrieval from a yaml file.
   *
   * @param name the name to load.
   * @param data the data to load.
   *
   * @return permission object.
   */
  @NotNull
  public static Permission loadPermission(@NotNull final String name, @NotNull final Map<String, Object> data) {
    return Permission.loadPermission(name, data, Permission.DEFAULT_PERMISSION, null);
  }

  /**
   * loads a permission from a map of data, usually used from retrieval from a yaml file.
   *
   * @param name the name to load.
   * @param data the data to load.
   * @param def the default to load.
   * @param output the output to load.
   *
   * @return permission object.
   */
  @NotNull
  public static Permission loadPermission(@NotNull final String name, @NotNull final Map<?, ?> data,
                                          @Nullable PermissionDefault def, @Nullable final List<Permission> output) {
    var desc = "";
    var children = new LinkedHashMap<String, Boolean>();
    final var defaultValue = data.get("default");
    if (defaultValue != null) {
      final var value = PermissionDefault.getByName(defaultValue.toString());
      Preconditions.checkArgument(value.isPresent(), "'default' key contained unknown value");
      def = value.get();
    }
    final var childrenValue = data.get("children");
    if (childrenValue != null) {
      if (childrenValue instanceof Iterable) {
        children = new LinkedHashMap<>();
        for (final var child : (Iterable<?>) childrenValue) {
          if (child != null) {
            children.put(child.toString(), Boolean.TRUE);
          }
        }
      } else if (childrenValue instanceof Map) {
        children = Permission.extractChildren((Map<?, ?>) childrenValue, name, def, output);
      } else {
        throw new IllegalArgumentException("'children' key is of wrong type");
      }
    }
    final var descriptionValue = data.get("description");
    if (descriptionValue != null) {
      desc = descriptionValue.toString();
    }
    final var finalDefault = def == null
      ? Permission.DEFAULT_PERMISSION
      : def;
    return new Permission(name, desc, finalDefault, children);
  }

  /**
   * loads a list of permissions from a map of data, usually used from retrieval from a yaml file.
   *
   * @param data the data to load.
   * @param error the error to load.
   * @param def the default to load.
   *
   * @return permission object.
   */
  @NotNull
  public static List<Permission> loadPermissions(@NotNull final Map<?, ?> data, @NotNull final String error,
                                                 @Nullable final PermissionDefault def) {
    final var result = new ArrayList<Permission>();
    data.forEach((key, value) -> {
      try {
        result.add(Permission.loadPermission(key.toString(), (Map<?, ?>) value, def, result));
      } catch (final Throwable ex) {
        Shiruka.getLogger().fatal(String.format(error, key), ex);
      }
    });
    return result;
  }

  /**
   * extracts the {@code input} into a new children map.
   *
   * @param input the input to extract.
   * @param name the name to extract.
   * @param def the def to extract.
   * @param output the output to extract.
   *
   * @return extracted children.
   */
  @NotNull
  private static LinkedHashMap<String, Boolean> extractChildren(@NotNull final Map<?, ?> input,
                                                                @NotNull final String name,
                                                                @Nullable final PermissionDefault def,
                                                                @Nullable final List<Permission> output) {
    final var children = new LinkedHashMap<String, Boolean>();
    input.forEach((key, value) -> {
      if (value instanceof Boolean) {
        children.put(key.toString(), (Boolean) value);
        return;
      }
      Preconditions.checkArgument(value instanceof Map, "Child '%s' contains invalid value!",
        key.toString());
      try {
        final var perm = Permission.loadPermission(key.toString(), (Map<?, ?>) value,
          def, output);
        children.put(perm.getName(), Boolean.TRUE);
        if (output != null) {
          output.add(perm);
        }
      } catch (final Throwable ex) {
        throw new IllegalArgumentException(String.format("Permission node '%s' in child of %s is invalid!",
          key.toString(), name), ex);
      }
    });
    return children;
  }

  /**
   * adds this permission to the specified parent permission.
   *
   * @param name the name to add.
   * @param value the value to add.
   *
   * @return parent permission it created or loaded.
   */
  @NotNull
  public Permission addParent(@NotNull final String name, final boolean value) {
    final var pm = Shiruka.getPermissionManager();
    final var lname = name.toLowerCase(Locale.ROOT);
    final var perm = pm.getPermission(lname);
    final Permission permission;
    if (perm.isEmpty()) {
      permission = new Permission(lname);
      pm.addPermission(permission);
    } else {
      permission = perm.get();
    }
    this.addParent(permission, value);
    return permission;
  }

  /**
   * adds this permission to the specified parent permission.
   *
   * @param perm the perm to add.
   * @param value the value to add.
   */
  public void addParent(@NotNull final Permission perm, final boolean value) {
    perm.putChildren(this.getName(), value);
    perm.recalculatePermissibles();
  }

  /**
   * gets the children of this permission.
   *
   * @return permission children.
   */
  @NotNull
  public Map<String, Boolean> getChildren() {
    return Collections.unmodifiableMap(this.children);
  }

  /**
   * gets the default value of this permission.
   *
   * @return default value of this permission.
   */
  @NotNull
  public PermissionDefault getDefault() {
    return this.defaultValue;
  }

  /**
   * sets the default value of this permission.
   *
   * @param value the value to set.
   */
  public void setDefault(@NotNull final PermissionDefault value) {
    this.defaultValue = value;
    this.recalculatePermissibles();
  }

  /**
   * gets a brief description of this permission, may be empty.
   *
   * @return brief description of this permission.
   */
  @NotNull
  public String getDescription() {
    return this.description;
  }

  /**
   * sets the description of this permission.
   *
   * @param value the value to set.
   */
  public void setDescription(@NotNull final String value) {
    this.description = value;
  }

  /**
   * returns the unique fully qualified name of this permission.
   *
   * @return fully qualified name.
   */
  @NotNull
  public String getName() {
    return this.name;
  }

  /**
   * gets a set containing every {@link Permissible} that has this permission.
   *
   * @return set containing permissibles with this permission.
   */
  @NotNull
  public Set<Permissible> getPermissibles() {
    return Shiruka.getPermissionManager().getPermissionSubscriptions(this.name);
  }

  /**
   * puts the given {@code name} and {@code value} into {@link #children}.
   *
   * @param name the name to put.
   * @param value the value to put.
   */
  public void putChildren(@NotNull final String name, final boolean value) {
    this.children.put(name, value);
  }

  /**
   * recalculates all {@link Permissible}s that contain this permission.
   */
  public void recalculatePermissibles() {
    final var perms = this.getPermissibles();
    Shiruka.getPermissionManager().recalculatePermissionDefaults(this);
    perms.forEach(Permissible::recalculatePermissions);
  }
}
