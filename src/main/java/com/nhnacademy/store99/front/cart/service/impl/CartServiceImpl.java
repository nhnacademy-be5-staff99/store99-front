package com.nhnacademy.store99.front.cart.service.impl;

import com.nhnacademy.store99.front.cart.adapter.CartAdapter;
import com.nhnacademy.store99.front.cart.dto.request.CartItemRequest;
import com.nhnacademy.store99.front.cart.entity.CartItem;
import com.nhnacademy.store99.front.cart.exception.DeleteCartFailedException;
import com.nhnacademy.store99.front.cart.exception.ModifyCartFailedException;
import com.nhnacademy.store99.front.cart.repository.CartItemRedisRepository;
import com.nhnacademy.store99.front.cart.service.CartService;
import com.nhnacademy.store99.front.common.exception.FailedException;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 장바구니
 * 로그인이 되어있지 않을 경우 레디스에 장바구니 정보를 저장하고, 쿠키에 저장된 UUID를 키로 접근한다.
 * 로그인이 되어있을 경우 DB에 장바구니 정보를 저장한다.
 *
 * @author seunggyu-kim
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartItemRedisRepository cartItemRedisRepository;
    private final CartAdapter cartAdapter;

    @Override
    public void addToCartDB(final CartItemRequest request) {
        CommonResponse<Void> response = cartAdapter.addBookToCart(request);
        if (!response.getHeader().isSuccessful()) {
            throw new FailedException("장바구니에 도서를 추가하는 데 실패했습니다.");
        }
    }

    @Override
    public Cookie addToCartRedis(Cookie cartItemCookie, final CartItemRequest request) {
        CartItem cartItem;

        if (Objects.isNull(cartItemCookie)) {
            // 쿠키가 없는 경우 새로운 장바구니 아이템 생성
            UUID redisKey = UUID.randomUUID();
            cartItem = CartItem.builder()
                    .id(redisKey)
                    .build();
            cartItemCookie = new Cookie("cartItem", redisKey.toString());
        } else {
            // 쿠키가 있는 경우
            String redisKey = cartItemCookie.getValue();

            // 레디스에 저장된 장바구니 정보를 가져오거나 새로운 장바구니 아이템 생성
            try {
                cartItem = cartItemRedisRepository.findById(UUID.fromString(redisKey))
                        .orElseGet(() -> CartItem.builder().id(UUID.randomUUID()).build());
            } catch (IllegalArgumentException e) {
                // 쿠키에 저장된 값이 UUID가 아닌 경우
                cartItem = CartItem.builder().id(UUID.randomUUID()).build();
            }

            // 쿠키에 담긴 키와 레디스에 저장된 키가 다르면 쿠키에 새로운 키를 저장
            String newRedisKey = cartItem.getId().toString();
            if (!redisKey.equals(newRedisKey)) {
                cartItemCookie.setValue(newRedisKey);
            }
        }

        cartItem.addBook(request.getBookId(), request.getQuantity());
        cartItemRedisRepository.save(cartItem);
        cartItemCookie.setPath("/");
        return cartItemCookie;
    }

    @Override
    public void modifyBookQuantityInCartWhenLogin(final CartItemRequest request) throws ModifyCartFailedException {
        CommonResponse<Void> response = cartAdapter.modifyBookQuantityInCart(request);
        if (!response.getHeader().isSuccessful()) {
            throw new ModifyCartFailedException();
        }
    }

    @Override
    public void modifyBookQuantityInCartWhenNotLogin(final Cookie cartItemCookie, final CartItemRequest request)
            throws ModifyCartFailedException {
        String redisKey = cartItemCookie.getValue();

        CartItem cartItem;
        try {
            cartItem = cartItemRedisRepository.findById(UUID.fromString(redisKey))
                    .orElseThrow(ModifyCartFailedException::new);
        } catch (IllegalArgumentException e) {
            throw new ModifyCartFailedException();
        }

        if (!cartItem.modifyBookQuantity(request.getBookId(), request.getQuantity())) {
            throw new ModifyCartFailedException();
        }

        cartItemRedisRepository.save(cartItem);     // 레디스 기간 연장
        cartItemCookie.setPath("/");
    }

    @Override
    public void removeBookInCartWhenLogin(final Long bookId) throws DeleteCartFailedException {
        CommonResponse<Void> response = cartAdapter.removeBookFromCart(bookId);
        if (!response.getHeader().isSuccessful()) {
            throw new DeleteCartFailedException();
        }
    }

    @Override
    public void removeBookInCartWhenNotLogin(final Cookie cartItemCookie, final Long bookId)
            throws DeleteCartFailedException {
        String redisKey = cartItemCookie.getValue();

        CartItem cartItem;
        try {
            cartItem = cartItemRedisRepository.findById(UUID.fromString(redisKey))
                    .orElseThrow(DeleteCartFailedException::new);
        } catch (IllegalArgumentException e) {
            throw new DeleteCartFailedException();
        }

        if (!cartItem.removeBook(bookId)) {
            throw new DeleteCartFailedException();
        }

        // 장바구니가 비었으면 쿠키와 레디스 삭제
        if (cartItem.getBookIdAndQuantity().isEmpty()) {
            cartItemRedisRepository.delete(cartItem);
            cartItemCookie.setPath("/");
            cartItemCookie.setMaxAge(0);
            return;
        }

        cartItemRedisRepository.save(cartItem);     // 레디스 기간 연장
    }

    @Async
    @Override
    public void mergeCart(final String accessToken, Cookie cartItemCookie) {
        log.info("Current thread name: " + Thread.currentThread().getName());
        if (Objects.isNull(cartItemCookie)) {
            return;
        }

        String redisKey = cartItemCookie.getValue();
        CartItem cartItem;
        try {
            cartItem = cartItemRedisRepository.findById(UUID.fromString(redisKey)).orElse(null);
        } catch (IllegalArgumentException e) {
            return;
        }
        if (Objects.isNull(cartItem)) {
            return;
        }

        CommonResponse<Void> response = cartAdapter.mergeCart(accessToken, cartItem.getBookIdAndQuantity());
        if (response.getHeader().isSuccessful()) {
            cartItemRedisRepository.delete(cartItem);
        }

    }
}
