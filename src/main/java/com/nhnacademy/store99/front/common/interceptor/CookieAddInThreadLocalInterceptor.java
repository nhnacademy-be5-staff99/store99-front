package com.nhnacademy.store99.front.common.interceptor;

import com.nhnacademy.store99.front.common.thread_local.XUserTokenThreadLocal;
import com.nhnacademy.store99.front.common.util.CookieUtils;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * /books, /mypage, 등의 요청 시에 X-USER-TOKEN cookie 를 thread local 에 저장 하는 interceptor
 *
 * @author Ahyeon Song
 */
public class CookieAddInThreadLocalInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Cookie xUserTokenCookie = CookieUtils.getCookie(request, "X-USER-TOKEN");

        if (Objects.isNull(xUserTokenCookie)){
            response.sendRedirect("/error/bad_request");
            return false;
        }

        XUserTokenThreadLocal.setXUserToken(xUserTokenCookie.getValue());

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        XUserTokenThreadLocal.reset();
    }
}
