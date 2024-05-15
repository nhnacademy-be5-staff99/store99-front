package com.nhnacademy.store99.front.review.service.impl;

import com.nhnacademy.store99.front.auth.service.LoginService;
import com.nhnacademy.store99.front.common.thread_local.XUserTokenThreadLocal;
import com.nhnacademy.store99.front.review.dto.request.TextReviewRegisterRequest;
import com.nhnacademy.store99.front.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {

    private final RestTemplate restTemplate;
    private final LoginService loginService;

    @Value("${gateway.url}")
    private String gateway;

    public ReviewServiceImpl(RestTemplate restTemplate, LoginService loginService) {
        this.restTemplate = restTemplate;
        this.loginService = loginService;
    }

    @Override
    public void registerTextReview(TextReviewRegisterRequest request) {
        String token = XUserTokenThreadLocal.getXUserToken();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setBearerAuth(token);

        String requestBody = "{"
                + "\"reviewDescription\":\"" + request.getReviewDescription() + "\","
                + "\"reviewRate\":\"" + request.getReviewRate() + "\","
                + "\"bookId\":\"" + request.getBookId() + "\""
                + "}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, header);

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(gateway + "/api/bookstore/v1/review/text/register", requestEntity,
                        String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("리뷰를 등록 요청이 성공적으로 전송되었습니다.");
        } else {
            System.out.println("리뷰 등록 요청 중 오류가 발생했습니다. 오류 코드: " + responseEntity.getStatusCodeValue());
        }
    }
}
