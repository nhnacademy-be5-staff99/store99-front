package com.nhnacademy.store99.front.common.exception;

/**
 * @author Ahyeon Song
 */
public class DefaultFeignClientError extends RuntimeException {
    public DefaultFeignClientError(String message) {
        super(message);
    }
}
