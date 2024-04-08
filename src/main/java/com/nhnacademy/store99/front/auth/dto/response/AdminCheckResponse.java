package com.nhnacademy.store99.front.auth.dto.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AdminCheckResponse {
    private Boolean isAdmin;

    public AdminCheckResponse(final Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }
}
