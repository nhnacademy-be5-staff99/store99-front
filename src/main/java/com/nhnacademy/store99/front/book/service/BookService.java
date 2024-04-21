package com.nhnacademy.store99.front.book.service;

import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;

public interface BookService {
    public BookResponse getBook(Long id);

    public BookResponse postBook();

    public CustomPageImpl<BookResponse> getBooks(int page, String query);
}
