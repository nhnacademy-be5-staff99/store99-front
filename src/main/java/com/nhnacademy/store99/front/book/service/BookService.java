package com.nhnacademy.store99.front.book.service;

import com.nhnacademy.store99.front.book.Response.BookPageResponse;
import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponse getBook(Long id);

    BookResponse postBook();

    CustomPageImpl<BookPageResponse> getBooks(Pageable pageable);

    CustomPageImpl<BookPageResponse> getBooksByCategory(Long categoryId, Pageable pageable);

}
