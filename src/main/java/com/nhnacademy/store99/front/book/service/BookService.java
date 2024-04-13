package com.nhnacademy.store99.front.book.service;

import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.common.util.CustomPage;

public interface BookService {
    public BookResponse getBook(Long id);

    public BookResponse postBook();

    public CustomPage<BookResponse> getBooks(int page);
}
