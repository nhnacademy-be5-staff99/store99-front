package com.nhnacademy.store99.front.common.interceptor;

import com.nhnacademy.store99.front.common.thread_local.XUserTokenThreadLocal;
import com.nhnacademy.store99.front.common.util.CookieUtils;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * X-USER-TOKEN 쿠키 검사 인터셉터
 * <p>/admin/** url로 접근할 경우, X-USER-TOKEN이 있는지, 그리고 해당 토큰이 관리자 토큰인지 검사한다.</p>
 * <p>X-USER-TOKEN은 ThreadLocal을 통하여 관리한다.</p>
 *
 * @author seunggyu-kim
 */
@Component
public class XUserTokenCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws Exception {
        Cookie xUserToken = CookieUtils.getCookie(request, "X-USER-TOKEN");

        if (Objects.isNull(xUserToken)) {
            response.sendRedirect("/admin/error/forbidden");
            return false;
        }

        XUserTokenThreadLocal.setXUserToken(xUserToken.getValue());

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
                           final ModelAndView modelAndView) throws Exception {
        XUserTokenThreadLocal.reset();
    }
}
