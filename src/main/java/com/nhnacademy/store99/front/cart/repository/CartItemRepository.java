package com.nhnacademy.store99.front.cart.repository;

import com.nhnacademy.store99.front.cart.entity.CartItem;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 * redis repository
 *
 * @author seunggyu-kim
 */
public interface CartItemRepository extends CrudRepository<CartItem, UUID> {
}
