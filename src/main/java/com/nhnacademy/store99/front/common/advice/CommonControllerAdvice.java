package com.nhnacademy.store99.front.common.advice;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * front 에서 발생하는 전역적인 에러를 처리
 *
 * @author Ahyeon Song
 */
@ControllerAdvice
public class CommonControllerAdvice {

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
