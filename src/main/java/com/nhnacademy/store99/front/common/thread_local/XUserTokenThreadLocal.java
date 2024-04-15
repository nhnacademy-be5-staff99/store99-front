package com.nhnacademy.store99.front.common.thread_local;

/**
 * X_USER_TOKEN을 ThreadLocal로 관리하는 클래스
 *
 * @author seunggyu-kim
 */
public class XUserTokenThreadLocal {
    private static final ThreadLocal<String> X_USER_TOKEN = new ThreadLocal<>();

    private XUserTokenThreadLocal() {
        throw new IllegalStateException("Utility class");
    }

    public static String getXUserToken() {
        return X_USER_TOKEN.get();
    }

    public static void setXUserToken(String xUserToken) {
        X_USER_TOKEN.set(xUserToken);
    }

    public static void reset() {
        X_USER_TOKEN.remove();
    }
}
