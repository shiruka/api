package io.github.shiruka.api.exception;

import lombok.experimental.StandardException;

/**
 * an exception class that thrown when attempting to load an invalid plugin file.
 */
@StandardException
public final class InvalidPluginException extends Exception {}
