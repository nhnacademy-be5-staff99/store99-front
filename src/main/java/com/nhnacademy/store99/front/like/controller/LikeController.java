package com.nhnacademy.store99.front.like.controller;

import com.nhnacademy.store99.front.like.dto.request.LikeRequest;
import com.nhnacademy.store99.front.like.service.LikeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;
    private boolean isLiked = false;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }


    @PostMapping
    public ModelAndView addlike(@ModelAttribute LikeRequest request) {
        isLiked = true;
        ModelAndView mvn = new ModelAndView();
        mvn.setViewName("redirect:/likes");
        Long bookId = request.getBookId();
        Long userId = request.getUserId();
        mvn.addObject("bookId", bookId);
        mvn.addObject("userId", userId);
        mvn.addObject("isLiked", isLiked);
        likeService.addLike(request);
        return mvn;
    }

    @DeleteMapping("/{likeId}")
    public ModelAndView deleteLike(@PathVariable Long likeId) {
        isLiked = false;
        ModelAndView mvn = new ModelAndView();
        mvn.addObject("isLiked", isLiked);
        mvn.addObject("likeId", likeId);
        mvn.setViewName("redirect:/likes");
        likeService.deleteLike(likeId);
        return mvn;
    }


    @GetMapping("/count")
    public ModelAndView getLikeCnt(@RequestParam(value = "bookId") Long bookId) {
        ModelAndView mvn = new ModelAndView();
        mvn.addObject("bookId", bookId);
        mvn.setViewName("book/book_sales_list");
        Long cnt = likeService.getLikeCnt(bookId);
        mvn.addObject("cnt", cnt);
        return mvn;
    }


}
