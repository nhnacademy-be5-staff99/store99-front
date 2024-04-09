package com.nhnacademy.store99.front.book.service;

import com.nhnacademy.store99.front.book.Response.BookResponse;
import java.util.List;

public interface BookService {
    public BookResponse getBook(Long id);

    public BookResponse postBook();

    public List<BookResponse> getBooks();
}
