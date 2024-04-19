package com.nhnacademy.store99.front.auth.exception;

/**
 * 로그인 상태 확인 중 Gateway 에서 401 Error 를 받았을 시 예외
 *
 * @author Ahyeon Song
 */
public class LoginCheckException extends RuntimeException {
    public LoginCheckException(String message) {
        super(message);
    }
}
