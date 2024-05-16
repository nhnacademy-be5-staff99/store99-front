package com.nhnacademy.store99.front.like.adapter;

import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.like.dto.request.LikeRequest;
import com.nhnacademy.store99.front.like.dto.response.BookInfoForLikeResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * FeignClient로 api를 호출하는 LikeAdapter 클래스 입니다.
 *
 * @author 이서연
 */
@FeignClient(value = "likeFeign", url = "${gateway.url}")
public interface LikeAdapter {

    @PostMapping("/api/bookstore/v1/likes")
    CommonResponse<Void> addLike(@RequestHeader("X-USER-TOKEN") String userToken, @RequestBody final LikeRequest req);

    @DeleteMapping("/api/bookstore/v1/likes/{likeId}")
    CommonResponse<String> deleteLike(@PathVariable final Long likeId);

    @GetMapping(value = "/open/bookstore/v1/likes/count/{bookId}")
    CommonResponse<Long> getLikeCnt(@PathVariable(value = "bookId") final Long bookId);

    @GetMapping(value = "/api/bookstore/v1/mylikes")
    CommonResponse<List<BookInfoForLikeResponse>> findAllByUser(@RequestHeader("X-USER-TOKEN") String userToken);


}