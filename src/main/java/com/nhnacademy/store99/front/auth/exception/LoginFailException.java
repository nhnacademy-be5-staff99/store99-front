package com.nhnacademy.store99.front.auth.exception;

/**
 * @author Ahyeon Song
 */
public class LoginFailException extends RuntimeException {
    public LoginFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
