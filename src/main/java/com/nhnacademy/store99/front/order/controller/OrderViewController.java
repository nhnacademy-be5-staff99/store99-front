package com.nhnacademy.store99.front.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.store99.front.order.dto.request.OrderBookRequest;
import com.nhnacademy.store99.front.order.dto.response.BookInOrderResponse;
import com.nhnacademy.store99.front.order.service.OrderQueryService;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author seunggyu-kim
 */
@Controller
@RequiredArgsConstructor
public class OrderViewController {
    private final OrderQueryService orderQueryService;

    @PostMapping("/checkout")
    public String getOrderIndex(@RequestAttribute boolean isLogin, Model model,
                                @ModelAttribute OrderBookRequest orderBookRequest) {
        List<BookInOrderResponse> orderBookList =
                orderQueryService.getOrderBookList(orderBookRequest.getOrderBookRequestList());
        model.addAttribute("orderId", UUID.randomUUID().toString());
        model.addAttribute("orderBookList", orderBookList);
        model.addAttribute("totalProductPrice",
                orderBookList.stream().mapToInt(BookInOrderResponse::getBookPrice).sum());
        model.addAttribute("orderName", getOrderName(orderBookList));
        return isLogin ? "order/checkout_main_member" : "order/checkout_main_guest";
    }

    /**
     * 주문 도서 리스트로 받아서 주문명 반환
     * 주문 도서 외 n권 형식으로 반환
     *
     * @param orderBookList 주문 도서 리스트
     * @return 주문명
     */
    private @NotNull String getOrderName(final List<BookInOrderResponse> orderBookList) {
        if (orderBookList.size() == 1) {
            return orderBookList.get(0).getBookTitle();
        }
        return orderBookList.get(0).getBookTitle() + " 외 " + (orderBookList.size() - 1) + "권";
    }

    /**
     * 인증성공처리
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String paymentRequest(HttpServletRequest request, Model model) throws Exception {
        return "order/success";
    }

    /**
     * 인증실패처리
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fail", method = RequestMethod.GET)
    public String failPayment(HttpServletRequest request, Model model) throws Exception {
        String failCode = request.getParameter("code");
        String failMessage = request.getParameter("message");

        model.addAttribute("code", failCode);
        model.addAttribute("message", failMessage);

        return "order/fail";
    }

    @RequestMapping(value = "/confirm")
    public ResponseEntity<ObjectNode> confirmPayment(@RequestBody String jsonBody) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String orderId;
        String amount;
        String paymentKey;
        try {
            // 클라이언트에서 받은 JSON 요청 바디입니다.
            Map<String, Object> requestData = objectMapper.readValue(jsonBody, Map.class);
            paymentKey = (String) requestData.get("paymentKey");
            orderId = (String) requestData.get("orderId");
            amount = (String) requestData.get("amount");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ObjectNode obj = objectMapper.createObjectNode();
        obj.put("orderId", orderId);
        obj.put("amount", amount);
        obj.put("paymentKey", paymentKey);

        // TODO: 개발자센터에 로그인해서 내 결제위젯 연동 키 > 시크릿 키를 입력하세요. 시크릿 키는 외부에 공개되면 안돼요.
        // @docs https://docs.tosspayments.com/reference/using-api/api-keys
        String widgetSecretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";

        // 토스페이먼츠 API는 시크릿 키를 사용자 ID로 사용하고, 비밀번호는 사용하지 않습니다.
        // 비밀번호가 없다는 것을 알리기 위해 시크릿 키 뒤에 콜론을 추가합니다.
        // @docs https://docs.tosspayments.com/reference/using-api/authorization#%EC%9D%B8%EC%A6%9D
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = "Basic " + new String(encodedBytes);

        // 결제 승인 API를 호출하세요.
        // 결제를 승인하면 결제수단에서 금액이 차감돼요.
        // @docs https://docs.tosspayments.com/guides/payment-widget/integration#3-결제-승인하기
        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(objectMapper.writeValueAsString(obj).getBytes(StandardCharsets.UTF_8));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        // TODO: 결제 성공 및 실패 비즈니스 로직을 구현하세요.
        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        ObjectNode jsonObject = objectMapper.readValue(reader, ObjectNode.class);
        responseStream.close();

        return ResponseEntity.status(code).body(jsonObject);
    }
}
