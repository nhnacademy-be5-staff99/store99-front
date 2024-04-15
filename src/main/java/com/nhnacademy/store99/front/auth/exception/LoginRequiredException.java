package com.nhnacademy.store99.front.auth.exception;

public class LoginRequiredException extends RuntimeException {
    public LoginRequiredException(String message) {
        super(message);
    }
}
