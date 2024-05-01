package com.nhnacademy.store99.front.auth.controller;

import com.nhnacademy.store99.front.auth.cookie.CookieSecurityProperties;
import com.nhnacademy.store99.front.auth.dto.LoginRequest;
import com.nhnacademy.store99.front.auth.service.LoginService;
import com.nhnacademy.store99.front.cart.service.CartService;
import com.nhnacademy.store99.front.common.response.CommonHeader;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import java.net.URI;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ahyeon Song
 * @author seunggyu-kim
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final CookieSecurityProperties cookieSecurityProperties;
    private final CartService cartService;

    /**
     * 로그인 페이지 진입
     *
     * @return login_form.html
     */
    @GetMapping("/login_form")
    public ModelAndView getLoginForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login_form");

        return mv;
    }

    /**
     * 로그인 요청을 보내는 메소드
     * <p>요청의 return 은 json 형태로, login_form 의 java script ajax 로 넘겨진다
     * <p>로그인 성공 시 : header 에 cookie 를 담아 index 로 redirect 된다
     * <p>로그인 실패 시 : alert 로 로그인 실패 메세지를 띄운다
     *
     * @param request (email, password)
     * @return cookie 를 포함한 response
     */
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<String>> doLogin(@ModelAttribute LoginRequest request,
                                                          HttpServletResponse servletResponse,
                                                          @CookieValue(value = "cartItem", required = false)
                                                          Cookie cartItemCookie) {
        String accessToken = loginService.doLogin(request);
        accessToken = accessToken.replace(" ", "");

        CommonHeader header = CommonHeader.builder()
                .httpStatus(HttpStatus.OK)
                .resultMessage("Login success")
                .build();

        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .header(header)
                .result("로그인 성공")
                .build();
        ResponseCookie cookie = ResponseCookie.from("X-USER-TOKEN", accessToken)
                .httpOnly(true)
                .secure(cookieSecurityProperties.isSecure())
                .path("/")
                .build();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.SET_COOKIE, cookie.toString());
        responseHeaders.setLocation(URI.create("/index"));

        cartService.mergeCart(accessToken, cartItemCookie);

        return new ResponseEntity<>(commonResponse, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ModelAndView doLogout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        loginService.doLogout(request, response);

        mv.setViewName("redirect:");
        return mv;
    }


}
