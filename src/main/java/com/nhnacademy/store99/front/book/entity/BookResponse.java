package com.nhnacademy.store99.front.book.entity;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class BookResponse {
    private Long id;
    private String bookIsbn13;
    private String bookIsbn10;
    private String bookTitle;
    private String bookContents;
    private String bookDescription;
    private String bookPublisher;
    private LocalDateTime bookDateTime;
    private Integer bookPrice;
    private Integer bookSalePrice;
    private Boolean bookIsPacked;
    private String bookThumbnailUrl;
    private Integer bookStock;
    private Integer bookViewCount;
    private Integer bookCntOfReview;
    private Integer bookAvgOfRate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}