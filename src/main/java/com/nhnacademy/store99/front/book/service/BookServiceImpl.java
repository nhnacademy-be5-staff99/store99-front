package com.nhnacademy.store99.front.book.service;


import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.book.adaptor.BookAdaptor;
import com.nhnacademy.store99.front.common.response.CommonListResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    private final BookAdaptor bookAdaptor;

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
     * @return Book List
     */
    @Override
    public List<BookResponse> getBooks() {
        CommonListResponse<BookResponse> books = bookAdaptor.getBooks().getBody();
        assert books != null;
        return books.getResultList();
    }
}
