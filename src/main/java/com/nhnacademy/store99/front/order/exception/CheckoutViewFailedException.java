package com.nhnacademy.store99.front.order.exception;

import com.nhnacademy.store99.front.common.exception.FailedException;

/**
 * @author seunggyu-kim
 */
public class CheckoutViewFailedException extends FailedException {
    public CheckoutViewFailedException(String message) {
        super(message);
    }
}
