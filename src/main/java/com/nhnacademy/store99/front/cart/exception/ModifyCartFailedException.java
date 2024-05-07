package com.nhnacademy.store99.front.cart.exception;

import com.nhnacademy.store99.front.common.exception.FailedException;

/**
 * @author seunggyu-kimpost
 */
public class ModifyCartFailedException extends FailedException {
    public ModifyCartFailedException() {
        super("장바구니에서 도서 수량 변경을 실패했습니다.");
    }
}
