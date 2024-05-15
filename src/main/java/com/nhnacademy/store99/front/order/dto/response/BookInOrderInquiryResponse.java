package com.nhnacademy.store99.front.order.dto.response;

import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
public class BookInOrderInquiryResponse {
    private Long bookId;
    private String bookTitle;
    private Integer quantity;
    private Integer bookSalePrice;
}
