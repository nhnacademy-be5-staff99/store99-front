package com.nhnacademy.store99.front.point_history.service;

import com.nhnacademy.store99.front.auth.exception.LoginRequiredException;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.point_history.adapter.PointAdapter;
import com.nhnacademy.store99.front.point_history.dto.UserPointResponse;
import feign.FeignException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PointService {

    private final PointAdapter pointAdapter;

    public List<UserPointResponse> getUserPointHistories() {
        CommonResponse<List<UserPointResponse>> userPointHistories;
        try {
            userPointHistories = pointAdapter.getUserPointHistories();
        } catch (FeignException.Unauthorized | FeignException.BadRequest ex) {
            throw new LoginRequiredException("나의 포인트 내역 조회에 접근하나 로그인 되어있지 않음");
        }

        return userPointHistories.getResult();
    }
}
