package com.nhnacademy.store99.front.like.service.impl;

import com.nhnacademy.store99.front.auth.exception.LoginRequiredException;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.common.thread_local.XUserTokenThreadLocal;
import com.nhnacademy.store99.front.like.adapter.LikeAdapter;
import com.nhnacademy.store99.front.like.dto.response.BookInfoForLikeResponse;
import com.nhnacademy.store99.front.like.service.LikeQueryService;
import feign.FeignException;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@Data
public class LikeQueryServiceImpl implements LikeQueryService {

    private final LikeAdapter likeAdapter;

    public LikeQueryServiceImpl(LikeAdapter likeAdapter) {
        this.likeAdapter = likeAdapter;
    }

    @Override
    public List<BookInfoForLikeResponse> getAllByUser() {
        CommonResponse<List<BookInfoForLikeResponse>> response;
        try {
            response = likeAdapter.findAllByUser(XUserTokenThreadLocal.getXUserToken());
        } catch (FeignException.Unauthorized ex) {
            throw new LoginRequiredException("나의 좋아요 내역 조회에 접근하나 로그인 되어있지 않음");
        } catch (FeignException.BadRequest ex) {
            throw new LoginRequiredException("2번째");
        }
        return response.getResult();
    }

}
