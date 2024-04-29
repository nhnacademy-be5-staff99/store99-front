package com.nhnacademy.store99.front.cart.exception;

import com.nhnacademy.store99.front.common.exception.FailedException;

/**
 * @author seunggyu-kimpost
 */
public class DeleteCartFailedException extends FailedException {
    public DeleteCartFailedException() {
        super("장바구니에서 도서 항목 삭제를 실패했습니다.");
    }
}
