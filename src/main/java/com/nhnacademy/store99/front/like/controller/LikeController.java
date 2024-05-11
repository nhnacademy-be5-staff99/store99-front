package com.nhnacademy.store99.front.like.controller;

import com.nhnacademy.store99.front.like.dto.request.LikeRequest;
import com.nhnacademy.store99.front.like.dto.response.BookInfoForLikeResponse;
import com.nhnacademy.store99.front.like.service.LikeQueryService;
import com.nhnacademy.store99.front.like.service.LikeService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class LikeController {
    private final LikeService likeService;
    private final LikeQueryService likeQueryService;
    private boolean isLiked = false;

    public LikeController(LikeService likeService, LikeQueryService likeQueryService) {
        this.likeService = likeService;
        this.likeQueryService = likeQueryService;
    }


    @PostMapping("/likes")
    public ModelAndView addlike(@ModelAttribute LikeRequest request) {
        isLiked = true;
        ModelAndView mvn = new ModelAndView();
        Long bookId = request.getBookId();
        Long userId = request.getUserId();
        mvn.addObject("bookId", bookId);
        mvn.addObject("userId", userId);
        mvn.addObject("isLiked", isLiked);
        mvn.setViewName("book/book_sales_list");
        likeService.addLike(request);
        return mvn;
    }

    @DeleteMapping("/likes/{likeId}")
    public ModelAndView deleteLike(@PathVariable Long likeId) {
        ModelAndView mvn = new ModelAndView();
        mvn.addObject("isLiked", isLiked);
        mvn.addObject("likeId", likeId);
        likeService.deleteLike(likeId);
        return mvn;
    }


    @GetMapping(value = "/likes/count/{bookId}")
    public ModelAndView getLikeCnt(@PathVariable(value = "bookId") Long bookId) {
        ModelAndView mvn = new ModelAndView();
        mvn.addObject("bookId", bookId);
        mvn.setViewName("book/book_sales_list");

        Long cnt = likeService.getLikeCnt(bookId);
        mvn.addObject("cnt", cnt);
        return mvn;
    }

    @GetMapping(value = "/mylikes")
    public ModelAndView getLikesByUser() {
        ModelAndView mvn = new ModelAndView();
        mvn.setViewName("mypage/mypage_like");

        List<BookInfoForLikeResponse> likeListPage = likeQueryService.getAllByUser();
        mvn.addObject("likeListPage", likeListPage);
        return mvn;
    }
}
