package com.nhnacademy.store99.front.auth.dto;

import com.nhnacademy.store99.front.common.response.CommonHeader;
import lombok.Getter;

/**
 * @author Ahyeon Song
 */
@Getter
public class LoginResponse {
    private final CommonHeader header;
    private final String result;

    public LoginResponse(CommonHeader header, String result) {
        this.header = header;
        this.result = result;
    }
}
