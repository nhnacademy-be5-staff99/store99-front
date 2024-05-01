package com.nhnacademy.store99.front.common.interceptor;

import com.nhnacademy.store99.front.auth.service.AdminCheckService;
import com.nhnacademy.store99.front.common.thread_local.XUserTokenThreadLocal;
import com.nhnacademy.store99.front.common.util.CookieUtils;
import feign.FeignException;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 모든 Controller 실행 전에 Cookie 유무를 확인하고 로그인 상태를 판단하는 Interceptor
 *
 * @author Ahyeon Song
 * @author seunggyu-kim
 */
@Slf4j
public class LoginStatusCheckInterceptor implements HandlerInterceptor {

    private final AdminCheckService adminCheckService;

    public LoginStatusCheckInterceptor(ObjectProvider<AdminCheckService> objectProvider) {
        this.adminCheckService = objectProvider.getIfUnique();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.debug("LoginStatusCheckInterceptor > preHandle > request.getRequestURI() 요청 주소: {}",
                request.getRequestURI());
        Cookie xUserTokenCookie = CookieUtils.getCookie(request, "X-USER-TOKEN");

        if (Objects.nonNull(xUserTokenCookie)) {
            log.debug("X-USER-TOKEN Cookie : {}", xUserTokenCookie);
            XUserTokenThreadLocal.setXUserToken(xUserTokenCookie.getValue());

            boolean isAdmin;
            try {
                XUserTokenThreadLocal.setXUserToken(xUserTokenCookie.getValue());

                isAdmin = adminCheckService.checkAdmin();

            } catch (FeignException.BadRequest | FeignException.Unauthorized ex) {
                log.debug("로그인 상태 확인 에러 : 토큰은 존재하나 Gateway 에서 {} Error 받음 (Token 문제)", ex.status());
                request.setAttribute("isLogin", false);

                CookieUtils.deleteCookie(request, response, "X-USER-TOKEN");
                log.debug("사용 불가능한 X-USER-TOKEN Cookie 삭제");

                XUserTokenThreadLocal.reset();
                log.debug("사용 불가능한 X-USER-TOKEN ThreadLocal 삭제");

                request.setAttribute("isLogin", false);
                request.setAttribute("isAdmin", false);

                return HandlerInterceptor.super.preHandle(request, response, handler);
            }

            log.debug("로그인 상태 확인 성공");
            request.setAttribute("isLogin", true);

            if (!isAdmin) {
                log.debug("사용자 권한 : USER");
                request.setAttribute("isAdmin", false);

            } else {
                log.debug("사용자 권한 : ADMIN");
                request.setAttribute("isAdmin", true);
            }

        } else {
            log.debug("로그아웃 상태 : X-USER-TOKEN Cookie 가 존재하지 않음");
            request.setAttribute("isLogin", false);
            request.setAttribute("isAdmin", false);
        }


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                                final Object handler, final Exception ex)
            throws Exception {
        XUserTokenThreadLocal.reset();
    }
}
