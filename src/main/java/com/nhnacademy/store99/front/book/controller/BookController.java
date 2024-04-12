package com.nhnacademy.store99.front.book.controller;

import com.nhnacademy.store99.front.book.Request.BookRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 도서 뷰 컨트롤러
 * <p>도서 페이지를 보여주는 컨트롤러
 *
 * @author seunggyu-kim
 */
@Controller
public class BookController {
    @GetMapping("/books")
    public String viewBookSalesList() {
        return "book/book_sales_list";
    }

    @GetMapping("/books/{id}")
    public String viewBookSalesPage(@PathVariable Long id, Model model) {
        model.addAttribute("bookId", id);
        return "book/book_sales_page";
    }

    @GetMapping("/admin/books")
    public String viewManageBookDetail() {
        return "admin/book/book_admin";
    }


    @PostMapping(value = "/admin/books")
    public String postBook(@RequestBody BookRequest bookRequest) {
        System.out.println(bookRequest);
        return "index";
    }
}
