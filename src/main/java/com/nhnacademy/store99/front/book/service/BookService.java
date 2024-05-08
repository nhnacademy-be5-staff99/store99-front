package com.nhnacademy.store99.front.book.service;

import com.nhnacademy.store99.front.book.Response.BookPageResponse;
import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;
import org.springframework.data.domain.Pageable;

public interface BookService {
    public BookResponse getBook(Long id);

    public BookResponse postBook();

    public CustomPageImpl<BookPageResponse> getBooks(Pageable pageable);
}
