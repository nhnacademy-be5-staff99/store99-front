package com.nhnacademy.store99.front.cart.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CartItemResponse {
    private Long id;

    private String bookTitle;

    private Integer bookPrice;

    private Integer bookSalePrice;

    private String bookThumbnailUrl;

    private Integer bookStock;

    private Integer quantity;
}
