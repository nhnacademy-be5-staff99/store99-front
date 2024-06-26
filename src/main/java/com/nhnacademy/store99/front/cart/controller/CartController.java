package com.nhnacademy.store99.front.cart.controller;

import com.nhnacademy.store99.front.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.front.cart.service.CartService;
import com.nhnacademy.store99.front.common.util.CookieUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 장바구니 뷰 컨트롤러
 * <p>장바구니 페이지를 보여주는 컨트롤러
 *
 * @author seunggyu-kim
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @ResponseBody
    @PostMapping("/cart/books")
    public void addBookToCart(@RequestBody @Valid CartItemRequest request, HttpServletRequest servletRequest,
                              HttpServletResponse servletResponse) {
        if ((boolean) servletRequest.getAttribute("isLogin")) {
            // 로그인한 경우
            cartService.addToCartDB(request);
        } else {
            // 비로그인한 경우
            Cookie cookie = CookieUtils.getCookie(servletRequest, "cartItem");
            cookie = cartService.addToCartRedis(cookie, request);
            servletResponse.addCookie(cookie);
        }
    }

    @PutMapping("/cart/books")
    public String updateBookQuantityInCart(@ModelAttribute @Valid CartItemRequest request,
                                           @RequestAttribute boolean isLogin,
                                           @CookieValue(value = "cartItem", required = false) Cookie cartItemCookie,
                                           HttpServletResponse servletResponse) {
        if (isLogin) {
            // 로그인한 경우
            cartService.modifyBookQuantityInCartWhenLogin(request);
        } else {
            // 비로그인한 경우
            cartService.modifyBookQuantityInCartWhenNotLogin(cartItemCookie, request);
            servletResponse.addCookie(cartItemCookie);
        }

        return "redirect:/cart";
    }

    @DeleteMapping("/cart/books/{bookId}")
    public String deleteBookInCart(@PathVariable Long bookId,
                                   @RequestAttribute boolean isLogin,
                                   @CookieValue(value = "cartItem", required = false) Cookie cartItemCookie,
                                   HttpServletResponse servletResponse) {
        if (isLogin) {
            // 로그인한 경우
            cartService.removeBookInCartWhenLogin(bookId);
        } else {
            // 비로그인한 경우
            cartService.removeBookInCartWhenNotLogin(cartItemCookie, bookId);
            servletResponse.addCookie(cartItemCookie);
        }

        return "redirect:/cart";
    }
}
