package com.nhnacademy.store99.front.search.controller;

import com.nhnacademy.store99.front.search.dto.BasicSearchResponse;
import com.nhnacademy.store99.front.search.service.BasicSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ahyeon Song
 */
@Controller
@RequiredArgsConstructor
public class BasicSearchController {

    private final BasicSearchService searchService;

    @GetMapping("/search")
    public ModelAndView basicSearchResult(@RequestParam(value = "content", defaultValue = "") String content,
                                          Pageable pageable) {
        ModelAndView mv = new ModelAndView();
        Page<BasicSearchResponse> searchResults = searchService.basicSearchResult(content, pageable);

        mv.setViewName("search/book_search_list");
        mv.addObject("searchContent", content);
        mv.addObject("searchResults", searchResults);
        return mv;
    }

}
