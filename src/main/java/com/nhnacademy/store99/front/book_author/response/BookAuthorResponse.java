package com.nhnacademy.store99.front.book_author.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookAuthorResponse {
    private Long authorId;
    private Long bookId;
    private String authorName;
}