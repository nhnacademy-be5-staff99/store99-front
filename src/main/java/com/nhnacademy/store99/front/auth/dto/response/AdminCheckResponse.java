package com.nhnacademy.store99.front.auth.dto.response;

/**
 * 관리자 권한 체크 응답 객체
 *
 * @author seunggyu-kim
 */
public class AdminCheckResponse {
    /**
     * 관리자 여부
     */
    private Boolean isAdmin;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(final boolean admin) {
        isAdmin = admin;
    }
}
