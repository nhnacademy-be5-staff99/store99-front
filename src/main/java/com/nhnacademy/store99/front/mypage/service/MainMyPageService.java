package com.nhnacademy.store99.front.mypage.service;

import com.nhnacademy.store99.front.auth.exception.LoginCheckException;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.mypage.adapter.MyPageAdapter;
import com.nhnacademy.store99.front.mypage.dto.MainMyPageResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ahyeon Song
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MainMyPageService {

    private final MyPageAdapter myPageAdapter;

    /**
     * 메인 마이페이지에 필요한 정보
     *
     * @return MainMyPageResponse
     */
    public MainMyPageResponse getMainMyPage() {
        CommonResponse<MainMyPageResponse> mainMyPageResponse;
        try {
            mainMyPageResponse = myPageAdapter.getMainMyPage();
        } catch (FeignException.Unauthorized | FeignException.BadRequest ex) {
            throw new LoginCheckException("마이페이지에 접근하나, 로그인이 되어있지 않음");
        }
        return mainMyPageResponse.getResult();
    }
}
