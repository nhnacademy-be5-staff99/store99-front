package com.nhnacademy.store99.front.address.exception;

/**
 * @author Ahyeon Song
 */
public class UserAddressOverTenException extends RuntimeException {
    public UserAddressOverTenException(String message) {
        super(message);
    }
}
