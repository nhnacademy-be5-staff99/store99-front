package com.nhnacademy.store99.front.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CartController {
    @GetMapping("/cart")
    public String cart() {
        return "cart/cart";
    }
}
