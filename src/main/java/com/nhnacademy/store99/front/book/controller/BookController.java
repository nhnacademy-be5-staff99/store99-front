package com.nhnacademy.store99.front.book.controller;

import com.nhnacademy.store99.front.book.Request.BookRequest;
import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 도서 뷰 컨트롤러
 * <p>도서 페이지를 보여주는 컨트롤러
 *
 * @author yrrho2
 * @author seunggyu-kim
 */
@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public String viewBookSalesPage(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<BookResponse> paging = bookService.getBooks(page);
        model.addAttribute("booksPage", paging);
        return "book/book_sales_list";
    }

    @GetMapping("/books/{id}")
    public String viewBookSalesPage(@PathVariable Long id, Model model) {
        model.addAttribute("bookId", id);
        return "book/book_sales_page";
    }

    @GetMapping("/admin/books")
    public String viewManageBookDetail() {
        return "book/book_admin";
    }


    @PostMapping(value = "/admin/books")
    public String postBook(@RequestBody BookRequest bookRequest) {
        bookService.postBook();
        return "/books/";
    }
}
