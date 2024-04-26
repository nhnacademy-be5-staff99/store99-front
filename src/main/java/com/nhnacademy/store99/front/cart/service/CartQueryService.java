package com.nhnacademy.store99.front.cart.service;

import com.nhnacademy.store99.front.cart.dto.response.CartItemResponse;
import java.util.List;
import javax.servlet.http.Cookie;

public interface CartQueryService {
    List<CartItemResponse> getSimpleBookListWhenNotLogin(final Cookie cartItemCookie);

}
