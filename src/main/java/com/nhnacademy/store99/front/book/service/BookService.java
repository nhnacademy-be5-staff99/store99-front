package com.nhnacademy.store99.front.book.service;

import com.nhnacademy.store99.front.book.Request.BookRequest;
import com.nhnacademy.store99.front.book.Response.BookResponse;
import org.springframework.http.ResponseEntity;

public interface BookService {
    public ResponseEntity<BookResponse> getBook(Long id);

    public ResponseEntity<BookRequest> postBook();
}
