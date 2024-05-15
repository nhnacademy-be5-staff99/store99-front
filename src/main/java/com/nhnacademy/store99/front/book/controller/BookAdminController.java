package com.nhnacademy.store99.front.book.controller;

import com.nhnacademy.store99.front.book.dto.request.BookRequest;
import com.nhnacademy.store99.front.book.dto.response.BookPageResponse;
import com.nhnacademy.store99.front.book.service.BookService;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class BookAdminController {
    private final BookService bookService;

    @GetMapping("/admin/book")
    public ModelAndView viewManageBookDetail(@PageableDefault(size = 10) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView();
        CustomPageImpl<BookPageResponse> allBooks = bookService.getBooks(pageable);

        modelAndView.setViewName("admin/book/book_admin");
        modelAndView.addObject("books", allBooks);
        return modelAndView;
    }


    @DeleteMapping("/admin/books/{bookId}")
    public ModelAndView deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return new ModelAndView("redirect:/admin/books");
    }

    @PutMapping("/admin/books/{bookId}/restore")
    public ModelAndView restoreBook(@PathVariable("bookId") Long bookId) {
        bookService.restoreBook(bookId);
        return new ModelAndView("redirect:/admin/books");
    }

    @PostMapping(value = "/admin/books")
    public String postBook(@RequestBody BookRequest bookRequest) {
        return "index";
    }
}
