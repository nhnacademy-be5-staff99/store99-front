package com.nhnacademy.store99.front.order.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookInOrderResponse {
    private Long bookId;

    private String bookTitle;

    private Integer bookPrice;

    private Integer bookSalePrice;

    private String bookThumbnailUrl;

    private Integer bookStock;

    private Integer quantity;
}