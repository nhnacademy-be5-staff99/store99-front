package com.nhnacademy.store99.front.address.service;

import com.nhnacademy.store99.front.address.adapter.AddressAdapter;
import com.nhnacademy.store99.front.address.dto.AddressMyPageResponse;
import com.nhnacademy.store99.front.auth.exception.LoginRequiredException;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import feign.FeignException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원의 주소를 조회하고 관리
 *
 * @author Ahyeon Song
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressMyPageService {

    private final AddressAdapter addressAdapter;

    /**
     * 마이페이지에서 보여주는 회원의 주소 목록을 반환
     *
     * @return AddressMyPageResponse
     */
    public List<AddressMyPageResponse> getUserAddresses() {
        CommonResponse<List<AddressMyPageResponse>> addressResponse;
        try {
            addressResponse = addressAdapter.getUserAddresses();
        } catch (FeignException.Unauthorized | FeignException.BadRequest ex) {
            throw new LoginRequiredException("고객이 주소를 요청했으나, 로그인 되어있지 않음");
        }

        return addressResponse.getResult();
    }
}
