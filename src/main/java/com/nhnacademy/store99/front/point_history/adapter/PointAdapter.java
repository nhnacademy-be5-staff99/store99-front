package com.nhnacademy.store99.front.point_history.adapter;

import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.point_history.dto.UserPointResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "pointAdapter", url = "${gateway.url}/api/bookstore/v1/points")
public interface PointAdapter {

    @GetMapping
    CommonResponse<List<UserPointResponse>> getUserPointHistories();
}
