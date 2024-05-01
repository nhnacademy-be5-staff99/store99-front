package com.nhnacademy.store99.front.book.dto.response;

import lombok.Getter;

/**
 * @author seunggyu-kim
 */
@Getter
public class SimpleBookResponse {
    private Long id;

    private String bookTitle;

    private Integer bookPrice;

    private Integer bookSalePrice;

    private String bookThumbnailUrl;

    private Integer bookStock;
}