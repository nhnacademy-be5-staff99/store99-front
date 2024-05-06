package com.nhnacademy.store99.front.cart.service;

import com.nhnacademy.store99.front.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.front.cart.exception.DeleteCartFailedException;
import com.nhnacademy.store99.front.cart.exception.ModifyCartFailedException;
import javax.servlet.http.Cookie;

/**
 * @author seunggyu-kim
 */
public interface CartService {
    void addToCartDB(final CartItemRequest request);

    Cookie addToCartRedis(Cookie cartItemCookie, final CartItemRequest request);

    void modifyBookQuantityInCartWhenLogin(final CartItemRequest request) throws ModifyCartFailedException;

    void modifyBookQuantityInCartWhenNotLogin(final Cookie cartItemCookie, final CartItemRequest request)
            throws ModifyCartFailedException;

    void removeBookInCartWhenLogin(final Long bookId) throws DeleteCartFailedException;

    void removeBookInCartWhenNotLogin(final Cookie cartItemCookie, final Long bookId)
            throws DeleteCartFailedException;

    void mergeCart(final String accessToken, Cookie cartItemCookie);
}
