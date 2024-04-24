package com.nhnacademy.store99.front.cart.adapter;

import com.nhnacademy.store99.front.cart.dto.request.CartItemRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author seunggyu-kim
 */
@FeignClient(value = "cart-adapter", url = "${gateway.url}/api/bookstore/v1/cart")
public interface CartAdapter {
    @PostMapping("books")
    void addBookToCart(CartItemRequest request);
}
