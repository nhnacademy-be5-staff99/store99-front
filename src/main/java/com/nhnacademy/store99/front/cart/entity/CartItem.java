package com.nhnacademy.store99.front.cart.entity;

import java.util.Map;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RedisHash(value = "cart_item", timeToLive = 60 * 60 * 24 * 7)
public class CartItem {
    @Id
    private UUID id;

    private Map<Long, Integer> bookIdAndQuantity;

    public void addBook(Long bookId, Integer quantity) {
        bookIdAndQuantity.put(bookId, bookIdAndQuantity.getOrDefault(bookId, 0) + quantity);
    }
}
