package com.nhnacademy.store99.front.like.adapter;

import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.like.dto.request.LikeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * FeignClient를 통한 LikeAdapter 클래스 입니다.
 *
 * @author 이서연
 */
@FeignClient(value = "likeFeign", url = "${gateway.url}")
public interface LikeAdapter {

    @PostMapping("/api/bookstore/v1/likes")
    CommonResponse<Void> addLike(final LikeRequest req);

    @DeleteMapping("/api/bookstore/v1/likes/{likeId}")
    CommonResponse<String> deleteLike(@PathVariable final Long likeId);

    @GetMapping("/open/bookstore/v1/likes/likeCnt")
    CommonResponse<Long> getLikeCnt(@RequestParam(value = "bookId") final Long bookId);
}
