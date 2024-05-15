package com.nhnacademy.store99.front.order.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

/**
 * @author seunggyu-kim
 */
@Getter
@Setter
public class ConfirmPaymentResponse {
    private boolean isSuccess;

    private JSONObject jsonObject;
}
