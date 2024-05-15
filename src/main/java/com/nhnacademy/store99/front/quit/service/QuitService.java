package com.nhnacademy.store99.front.quit.service;

import static com.nhnacademy.store99.front.common.thread_local.XUserTokenThreadLocal.getXUserToken;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.store99.front.auth.adapter.LoginAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuitService {
    @Autowired
    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gateway;
    public static Long Id;

    private final LoginAdapter loginAdapter;


    public QuitService(LoginAdapter loginAdapter) {
        this.loginAdapter = loginAdapter;
        this.restTemplate = new RestTemplate();
    }

    public void quit() {
        HttpHeaders headers = new HttpHeaders();
        String xUserToken = getXUserToken(); // X-USER-TOKEN을 얻는 로직
        headers.set("X-USER-TOKEN", xUserToken);

// requestBody 없이 HttpEntity 생성
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                gateway + "/api/bookstore/v1/users/quit",
                requestEntity,
                String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("회원 탈퇴 요청이 성공적으로 전송되었습니다.");
            String response = responseEntity.getBody();
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response);
                String result = jsonNode.get("result").asText();
                Id = Long.valueOf(result);
            } catch (Exception e) {
                System.out.println("응답 데이터 처리 중 오류 발생: " + e.getMessage());
            }
        } else {
            System.out.println("회원 탈퇴 요청을 보내는 중 오류가 발생하였습니다. 응답 코드: " + responseEntity.getStatusCodeValue());
        }
    }
}
