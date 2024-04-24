package com.nhnacademy.store99.front.cart.service.impl;

import com.nhnacademy.store99.front.cart.adapter.CartAdapter;
import com.nhnacademy.store99.front.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.front.cart.entity.CartItem;
import com.nhnacademy.store99.front.cart.repository.CartItemRepository;
import com.nhnacademy.store99.front.cart.service.CartService;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 장바구니
 * 로그인이 되어있지 않을 경우 레디스에 장바구니 정보를 저장하고, 쿠키에 저장된 UUID를 키로 접근한다.
 * 로그인이 되어있을 경우 DB에 장바구니 정보를 저장한다.
 *
 * @author seunggyu-kim
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartItemRepository cartItemRepository;
    private final CartAdapter cartAdapter;

    @Override
    public void addToCartDB(final CartItemRequest request) {
        cartAdapter.addBookToCart(request);
    }

    @Override
    public Cookie addToCartRedis(Cookie cartItemCookie, final CartItemRequest request) {
        CartItem cartItem;

        if (Objects.isNull(cartItemCookie)) {
            // 쿠키가 없는 경우
            cartItem = createNewCartItem(request);

            String id = cartItem.getId().toString();
            cartItemCookie = new Cookie("cartItem", id);
        } else {
            // 쿠키가 있는 경우
            String id = cartItemCookie.getValue();

            cartItem = cartItemRepository.findById(UUID.fromString(id)).orElseGet(() -> createNewCartItem(request));
            cartItem.addBook(request.getBookId(), request.getQuantity());

            String newId = cartItem.getId().toString();
            if (!id.equals(newId)) {
                cartItemCookie.setValue(newId);
            }
        }

        cartItemRepository.save(cartItem);
        cartItemCookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(7));

        return cartItemCookie;
    }

    /**
     * 새로운 장바구니 아이템을 생성한다.
     *
     * @param request 장바구니 요청
     * @return 새로운 장바구니 아이템
     */
    private CartItem createNewCartItem(CartItemRequest request) {
        Map<Long, Integer> bookIdAndQuantity = Map.of(request.getBookId(), request.getQuantity());

        UUID id = UUID.randomUUID();

        return CartItem.builder()
                .id(id)
                .bookIdAndQuantity(bookIdAndQuantity)
                .build();
    }
}