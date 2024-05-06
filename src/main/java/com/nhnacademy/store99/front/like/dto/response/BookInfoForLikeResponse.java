package com.nhnacademy.store99.front.like.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookInfoForLikeResponse {

    private Long bookId;

    private String bookThumbnailUrl;

    private String bookTitle;

    private String authorName;
}
