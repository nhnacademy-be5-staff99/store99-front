package com.nhnacademy.store99.front.address.service;

import com.nhnacademy.store99.front.address.adapter.AddressAdapter;
import com.nhnacademy.store99.front.address.adapter.AddressRestTemplateAdapter;
import com.nhnacademy.store99.front.address.dto.UserAddressAddRequest;
import com.nhnacademy.store99.front.address.dto.UserAddressResponse;
import com.nhnacademy.store99.front.address.dto.UserAddressUpdateRequest;
import com.nhnacademy.store99.front.address.dto.UserChangeDefaultAddressRequest;
import com.nhnacademy.store99.front.address.exception.AddressDeleteException;
import com.nhnacademy.store99.front.address.exception.UserAddressOverTenException;
import com.nhnacademy.store99.front.auth.exception.LoginRequiredException;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import feign.FeignException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

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
    private final AddressRestTemplateAdapter restTemplateAdapter;

    /**
     * 마이페이지에서 보여주는 회원의 주소 목록을 반환
     *
     * @return AddressMyPageResponse
     */
    public List<UserAddressResponse> getUserAddresses() {
        CommonResponse<List<UserAddressResponse>> addressResponse;
        try {
            addressResponse = addressAdapter.getUserAddresses();
        } catch (FeignException.Unauthorized | FeignException.BadRequest ex) {
            throw new LoginRequiredException("고객이 주소를 요청했으나, 로그인 되어있지 않음");
        }

        return addressResponse.getResult();
    }

    /**
     * addressId 에 해당하는 주소 조회
     *
     * @param addressId
     * @return
     */
    public UserAddressResponse getUserAddressById(Long addressId) {
        CommonResponse<UserAddressResponse> userAddress;
        try {
            userAddress = addressAdapter.getUserAddressById(addressId);
        } catch (FeignException.Unauthorized | FeignException.BadRequest ex) {
            throw new LoginRequiredException(
                    String.format("고객이 주소 (addressId : %d) 조회를 요청했으나, 로그인 되어있지 않음", addressId));
        }
        return userAddress.getResult();
    }

    /**
     * 주소 추가
     */
    @Transactional
    public void addUserAddress(UserAddressAddRequest request) {
        CommonResponse<Integer> userAddressCount;
        try {
            // 현재 주소 개수를 조회
            userAddressCount = addressAdapter.getUserAddressCount();
        } catch (FeignException.Unauthorized | FeignException.BadRequest ex) {
            throw new LoginRequiredException("고객이 주소 개수 조회를 요청했으나, 로그인 되어있지 않음");
        }

        Integer count = userAddressCount.getResult();

        if (count >= 10) {
            throw new UserAddressOverTenException("이미 주소가 10개 이상임");
        }

        try {
            // 실제 주소를 추가
            addressAdapter.addUserAddress(request);
        } catch (FeignException.Unauthorized | FeignException.BadRequest ex) {
            throw new LoginRequiredException("고객이 주소 추가를 요청했으나, 로그인 되어있지 않음");
        }
    }

    /**
     * 주소 수정
     */
    @Transactional
    public void updateUserAddress(UserAddressUpdateRequest request) {
        try {
            restTemplateAdapter.updateUserAddress(request);
        } catch (HttpClientErrorException ex) {
            throw new LoginRequiredException("고객이 주소 수정을 요청했으나, 로그인 되어있지 않음");
        }
    }

    /**
     * 기본 주소 변경
     */
    @Transactional
    public void updateDefaultAddress(UserChangeDefaultAddressRequest request) {
        try {
            restTemplateAdapter.updateDefaultAddress(request);
        } catch (HttpClientErrorException ex) {
            throw new LoginRequiredException("고객이 기본 주소 변경을 요청했으나, 로그인 되어있지 않음");
        }
    }

    /**
     * 주소 삭제
     */
    @Transactional
    public void deleteUserAddress(Long addressId) {
        CommonResponse<Void> deleteResponse;
        try {
            deleteResponse = addressAdapter.deleteUserAddress(addressId);

            if (!deleteResponse.getHeader().isSuccessful()) {
                throw new AddressDeleteException(deleteResponse.getHeader().getResultMessage());
            }
        } catch (FeignException.Unauthorized | FeignException.BadRequest ex) {
            throw new LoginRequiredException("고객이 주소 삭제를 요청했으나, 로그인 되어있지 않음");
        }

    }
}
