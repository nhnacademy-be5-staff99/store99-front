package com.nhnacademy.store99.front.address.adapter;

import com.nhnacademy.store99.front.address.dto.AddressMyPageResponse;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "addressAdapter", url = "${gateway.url}/api/bookstore/v1/address")
public interface AddressAdapter {

    @GetMapping
    CommonResponse<List<AddressMyPageResponse>> getUserAddresses();
}
