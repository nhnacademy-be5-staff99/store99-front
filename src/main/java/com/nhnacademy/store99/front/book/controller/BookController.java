package com.nhnacademy.store99.front.book.controller;

import com.nhnacademy.store99.front.category.dto.response.CategoryChildrenListAndRouteResponse;
import com.nhnacademy.store99.front.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/books")
    public String viewBookSalesList() {
        return "book/book_sales_list";
    }

    @GetMapping("/books/{id}")
    public String viewBookSalesPage(@PathVariable Long id, Model model) {
        model.addAttribute("bookId", id);
        return "book/book_sales_page";
    }

    @GetMapping("/categories/{categoryId}/books")
    public String viewBookSalesListByCategory(@PathVariable Long categoryId, Model model) {
        CategoryChildrenListAndRouteResponse categoryChildrenListAndRoute =
                categoryService.getChildrenListAndRoute(categoryId);
        model.addAttribute("categoryChildrenListAndRoute", categoryChildrenListAndRoute);
        return "book/book_sales_list";
    }
}
