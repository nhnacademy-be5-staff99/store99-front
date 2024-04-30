package com.nhnacademy.store99.front.address.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 마이페이지에서 회원의 주소 리스트를 보여주기 위한 형식
 *
 * @author Ahyeon Song
 */
@Getter
@AllArgsConstructor
public class UserAddressResponse {
    private Long addressId;
    private String addressGeneral;
    private String addressDetail;
    private String addressAlias;
    private Integer addressCode;
    private Boolean isDefaultAddress;
}
