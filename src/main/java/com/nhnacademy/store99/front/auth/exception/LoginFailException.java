package com.nhnacademy.store99.front.auth.exception;

public class LoginFailException extends RuntimeException {
    public LoginFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
