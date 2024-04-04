package com.nhnacademy.store99.front.book.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


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

    @GetMapping("/books/admin")
    public String viewManageBookDetail() {
        return "book/book_admin";
    }


    @ResponseBody
    @PostMapping(value = "/books/admin")
    public String postBook(@RequestBody String bookRequest) {
        System.out.println(bookRequest);
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(bookRequest).getAsJsonObject();
        System.out.println(object);
        return "index";
    }
}
