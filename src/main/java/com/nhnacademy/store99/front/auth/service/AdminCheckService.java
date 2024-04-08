package com.nhnacademy.store99.front.auth.service;

/**
 * 관리자 권한 체크 서비스 인터페이스
 *
 * @author seunggyu-kim
 */
public interface AdminCheckService {
    Boolean checkAdmin(String xUserToken);
}
