package com.nhnacademy.store99.front.auth.service.impl;

import com.nhnacademy.store99.front.auth.adaptor.AuthAdaptor;
import com.nhnacademy.store99.front.auth.dto.response.AdminCheckResponse;
import com.nhnacademy.store99.front.auth.service.AdminCheckService;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminCheckServiceImpl implements AdminCheckService {
    private final AuthAdaptor authAdaptor;

    @Override
    public Boolean checkAdmin(final String xUserToken) {
        CommonResponse<AdminCheckResponse> response = authAdaptor.checkAdmin(xUserToken).getBody();
        assert response != null;
        if (!response.getHeader().isSuccessful()) {
            return false;
        }
        return response.getResult().getIsAdmin();
    }
}
