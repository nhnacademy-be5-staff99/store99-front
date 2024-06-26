package com.nhnacademy.store99.front.cart.service;

import com.nhnacademy.store99.front.cart.dto.response.CartItemResponse;
import java.util.List;
import javax.servlet.http.Cookie;

/**
 * @author seunggyu-kimpost
 */
public interface CartQueryService {
    List<CartItemResponse> getCartItemsWhenNotLogin(final Cookie cartItemCookie);

    List<CartItemResponse> getCartItemsWhenLongin();
}
