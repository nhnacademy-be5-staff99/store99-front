package com.nhnacademy.store99.front.common.exception;

public class FailedException extends RuntimeException {
    public FailedException(String message) {
        super(message);
    }

    public FailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
