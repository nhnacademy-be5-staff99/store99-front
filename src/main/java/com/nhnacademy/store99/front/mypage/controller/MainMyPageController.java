package com.nhnacademy.store99.front.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mypage")
public class MainMyPageController {

    @GetMapping("")
    public ModelAndView getMainMyPage(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("mypage/mypage");
        return mv;
    }
}
