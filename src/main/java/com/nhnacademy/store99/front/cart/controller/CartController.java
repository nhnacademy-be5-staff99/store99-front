package com.nhnacademy.store99.front.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 장바구니 뷰 컨트롤러
 * <p>장바구니 페이지를 보여주는 컨트롤러
 *
 * @author seunggyu-kim
 */
@RequiredArgsConstructor
@Controller
public class CartController {
    @GetMapping("/cart")
    public String cart() {
        return "cart/cart";
    }
}
