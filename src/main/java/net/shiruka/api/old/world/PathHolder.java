package net.shiruka.api.old.world;

import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation of {@link WorldHolder} to hold worlds in a folder.
 */
@RequiredArgsConstructor
public final class PathHolder implements WorldHolder {

  /**
   * the folder.
   */
  @NotNull
  private final Path folder;
}
