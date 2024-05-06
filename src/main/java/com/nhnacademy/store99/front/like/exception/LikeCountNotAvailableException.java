package com.nhnacademy.store99.front.like.exception;

public class LikeCountNotAvailableException extends RuntimeException {

    public LikeCountNotAvailableException() {
        super("좋아요 카운팅 실패");
    }
}
