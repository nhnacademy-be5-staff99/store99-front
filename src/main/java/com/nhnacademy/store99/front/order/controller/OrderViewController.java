package com.nhnacademy.store99.front.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderViewController {
    @GetMapping("/order")
    public String getOrderIndex() {
        return "order/order_index";
    }
}
