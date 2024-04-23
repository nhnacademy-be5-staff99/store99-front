package com.nhnacademy.store99.front.common.exception;

import lombok.Getter;

/**
 * @author Ahyeon Song
 * @author seunggyu-kim
 */
public class DefaultFeignClientError extends RuntimeException {
    @Getter
    private final int status;

    public DefaultFeignClientError(String message, int status) {
        super(message);
        this.status = status;
    }
}
