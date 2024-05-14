package com.nhnacademy.store99.front.book.adapter;

import com.nhnacademy.store99.front.book.dto.response.BookPageResponse;
import com.nhnacademy.store99.front.book.dto.response.BookResponse;
import com.nhnacademy.store99.front.book.dto.response.SimpleBookResponse;
import com.nhnacademy.store99.front.book_author.response.BookAuthorResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;
import com.nhnacademy.store99.front.index.dto.response.IndexBookResponse;
import java.util.List;
import java.util.Set;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author seunggyu-kim
 */
@FeignClient(value = "book-open-adapter", url = "${gateway.url}/open/bookstore/v1/books")
public interface BookOpenAdapter {
    @GetMapping("/simple")
    CommonResponse<List<SimpleBookResponse>> getSimpleBooks(@RequestParam Set<Long> bookIds);

    @GetMapping("/")
    CommonResponse<CustomPageImpl<BookPageResponse>> getBooks(
            @RequestParam(value = "page") Pageable pageable);

    @GetMapping("/author/book")
    ResponseEntity<CommonResponse<BookAuthorResponse>> getBookAuthor(@RequestParam(value = "bookId") Long bookId);

    @GetMapping("/{bookId}")
    CommonResponse<BookResponse> getBook(@PathVariable(value = "bookId") Long bookId);

    /*
    하나 굳이 작은 의견을 내자면, books/categories/{categoryId}라는 url이 도서의 카테고리의 한 카테고리 라는 의미로 받아들여져서 직관적이지는 않아보인다는 점이 있네요. categories/{categoryId}/books나 books?categoryId={categoryId}같은 경우가 좀 더 가독성 있을거같긴합니다. 특히 books?categoryId={categoryId}와 같은 경우는 정렬이나 검색과 같은 경우에도 뒤에 파라미터만 더 추가하면 되므로 더욱 확장성 있지 않을까요?
     */
    // 자식 카테고리까지 포함된 도서 목록
    @GetMapping("/categories/{categoryId}")
    CommonResponse<CustomPageImpl<BookPageResponse>> getBooksByCategory(@PathVariable("categoryId") Long categoryId,
                                                                        Pageable pageable);

    @GetMapping("/categories/index")
    CommonResponse<List<IndexBookResponse>> getBooksByCategory(@RequestParam("categoryId") Long categoryId);

    @GetMapping("/best")
    CommonResponse<List<IndexBookResponse>> getBestBooks();

    @GetMapping("/latest")
    CommonResponse<List<IndexBookResponse>> getLatestBooks();
}
