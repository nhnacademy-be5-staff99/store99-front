package com.nhnacademy.store99.front.address.adapter;

import com.nhnacademy.store99.front.address.dto.UserAddressAddRequest;
import com.nhnacademy.store99.front.address.dto.UserAddressResponse;
import com.nhnacademy.store99.front.address.dto.UserAddressUpdateRequest;
import com.nhnacademy.store99.front.address.dto.UserChangeDefaultAddressRequest;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Ahyeon Song
 */
@FeignClient(name = "addressAdapter", url = "${gateway.url}/api/bookstore/v1/address")
public interface AddressAdapter {

    @GetMapping
    CommonResponse<List<UserAddressResponse>> getUserAddresses();

    @GetMapping("/count")
    CommonResponse<Integer> getUserAddressCount();

    @GetMapping(params = {"addressId"})
    CommonResponse<UserAddressResponse> getUserAddressById(@RequestParam("addressId") Long addressId);

    @PostMapping
    CommonResponse<Void> addUserAddress(@RequestBody UserAddressAddRequest request);

    @DeleteMapping("/{addressId}")
    CommonResponse<Void> deleteUserAddress(@PathVariable("addressId") Long addressId);
}
