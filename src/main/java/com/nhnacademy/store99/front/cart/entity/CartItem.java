package com.nhnacademy.store99.front.cart.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * @author seunggyu-kim
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RedisHash(value = "cart_item", timeToLive = 60 * 60 * 24)
public class CartItem {
    @Id
    private UUID id;

    @Builder.Default
    private Map<Long, Integer> bookIdAndQuantity = new HashMap<>();

    public void addBook(Long bookId, Integer quantity) {
        bookIdAndQuantity.put(bookId, bookIdAndQuantity.getOrDefault(bookId, 0) + quantity);
    }

    public boolean modifyBookQuantity(Long bookId, Integer quantity) {
        if (!bookIdAndQuantity.containsKey(bookId)) {
            return false;
        }
        bookIdAndQuantity.put(bookId, quantity);
        return true;
    }

    public boolean removeBook(Long bookId) {
        return bookIdAndQuantity.remove(bookId) != null;
    }
}
