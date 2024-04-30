package com.nhnacademy.store99.front.cart.adapter;

import com.nhnacademy.store99.front.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.front.cart.dto.response.CartItemResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author seunggyu-kim
 */
@FeignClient(value = "cart-adapter", url = "${gateway.url}/api/bookstore/v1/cart/books")
public interface CartAdapter {
    @PostMapping
    CommonResponse<Void> addBookToCart(@RequestBody CartItemRequest request);

    @GetMapping
    CommonResponse<List<CartItemResponse>> getCartItemsByUser();

    @PutMapping
    CommonResponse<Void> modifyBookQuantityInCart(@RequestBody CartItemRequest request);

    @DeleteMapping("/{bookId}")
    CommonResponse<Void> removeBookFromCart(@PathVariable Long bookId);

    @PostMapping("/merge")
    CommonResponse<Void> mergeCart(@RequestHeader("X-USER-TOKEN") String accessToken,
                                   @RequestBody Map<Long, Integer> bookIdAndQuantity);
}
