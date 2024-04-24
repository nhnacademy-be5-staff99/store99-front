package com.nhnacademy.store99.front.book.controller;

import com.nhnacademy.store99.front.book.Response.BookPageResponse;
import com.nhnacademy.store99.front.book.service.BookService;
import com.nhnacademy.store99.front.category.dto.response.CategoryChildrenListAndRouteResponse;
import com.nhnacademy.store99.front.category.service.CategoryService;
import com.nhnacademy.store99.front.common.response.CustomPageImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 도서 뷰 컨트롤러
 * <p>도서 페이지를 보여주는 컨트롤러
 *
 * @author seunggyu-kim
 */
@Controller
@RequiredArgsConstructor
public class BookController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @GetMapping("/books")
    public String viewBookSalesList(Model model, Pageable pageable) {
        model.addAttribute("categoryChildrenListAndRoute", null);
        model.addAttribute("booksDTOPage", null);
        return "book/book_sales_list";
    }

    @GetMapping("/books/{id}")
    public String viewBookSalesPage(@PathVariable Long id, Model model) {
        model.addAttribute("bookId", id);
        return "book/book_sales_page";
    }

    @GetMapping("/categories/{categoryId}/books")
    public String viewBookSalesListByCategory(@PathVariable Long categoryId, Model model, Pageable pageable) {
        CategoryChildrenListAndRouteResponse categoryChildrenListAndRoute =
                categoryService.getChildrenListAndRoute(categoryId);
        CustomPageImpl<BookPageResponse> booksDTOPage = bookService.getBooks(pageable);
        model.addAttribute("categoryChildrenListAndRoute", categoryChildrenListAndRoute);
//        model.addAttribute("booksDTOPage", booksDTOPage);
        return "book/book_sales_list";
    }
}
