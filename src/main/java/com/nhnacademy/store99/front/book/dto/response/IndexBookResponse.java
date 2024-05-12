package com.nhnacademy.store99.front.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class IndexBookResponse {
    private Long BookId;
    private String BookTitle;
    private String BookDescription;
    private String BookThumbnailUrl;
}
