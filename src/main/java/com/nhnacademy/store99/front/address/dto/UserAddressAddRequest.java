package com.nhnacademy.store99.front.address.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ahyeon Song
 */
@AllArgsConstructor
@Getter
public class UserAddressAddRequest {
    private String addressGeneral;
    private String addressDetail;
    private String addressAlias;
    private Integer addressCode;
}
