package com.nhnacademy.store99.front.auth.exception;

/**
 * feign client 에서 받은 unauthorized exception
 *
 * @author Ahyeon Song
 */
public class UnauthorizedFromGatewayException extends RuntimeException {
    public UnauthorizedFromGatewayException(String message) {
        super(message);
    }
}
