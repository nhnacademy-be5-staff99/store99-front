package com.nhnacademy.store99.front.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BestBookResponse {
    private Long BookId;
    private String BookTitle;
    private String BookDescription;
    private String BookThumbnailUrl;
}
