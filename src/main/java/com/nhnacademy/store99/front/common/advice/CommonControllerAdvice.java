package com.nhnacademy.store99.front.common.advice;

import com.nhnacademy.store99.front.admin.exception.AdminPermissionDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 공통 예외 처리
 *
 * @author seunggyu-kim
 */
@ControllerAdvice
public class CommonControllerAdvice {
    /**
     * AdminPermissionDeniedException Handler
     * <p>403 FORBIDDEN 에러를 반환할 경우 AdminPermissionDeniedException을 상속받아서 사용
     *
     * @param ex NotFoundException
     * @return 404 NOT_FOUND
     */
    @ExceptionHandler(AdminPermissionDeniedException.class)
    public String notFoundExceptionHandler(AdminPermissionDeniedException ex) {
        return "admin/error/forbidden";
    }
}
