package com.nhnacademy.store99.front.common.advice;

import com.nhnacademy.store99.front.admin.exception.AdminPermissionDeniedException;
import com.nhnacademy.store99.front.auth.exception.LoginCheckException;
import com.nhnacademy.store99.front.auth.exception.LoginRequiredException;
import com.nhnacademy.store99.front.auth.exception.UnauthorizedFromGatewayException;
import com.nhnacademy.store99.front.common.exception.DefaultFeignClientError;
import com.nhnacademy.store99.front.common.exception.FailedException;
import java.util.stream.Collectors;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
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
     * 권한이 필요하나, gateway server 에서 401 error 를 받았을 때 처리
     *
     * @param ex FeignException.Unauthorized
     * @return error/unauthorized.html
     */
    @ExceptionHandler(value = {UnauthorizedFromGatewayException.class})
    public ModelAndView unauthorizedExceptionHandler(UnauthorizedFromGatewayException ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error/unauthorized");

        return mv;
    }

    /**
     * 로그인 필요시 로그인 화면으로 이동
     *
     * @param ex LoginRequiredException
     * @return login_form.html
     */
    @ExceptionHandler(value = {LoginRequiredException.class})
    public ModelAndView loginRequiredException(LoginRequiredException ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login_form");

        return mv;
    }

    /**
     * LoginStatusCheckInterceptor 처리 중 로그인 상태를 확인할 수 없음
     *
     * <p>토큰은 존재하나 토큰에 문제가 있어 Gateway 에서 401 Error 받음
     * <p>모든 요청 실행 전에 토큰에 문제가 있다고 해서 로그인이 필요하거나 접근 권한이 없는 것은 아니므로 index 반환
     *
     * @param ex LoginCheckException
     * @return index.html
     */
    @ExceptionHandler(value = {LoginCheckException.class})
    public ModelAndView loginCheckFailException(LoginCheckException ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");

        return mv;
    }

    @ExceptionHandler(value = {DefaultFeignClientError.class})
    public ModelAndView defaultFeignClientExceptionHandler(DefaultFeignClientError ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("status", ex.getStatus());
        mv.setViewName("error/default_feignclient_error");

        return mv;
    }

    @ExceptionHandler({FailedException.class})
    public ModelAndView defaultExceptionHandler(FailedException ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", ex.getMessage());
        mv.setViewName("error/default_error");

        return mv;
    }

    @ExceptionHandler(BindException.class)
    public ModelAndView validationExceptionHandler(BindException ex) {
        ModelAndView mv = new ModelAndView();
        String errorMessage = ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        mv.addObject("message", errorMessage);
        mv.setViewName("error/default_error");

        return mv;
    }

}
