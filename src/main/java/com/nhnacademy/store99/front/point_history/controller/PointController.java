package com.nhnacademy.store99.front.point_history.controller;

import com.nhnacademy.store99.front.point_history.dto.UserPointResponse;
import com.nhnacademy.store99.front.point_history.service.PointService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {

    private final PointService pointService;

    @GetMapping("/my_point")
    public ModelAndView getUserPointHistories(){
        ModelAndView mv = new ModelAndView();
        List<UserPointResponse> userPointHistories = pointService.getUserPointHistories();

        mv.addObject("userPointHistories", userPointHistories);
        mv.setViewName("mypage/mypage_point_history");

        return mv;
    }
}
