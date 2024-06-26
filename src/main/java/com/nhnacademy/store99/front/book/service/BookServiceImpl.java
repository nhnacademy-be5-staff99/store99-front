package com.nhnacademy.store99.front.book.service;


import com.nhnacademy.store99.front.book.adapter.BookAdapter;
import com.nhnacademy.store99.front.book.adapter.BookOpenAdapter;
import com.nhnacademy.store99.front.book.dto.response.BookPageResponse;
import com.nhnacademy.store99.front.book.dto.response.BookResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;
import com.nhnacademy.store99.front.index.dto.response.IndexBookResponse;
import java.util.List;
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
    private final BookOpenAdapter bookAdaptor;
    private final BookAdapter bookAdminAdaptor;

    @Override
    public BookResponse getBook(Long bookId) {
        return bookAdaptor.getBook(bookId).getResult();
    }

    @Override
    public BookResponse postBook() {
        return null;
    }

    @Override
    public List<IndexBookResponse> getBestBooks() {
        CommonResponse<List<IndexBookResponse>> bestBooksResponse = bookAdaptor.getBestBooks();
        if (!bestBooksResponse.getHeader().isSuccessful()) {
            return List.of();
        }
        return bestBooksResponse.getResult();
    }

    @Override
    public List<IndexBookResponse> getLatestBooks() {
        CommonResponse<List<IndexBookResponse>> latestBooksResponse = bookAdaptor.getLatestBooks();
        if (!latestBooksResponse.getHeader().isSuccessful()) {
            return List.of();
        }
        return latestBooksResponse.getResult();
    }

    /**
     * book 목록 조회
     *
     * @return Book Page
     */
    @Override
    public CustomPageImpl<BookPageResponse> getBooks(Pageable pageable) {
        return Objects.requireNonNull(bookAdaptor.getBooks(pageable)).getResult();
    }

    @Override
    public CustomPageImpl<BookPageResponse> getBooksByCategory(Long categoryId, Pageable pageable) {
        return bookAdaptor.getBooksByCategory(categoryId, pageable).getResult();
    }

    @Override
    public List<IndexBookResponse> getBooksByCategory(Long categoryId) {
        return bookAdaptor.getBooksByCategory(categoryId).getResult();
    }

    @Override
    public void deleteBook(Long bookId) {
        bookAdminAdaptor.deleteBook(bookId);
    }

    @Override
    public void restoreBook(Long bookId) {
        bookAdminAdaptor.restoreBook(bookId);
    }
}
