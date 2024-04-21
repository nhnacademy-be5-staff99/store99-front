package com.nhnacademy.store99.front.like.controller;

import com.nhnacademy.store99.front.like.dto.request.LikeRequest;
import com.nhnacademy.store99.front.like.service.LikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;
    private boolean isLiked=false;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public String showLikes(Model model) {
        model.addAttribute("isLiked", isLiked);
        return"book/book_sales_page";
    }

    @PostMapping
    public ModelAndView addlike(@ModelAttribute LikeRequest request) {
        isLiked=!isLiked;
        ModelAndView mvn = new ModelAndView();
        mvn.addObject(isLiked);
        mvn.setViewName("redirect:/likes");
        likeService.addLike(request);
        return mvn;
    }

    @DeleteMapping("/{bookId}/{userId}")
    public ModelAndView deleteLike(@PathVariable Long bookId, @PathVariable Long userId) {
        isLiked=!isLiked;
        ModelAndView mvn = new ModelAndView();
        mvn.addObject(isLiked);
        mvn.setViewName("redirect:/likes");
        likeService.deleteLike(bookId, userId);
        return mvn;
    }


}
