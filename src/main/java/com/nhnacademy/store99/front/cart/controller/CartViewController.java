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
        if ((boolean) servletRequest.getAttribute("isLogin")) {
            // 로그인한 경우
        } else {
            // 비로그인한 경우
            List<CartItemResponse> cartItems = cartQueryService.getSimpleBookListWhenNotLogin(cartItem);
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalPrice",
                    cartItems.stream().map(o -> o.getBookSalePrice() * o.getQuantity()).reduce(0, Integer::sum));
        }
        return "cart/cart";
    }
}
