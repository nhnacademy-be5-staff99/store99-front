package com.nhnacademy.store99.front.book.service;

import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.common.response.CustomPageResponse;

public interface BookService {
    public BookResponse getBook(Long id);

    public BookResponse postBook();

    public CustomPageResponse<BookResponse> getBooks(int page);
}
