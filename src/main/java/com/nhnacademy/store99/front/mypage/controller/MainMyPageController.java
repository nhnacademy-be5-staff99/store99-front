package com.nhnacademy.store99.front.mypage.controller;

import com.nhnacademy.store99.front.mypage.dto.MainMyPageResponse;
import com.nhnacademy.store99.front.mypage.service.MainMyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 메인 마이페이지 컨트롤러
 *
 * @author Ahyeon Song
 */
@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MainMyPageController {

    private final MainMyPageService myPageService;

    @GetMapping
    public ModelAndView getMainMyPage() {
        ModelAndView mv = new ModelAndView();
        MainMyPageResponse myPageInfo = myPageService.getMainMyPage();

        mv.addObject("myPageInfo", myPageInfo);

        mv.setViewName("mypage/mypage");
        return mv;
    }
}
