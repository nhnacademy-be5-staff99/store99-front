package com.nhnacademy.store99.front.book_author.service;

import com.nhnacademy.store99.front.book.adapter.BookAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookAuthorService {
    private final BookAdapter bookAdapter;
}
