package com.nhnacademy.store99.front.mypage.adapter;

import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.mypage.dto.MainMyPageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ahyeon Song
 */
@FeignClient(name = "mypageAdapter", url = "${gateway.url}/api/bookstore/v1/mypage")
public interface MyPageAdapter {

    @GetMapping
    CommonResponse<MainMyPageResponse> getMainMyPage();
}
