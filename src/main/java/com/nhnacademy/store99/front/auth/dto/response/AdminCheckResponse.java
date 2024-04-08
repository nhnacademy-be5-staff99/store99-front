package com.nhnacademy.store99.front.auth.dto.response;

import lombok.NoArgsConstructor;

/**
 * 관리자 권한 체크 응답 객체
 *
 * @author seunggyu-kim
 */
@NoArgsConstructor
public class AdminCheckResponse {
    /**
     * 관리자 여부
     */
    private Boolean isAdmin;

    public AdminCheckResponse(final Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }
}
