package com.nhnacademy.store99.front.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.store99.front.book.Request.BookRequest;
import com.nhnacademy.store99.front.book.Response.BookResponse;
import com.nhnacademy.store99.front.book.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
 * @author yrrho2
 * @author seunggyu-kim
 */
@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/books")
    public String viewBookSalesList(Model model) {
        List<BookResponse> books =
                objectMapper.registerModule(new JavaTimeModule()).convertValue(bookService.getBooks(), List.class);
        model.addAttribute("books", books);
        return "book/book_sales_list";
    }
//    private ObjectMapper mapper = new ObjectMapper();
//    PremierDriverInfoVariationDTO premierDriverInfoDTO =
//            mapper.convertValue(json, PremierDriverInfoVariationDTO.class);
//log.debug("premierDriverInfoDTO : {}", premierDriverInfoDTO);

    @GetMapping("/books/{id}")
    public String viewBookSalesPage(@PathVariable Long id, Model model) {
        model.addAttribute("bookId", id);
        return "book/book_sales_page";
    }

    @GetMapping("/books/admin")
    public String viewManageBookDetail() {
        return "book/book_admin";
    }


    @PostMapping(value = "/books/admin")
    public String postBook(@RequestBody BookRequest bookRequest) {
        System.out.println(bookRequest);
        return "index";
    }
}
