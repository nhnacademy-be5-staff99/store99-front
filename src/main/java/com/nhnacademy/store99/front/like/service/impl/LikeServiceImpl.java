package com.nhnacademy.store99.front.like.service.impl;

import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.like.adapter.LikeAdapter;
import com.nhnacademy.store99.front.like.dto.request.LikeRequest;
import com.nhnacademy.store99.front.like.exception.LikeCountNotAvailableException;
import com.nhnacademy.store99.front.like.exception.LikeProcessingFaildException;
import com.nhnacademy.store99.front.like.service.LikeService;
import feign.FeignException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@Data
public class LikeServiceImpl implements LikeService {
    private final LikeAdapter likeAdapter;

    public LikeServiceImpl(LikeAdapter likeAdapter) {
        this.likeAdapter = likeAdapter;
    }

    @Override
    public void addLike(final LikeRequest likeRequest) {
        try {
            likeAdapter.addLike(likeRequest);
        } catch (FeignException.Forbidden ex) {
            throw new LikeProcessingFaildException();
        } catch (Exception ex) {
            log.error("error of addLike: ", ex);
        }

    }

    @Override
    public String deleteLike(Long likeId) {
        CommonResponse<String> commonResponse = null;
        try {
            commonResponse = likeAdapter.deleteLike(likeId);
        } catch (FeignException.Forbidden ex) {
            throw new LikeProcessingFaildException();
        } catch (Exception ex) {
            log.error("error of deleteLike: ", ex);
        }

        log.debug("successfully deleted like: {}", likeId);
        assert commonResponse != null;
        log.debug("commonResponse: {}", commonResponse);
        return commonResponse.getResult();
    }

    @Override
    public Long getLikeCnt(Long bookId) {
        Long cnt = 0L;
        try {
            cnt = likeAdapter.getLikeCnt(bookId).getResult();

        } catch (FeignException.Forbidden ex) {
            throw new LikeCountNotAvailableException();
        } catch (Exception ex) {
            log.error("error of deleteLike: ", ex);
        }
        log.debug("cnt= " + cnt);
        return cnt;
    }
}