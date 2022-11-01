package io.github.shiruka.api.exception;

import lombok.experimental.StandardException;

/**
 * an exception class that thrown an error occurs when posting an event.
 */
@StandardException
public final class PostEventException extends RuntimeException {}
