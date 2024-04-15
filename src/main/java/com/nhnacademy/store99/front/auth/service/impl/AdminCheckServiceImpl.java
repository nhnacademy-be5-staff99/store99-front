package com.nhnacademy.store99.front.auth.service.impl;

import com.nhnacademy.store99.front.admin.exception.AdminPermissionDeniedException;
import com.nhnacademy.store99.front.auth.adapter.AuthAdapter;
import com.nhnacademy.store99.front.auth.dto.response.AdminCheckResponse;
import com.nhnacademy.store99.front.auth.service.AdminCheckService;
import com.nhnacademy.store99.front.common.thread_local.XUserTokenThreadLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 관리자 권한 체크 서비스 구현체
 *
 * @author seunggyu-kim
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminCheckServiceImpl implements AdminCheckService {
    private final AuthAdapter authAdapter;

    /**
     * 관리자 권한 체크
     *
     * @return 관리자 여부
     */
    @Override
    public Boolean checkAdmin() {
        AdminCheckResponse response = authAdapter.checkAdmin(XUserTokenThreadLocal.getXUserToken()).orElseThrow(
                AdminPermissionDeniedException::new);
        return response.isAdmin();
    }
}
