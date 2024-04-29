package com.nhnacademy.store99.front.cart.exception;

import com.nhnacademy.store99.front.common.exception.FailedException;

public class ModifyCartFailedException extends FailedException {
    public ModifyCartFailedException() {
        super("장바구니에 없는 도서를 수정하려고 합니다.");
    }
}
