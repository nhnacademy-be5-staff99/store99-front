package com.nhnacademy.store99.front.common.advice;

import com.nhnacademy.store99.front.admin.exception.AdminPermissionDeniedException;
import feign.FeignException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * front 에서 발생하는 전역적인 에러를 처리
 *
 * @author seunggyu-kim
 * @author Ahyeon Song
 */
@ControllerAdvice
public class CommonControllerAdvice {
    /**
     * AdminPermissionDeniedException Handler
     * <p>403 FORBIDDEN 에러를 반환할 경우 AdminPermissionDeniedException을 상속받아서 사용
     *
     * @param ex AdminPermissionDeniedException
     * @return 403 FORBIDDEN 에러 페이지
     */
    @ExceptionHandler(AdminPermissionDeniedException.class)
    public String adminPermissionDeniedExceptionHandler(AdminPermissionDeniedException ex) {
        return "admin/error/forbidden";
    }

    /**
     * gateway server 에서 401 error 를 받았을 때 처리
     *
     * @param ex
     * @return error/unauthorized.html
     */
    @ExceptionHandler(value = {FeignException.Unauthorized.class})
    public ModelAndView unauthorizedExceptionHandler(FeignException.Unauthorized ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error/unauthorized");

        return mv;
    }
}
