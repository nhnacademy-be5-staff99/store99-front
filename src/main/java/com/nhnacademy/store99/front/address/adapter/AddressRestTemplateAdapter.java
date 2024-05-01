package com.nhnacademy.store99.front.address.adapter;

import com.nhnacademy.store99.front.address.dto.UserAddressUpdateRequest;
import com.nhnacademy.store99.front.address.dto.UserChangeDefaultAddressRequest;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.common.thread_local.XUserTokenThreadLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class AddressRestTemplateAdapter {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    public CommonResponse<Void> updateUserAddress(UserAddressUpdateRequest request) {
        String url = gatewayUrl + "/api/bookstore/v1/address/update";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-USER-TOKEN", XUserTokenThreadLocal.getXUserToken());
        HttpEntity<UserAddressUpdateRequest> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<CommonResponse<Void>>
                responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity,
                new ParameterizedTypeReference<CommonResponse<Void>>() {
                });
        return responseEntity.getBody();
    }

    public CommonResponse<Void> updateDefaultAddress(UserChangeDefaultAddressRequest request) {
        String url = gatewayUrl + "/api/bookstore/v1/address/change_default";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-USER-TOKEN", XUserTokenThreadLocal.getXUserToken());
        HttpEntity<UserChangeDefaultAddressRequest> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<CommonResponse<Void>> responseEntity =
                restTemplate.exchange(url, HttpMethod.PATCH, requestEntity,
                        new ParameterizedTypeReference<CommonResponse<Void>>() {
                        });
        return responseEntity.getBody();
    }


}
