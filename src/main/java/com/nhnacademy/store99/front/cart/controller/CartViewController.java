package com.nhnacademy.store99.front.cart.controller;

import com.nhnacademy.store99.front.cart.dto.response.CartItemResponse;
import com.nhnacademy.store99.front.cart.service.CartQueryService;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 장바구니 뷰 반환용 컨트롤러
 *
 * @author seunggyu-kim
 */
@Controller
@RequiredArgsConstructor
public class CartViewController {
    private final CartQueryService cartQueryService;

    @GetMapping("/cart")
    public String cart(@CookieValue(required = false) Cookie cartItem, HttpServletRequest servletRequest, Model model) {
        List<CartItemResponse> cartItems;
        if ((boolean) servletRequest.getAttribute("isLogin")) {
            // 로그인한 경우
            cartItems = cartQueryService.getCartItemsWhenLongin();
        } else {
            // 비로그인한 경우
            cartItems = cartQueryService.getCartItemsWhenNotLogin(cartItem);
        }
        model.addAttribute("cartItems", cartItems);
        return "cart/cart";
    }
}
