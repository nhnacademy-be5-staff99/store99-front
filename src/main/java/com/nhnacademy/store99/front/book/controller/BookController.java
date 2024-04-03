package com.nhnacademy.store99.front.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
