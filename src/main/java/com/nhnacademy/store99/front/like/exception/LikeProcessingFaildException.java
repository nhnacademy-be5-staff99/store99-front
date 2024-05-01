package com.nhnacademy.store99.front.like.exception;

public class LikeProcessingFaildException extends RuntimeException {
    public LikeProcessingFaildException() {
        super("좋아요 생성 불가");
    }
}
