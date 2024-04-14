package com.nhnacademy.store99.front.admin.exception;

public class AdminPermissionDeniedException extends RuntimeException {
    public AdminPermissionDeniedException() {
        super("관리자 권한이 없습니다.");
    }
}
