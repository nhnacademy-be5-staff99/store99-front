package com.nhnacademy.store99.front.book.service;


import com.nhnacademy.store99.front.book.Response.BookPageResponse;
import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.book.adapter.BookAdapter;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 도서 api 서비스 구현체
 *
 * @author yrrho2
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
    private final BookAdapter bookAdaptor;

    @Override
    public BookResponse getBook(Long id) {
        return null;
    }

    @Override
    public BookResponse postBook() {
        return null;
    }

    /**
     * book 목록 조회
     *
     * @return Book Page
     */
    @Override
    public CustomPageImpl<BookPageResponse> getBooks(Pageable pageable) {
        return Objects.requireNonNull(bookAdaptor.getBooks(pageable).getBody()).getResult();
    }
}