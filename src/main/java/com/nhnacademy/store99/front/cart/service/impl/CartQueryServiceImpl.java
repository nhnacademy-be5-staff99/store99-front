package com.nhnacademy.store99.front.cart.service.impl;

import com.nhnacademy.store99.front.book.adapter.BookOpenAdapter;
import com.nhnacademy.store99.front.book.dto.response.SimpleBookResponse;
import com.nhnacademy.store99.front.cart.dto.response.CartItemResponse;
import com.nhnacademy.store99.front.cart.entity.CartItem;
import com.nhnacademy.store99.front.cart.repository.CartItemRedisRepository;
import com.nhnacademy.store99.front.cart.service.CartQueryService;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 장바구니 조회용 서비스
 * 종속성 관리를 위해 조회용 서비스는 별도로 분리
 */
@Service
@RequiredArgsConstructor
public class CartQueryServiceImpl implements CartQueryService {
    private final CartItemRedisRepository cartItemRedisRepository;
    private final BookOpenAdapter bookOpenAdapter;

    /**
     * 비로그인 시 장바구니 조회
     * <p>
     * 쿠키에서 키를 가져온다.
     * 그 키로 레디스에서 장바구니에 저장된 도서 아이디와 수량 정보를 가져온다.
     * 도서 아이디로 도서 정보를 서버에서 불러온다.
     * 응답객체를 생성하여 반환한다.
     * </p>
     *
     * @param cartItemCookie 레디스의 키가 저장되어있는 쿠키이다.
     *                       만약 쿠키가 null 값이거나 레디스에 해당 키의 값이 없으면, 빈 리스트를 반환한다.
     * @return 장바구니 응답 리스트
     */
    @Override
    public List<CartItemResponse> getSimpleBookListWhenNotLogin(final Cookie cartItemCookie) {
        if (Objects.isNull(cartItemCookie)) {
            // 쿠키가 없는 경우 빈 리스트 반환
            return List.of();
        }

        // 레디스 장바구니 조회
        UUID redisId = UUID.fromString(cartItemCookie.getValue());
        Optional<CartItem> cartItem = cartItemRedisRepository.findById(redisId);
        if (cartItem.isEmpty()) {
            // 레디스의 장바구니가 비어있는 경우 빈 리스트 반환
            return List.of();
        }
        // 레디스에서 책 아이디와 수량 조회
        Map<Long, Integer> bookIdAndQuantityInRedis = cartItem.get().getBookIdAndQuantity();

        // 장바구니 책 정보 조회
        Set<Long> bookIds = bookIdAndQuantityInRedis.keySet();

        CommonResponse<List<SimpleBookResponse>> commonResponse = bookOpenAdapter.getSimpleBooks(bookIds);
        if (!commonResponse.getHeader().isSuccessful()) {
            return List.of();
        }
        List<SimpleBookResponse> simpleBooks = commonResponse.getResult();

        // 장바구니 응답 생성
        List<CartItemResponse> cartItemResponses = new ArrayList<>();
        for (SimpleBookResponse simpleBook : simpleBooks) {
            CartItemResponse cartItemResponse = CartItemResponse.builder()
                    .id(simpleBook.getId())
                    .bookTitle(simpleBook.getBookTitle())
                    .bookPrice(simpleBook.getBookPrice())
                    .bookSalePrice(simpleBook.getBookSalePrice())
                    .bookThumbnailUrl(simpleBook.getBookThumbnailUrl())
                    .bookStock(simpleBook.getBookStock())
                    .quantity(bookIdAndQuantityInRedis.get(simpleBook.getId()))
                    .build();
            cartItemResponses.add(cartItemResponse);
        }
        return cartItemResponses;
    }
}
