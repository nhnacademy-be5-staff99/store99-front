package com.nhnacademy.store99.front.cart.adapter;

import com.nhnacademy.store99.front.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.front.cart.dto.response.CartItemResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author seunggyu-kim
 */
@FeignClient(value = "cart-adapter", url = "${gateway.url}/api/bookstore/v1/cart")
public interface CartAdapter {
    @PostMapping("/books")
    CommonResponse<Void> addBookToCart(CartItemRequest request);

    @GetMapping("/books")
    CommonResponse<List<CartItemResponse>> getCartItemsByUser();

    @PutMapping("/books")
    CommonResponse<Void> modifyBookQuantityInCart(CartItemRequest request);

    @DeleteMapping("/books/{bookId}")
    CommonResponse<Void> removeBookFromCart(@PathVariable Long bookId);
}
