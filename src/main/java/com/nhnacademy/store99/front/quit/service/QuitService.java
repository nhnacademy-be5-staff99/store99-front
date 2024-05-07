package com.nhnacademy.store99.front.quit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuitService {
    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gateway;

    public QuitService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void quit(String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"email\":\"" + email + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                gateway + "/open/bookstore/v1/users/quit",
                requestEntity,
                String.class);
    }
}
