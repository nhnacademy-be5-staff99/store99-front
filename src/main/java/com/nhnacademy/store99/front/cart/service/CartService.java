package com.nhnacademy.store99.front.cart.service;

import com.nhnacademy.store99.front.cart.dto.request.CartItemRequest;
import javax.servlet.http.Cookie;

public interface CartService {
    void addToCartDB(final CartItemRequest request);

    Cookie addToCartRedis(Cookie cartItemCookie, final CartItemRequest request);
}
