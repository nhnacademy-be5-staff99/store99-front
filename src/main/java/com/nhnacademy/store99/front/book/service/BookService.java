package com.nhnacademy.store99.front.book.service;

import com.nhnacademy.store99.front.book.dto.response.BookPageResponse;
import com.nhnacademy.store99.front.book.dto.response.BookResponse;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;
import com.nhnacademy.store99.front.index.dto.response.IndexBookResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponse getBook(Long id);

    BookResponse postBook();

    List<IndexBookResponse> getBestBooks();

    List<IndexBookResponse> getLatestBooks();

    CustomPageImpl<BookPageResponse> getBooks(Pageable pageable);

    CustomPageImpl<BookPageResponse> getBooksByCategory(Long categoryId, Pageable pageable);

    List<IndexBookResponse> getBooksByCategory(Long categoryId);

    void deleteBook(Long bookId);

    void restoreBook(Long bookId);

}
