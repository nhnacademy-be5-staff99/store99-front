package com.nhnacademy.store99.front.order.service.impl;

import com.nhnacademy.store99.front.order.adapter.OrderOpenAdapter;
import com.nhnacademy.store99.front.order.dto.request.PaymentKeyRequest;
import com.nhnacademy.store99.front.order.dto.response.ConfirmPaymentResponse;
import com.nhnacademy.store99.front.order.service.OrderService;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

/**
 * @author seunggyu-kim
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderOpenAdapter orderOpenAdapter;

    /**
     * front에서 서버로 api 요청을 보내 구매자, 주문 정보(결제 대기 상태)를 DB에 저장한 후 실행되는 메소드
     * 성공적으로 수행되면 주문 상태를 완료로 바꾼다.
     */
    @Override
    public ConfirmPaymentResponse confirmPayment(final String orderId, final Integer amount, final String paymentKey)
            throws Exception {
        HttpURLConnection connection;
        try {
            String secretKey = "test_sk_Z1aOwX7K8m12DZ9omOOq8yQxzvNP:";

            Base64.Encoder encoder = Base64.getEncoder();
            byte[] encodedBytes = encoder.encode(secretKey.getBytes(StandardCharsets.UTF_8));
            String authorizations = "Basic " + new String(encodedBytes);

            URL url = new URL("https://api.tosspayments.com/v1/payments/" + paymentKey);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorizations);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            JSONObject obj = new JSONObject();
            obj.put("orderId", orderId);
            obj.put("amount", amount);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(obj.toString().getBytes(StandardCharsets.UTF_8));

        } catch (Exception ex) {
            // 결제 도중 예외 발생 시, 주문 정보 삭제
            orderOpenAdapter.undoPendingPayment(orderId);
            throw new Exception(ex);
        }

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;

        if (isSuccess) {
            // 결제 정보 성공으로 저장
            orderOpenAdapter.successPendingPayment(orderId, new PaymentKeyRequest(paymentKey));
        } else {
            try {
                // 결제 정보 실패로 저장
                orderOpenAdapter.failPendingPayment(orderId, new PaymentKeyRequest(paymentKey));
            } catch (Exception ex) {

            }
        }

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        responseStream.close();

        ConfirmPaymentResponse confirmPaymentResponse = new ConfirmPaymentResponse();
        confirmPaymentResponse.setSuccess(isSuccess);
        confirmPaymentResponse.setJsonObject(jsonObject);
        return confirmPaymentResponse;
    }
}
