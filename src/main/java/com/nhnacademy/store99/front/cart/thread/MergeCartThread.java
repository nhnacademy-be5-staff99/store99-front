package com.nhnacademy.store99.front.cart.thread;

import javax.servlet.http.Cookie;

public class MergeCartThread implements Runnable {
    private final Cookie cartItemCookie;

    public MergeCartThread(Cookie cartItemCookie) {
        this.cartItemCookie = cartItemCookie;
    }

    @Override
    public void run() {

    }
}
