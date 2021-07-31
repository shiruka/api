package net.shiruka.api.metadata;

import org.jetbrains.annotations.NotNull;

/**
 * an exception that thrown any time a {@link LazyMetadataValue} fails to evaluate its value due to an exception.
 */
class MetadataEvaluationException extends RuntimeException {

  MetadataEvaluationException(@NotNull final Throwable cause) {
    super(cause);
  }
}
