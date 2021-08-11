package io.github.shiruka.api.version;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents semantic versioning.
 */
@Getter
@EqualsAndHashCode
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Version implements Comparable<Version> {

  /**
   * the build meta.
   */
  @NotNull
  private final List<String> buildMeta;

  /**
   * the major.
   */
  private final int major;

  /**
   * the minor.
   */
  private final int minor;

  /**
   * the patch.
   */
  private final int patch;

  /**
   * the pre release.
   */
  @NotNull
  private final List<String> preRelease;

  /**
   * creates a simple version instance.
   *
   * @param major the major to create.
   * @param minor the minor to create.
   * @param patch the patch to create.
   * @param buildMeta the build meta to create.
   * @param preRelease the pre release to create.
   *
   * @return a newly created version instance.
   */
  @NotNull
  public static Version of(final int major, final int minor, final int patch,
                           @NotNull final List<String> buildMeta, @NotNull final List<String> preRelease) {
    return new Version(buildMeta, major, minor, patch, preRelease);
  }

  /**
   * creates a simple version instance.
   *
   * @param major the major to create.
   * @param minor the minor to create.
   * @param patch the patch to create.
   *
   * @return a newly created version instance.
   */
  @NotNull
  public static Version of(final int major, final int minor, final int patch) {
    return Version.of(major, minor, patch, Collections.emptyList(), Collections.emptyList());
  }

  /**
   * creates a simple version instance.
   *
   * @param major the major to create.
   * @param minor the minor to create.
   *
   * @return a newly created version instance.
   */
  @NotNull
  public static Version of(final int major, final int minor) {
    return Version.of(major, minor, 0);
  }

  /**
   * creates a simple version instance.
   *
   * @param major the major to create.
   *
   * @return a newly created version instance.
   */
  @NotNull
  public static Version of(final int major) {
    return Version.of(major, 0);
  }

  /**
   * creates a simple version instance.
   *
   * @return a newly created version instance.
   */
  @NotNull
  public static Version of() {
    return Version.of(0);
  }

  /**
   * creates a simple version instance.
   *
   * @param versionString the version string to create.
   *
   * @return a newly created version instance.
   *
   * @throws ParseException if the version string does not conform to the semver specs.
   */
  @NotNull
  public static Version of(@NotNull final String versionString) throws ParseException {
    return new Parser(versionString).parse();
  }

  @Override
  public int compareTo(@NotNull final Version o) {
    var result = this.major - o.major;
    if (result != 0) {
      return result;
    }
    result = this.minor - o.minor;
    if (result != 0) {
      return result;
    }
    result = this.patch - o.patch;
    if (result != 0) {
      return result;
    }
    final var thisPreReleaseSize = this.preRelease.size();
    final var oPreReleaseSize = o.preRelease.size();
    if (thisPreReleaseSize == 0 && oPreReleaseSize > 0) {
      result = 1;
    }
    if (oPreReleaseSize == 0 && thisPreReleaseSize > 0) {
      result = -1;
    }
    if (thisPreReleaseSize <= 0 || oPreReleaseSize <= 0) {
      return result;
    }
    final var len = Math.min(thisPreReleaseSize, oPreReleaseSize);
    var count = 0;
    for (count = 0; count < len; count++) {
      result = this.comparePreReleaseTag(count, o);
      if (result != 0) {
        break;
      }
    }
    if (result == 0 && count == len) {
      result = thisPreReleaseSize - oPreReleaseSize;
    }
    return result;
  }

  /**
   * checks if the version has build meta.
   *
   * @param tag the tag to check.
   *
   * @return {@code true} if the version has the build meta.
   */
  public boolean hasBuildMeta(@NotNull final String tag) {
    return this.buildMeta.contains(tag);
  }

  /**
   * checks if the version has pre release.
   *
   * @param tag the tag to check.
   *
   * @return {@code true} if the version has the pre release.
   */
  public boolean hasPreRelease(@NotNull final String tag) {
    return this.preRelease.contains(tag);
  }

  /**
   * convenience method to check if this version is a compatible update.
   *
   * @param version the version to check.
   *
   * @return {@code true} if this version is newer and both have the same major version.
   */
  public boolean isCompatibleUpdateFor(@NotNull final Version version) {
    return this.isUpdateFor(version) && this.major == version.major && this.major != 0;
  }

  /**
   * convenience method to check if this is a stable version.
   *
   * @return {@code true} if the major version number is greater than zero and there are no pre release tags.
   */
  public boolean isStable() {
    return this.major > 0 && this.preRelease.size() == 0;
  }

  /**
   * convenience method to check if this version is an update.
   *
   * @param version the version to check.
   *
   * @return {@code true} if this version is newer than the other one.
   */
  public boolean isUpdateFor(@NotNull final Version version) {
    return this.compareTo(version) > 0;
  }

  @NotNull
  @Override
  public String toString() {
    final var builder = new StringBuilder()
      .append(this.major)
      .append('.')
      .append(this.minor)
      .append('.')
      .append(this.patch);
    final var preReleaseSize = this.preRelease.size();
    if (preReleaseSize > 0) {
      builder.append('-');
      for (var index = 0; index < preReleaseSize; index++) {
        builder.append(this.preRelease.get(index));
        if (index < preReleaseSize - 1) {
          builder.append('.');
        }
      }
    }
    final var buildMetaSize = this.buildMeta.size();
    if (buildMetaSize > 0) {
      builder.append('+');
      for (var index = 0; index < buildMetaSize; index++) {
        builder.append(this.buildMeta.get(index));
        if (index < buildMetaSize - 1) {
          builder.append('.');
        }
      }
    }
    return builder.toString();
  }

  /**
   * compares pre release tag.
   *
   * @param position the position to compare.
   * @param comparing the comparing to compare.
   *
   * @return comparing result.
   */
  private int comparePreReleaseTag(final int position, @NotNull final Version comparing) {
    Integer here = null;
    Integer there = null;
    final var thisPreRelease = this.preRelease.get(position);
    final var comparingPreRelease = comparing.preRelease.get(position);
    try {
      here = Integer.parseInt(thisPreRelease, 10);
    } catch (final NumberFormatException ignored) {
    }
    try {
      there = Integer.parseInt(comparingPreRelease, 10);
    } catch (final NumberFormatException ignored) {
    }
    if (here != null && there == null) {
      return -1;
    }
    if (here == null && there != null) {
      return 1;
    }
    if (here == null) {
      return thisPreRelease.compareTo(comparingPreRelease);
    }
    return here.compareTo(there);
  }

  /**
   * a class that represents parser for semantic versions.
   */
  private static final class Parser {

    /**
     * the input.
     */
    private final char[] input;

    /**
     * the meta parts.
     */
    @NotNull
    private final List<String> metaParts;

    /**
     * the pre release parts.
     */
    @NotNull
    private final List<String> preReleaseParts;

    /**
     * the version parts.
     */
    private final int[] versionParts;

    /**
     * the version string.
     */
    @NotNull
    private final String versionString;

    /**
     * error position.
     */
    private int errorPosition;

    /**
     * ctor.
     *
     * @param versionString the version string.
     */
    private Parser(@NotNull final String versionString) {
      this.versionString = versionString;
      this.input = this.versionString.toCharArray();
      this.metaParts = new ArrayList<>(5);
      this.preReleaseParts = new ArrayList<>(5);
      this.versionParts = new int[3];
    }

    /**
     * parses the version.
     *
     * @return parsed version.
     */
    @NotNull
    private Version parse() throws ParseException {
      if (!this.stateMajor()) {
        throw new ParseException(this.versionString, this.errorPosition);
      }
      return Version.of(
        this.versionParts[0],
        this.versionParts[1],
        this.versionParts[2],
        this.preReleaseParts,
        this.metaParts);
    }

    /**
     * states the major.
     *
     * @return passed or not.
     */
    private boolean stateMajor() {
      var pos = 0;
      while (pos < this.input.length && this.input[pos] >= '0' && this.input[pos] <= '9') {
        pos++;
      }
      if (pos == 0) {
        return false;
      }
      if (this.input[0] == '0' && pos > 1) {
        return false;
      }
      this.versionParts[0] = Integer.parseInt(new String(this.input, 0, pos), 10);
      if (this.input[pos] == '.') {
        return this.stateMinor(pos + 1);
      }
      return false;
    }

    /**
     * states the meta.
     *
     * @param index the index to state.
     *
     * @return passed or not.
     */
    private boolean stateMeta(final int index) {
      var pos = index;
      while (pos < this.input.length
        && (this.input[pos] >= '0' && this.input[pos] <= '9'
        || this.input[pos] >= 'a' && this.input[pos] <= 'z'
        || this.input[pos] >= 'A' && this.input[pos] <= 'Z' || this.input[pos] == '-')) {
        pos++; // match [0..9a-zA-Z-]+
      }
      if (pos == index) {
        this.errorPosition = index;
        return false;
      }
      this.metaParts.add(new String(this.input, index, pos - index));
      if (pos == this.input.length) {
        return true;
      }
      if (this.input[pos] == '.') {
        return this.stateMeta(pos + 1);
      }
      this.errorPosition = pos;
      return false;
    }

    /**
     * states the minor.
     *
     * @param index the index to state.
     *
     * @return passed or not.
     */
    private boolean stateMinor(final int index) {
      var pos = index;
      while (pos < this.input.length && this.input[pos] >= '0' && this.input[pos] <= '9') {
        pos++;
      }
      if (pos == index) {
        this.errorPosition = index;
        return false;
      }
      if (this.input[0] == '0' && pos - index > 1) {
        this.errorPosition = index;
        return false;
      }
      this.versionParts[1] = Integer.parseInt(new String(this.input, index, pos - index), 10);
      if (this.input[pos] == '.') {
        return this.statePatch(pos + 1);
      }
      this.errorPosition = pos;
      return false;
    }

    /**
     * states the patch.
     *
     * @param index the index to state.
     *
     * @return passed or not.
     */
    private boolean statePatch(final int index) {
      var pos = index;
      while (pos < this.input.length && this.input[pos] >= '0' && this.input[pos] <= '9') {
        pos++;
      }
      if (pos == index) {
        this.errorPosition = index;
        return false;
      }
      if (this.input[0] == '0' && pos - index > 1) {
        this.errorPosition = index;
        return false;
      }
      this.versionParts[2] = Integer.parseInt(new String(this.input, index, pos - index), 10);
      if (pos == this.input.length) {
        return true;
      }
      if (this.input[pos] == '+') {
        return this.stateMeta(pos + 1);
      }
      if (this.input[pos] == '-') {
        return this.stateRelease(pos + 1);
      }
      this.errorPosition = pos;
      return false;
    }

    /**
     * states the release.
     *
     * @param index the index to state.
     *
     * @return passed or not.
     */
    private boolean stateRelease(final int index) {
      var pos = index;
      while (pos < this.input.length
        && (this.input[pos] >= '0' && this.input[pos] <= '9'
        || this.input[pos] >= 'a' && this.input[pos] <= 'z'
        || this.input[pos] >= 'A' && this.input[pos] <= 'Z' || this.input[pos] == '-')) {
        pos++;
      }
      if (pos == index) {
        this.errorPosition = index;
        return false;
      }
      this.preReleaseParts.add(new String(this.input, index, pos - index));
      if (pos == this.input.length) {
        return true;
      }
      if (this.input[pos] == '.') {
        return this.stateRelease(pos + 1);
      }
      if (this.input[pos] == '+') {
        return this.stateMeta(pos + 1);
      }
      this.errorPosition = pos;
      return false;
    }
  }
}
