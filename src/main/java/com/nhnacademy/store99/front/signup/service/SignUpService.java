package com.nhnacademy.store99.front.signup.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.store99.front.signup.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SignUpService {

    public static String code;
    public static String isDuplicate;
    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gateway;

    public SignUpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void mailConfirm(String email) {
        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"email\":\"" + email + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                 gateway + "/open/bookstore/v1/users/mailConfirm",
                requestEntity,
                String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("메일 확인 요청이 성공적으로 전송되었습니다.");
            String response = responseEntity.getBody();
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response);
                String result = jsonNode.get("result").asText();
                code = result;
            } catch (Exception e) {
                System.out.println("응답 데이터 처리 중 오류 발생: " + e.getMessage());
            }
        } else {
            System.out.println("메일 확인 요청을 보내는 중 오류가 발생하였습니다. 응답 코드: " + responseEntity.getStatusCodeValue());
        }
    }

    public void duplicateCheck(String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"password\":\"" + password + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);



        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                gateway + "/open/bookstore/v1/users/duplicateCheck",
                requestEntity,
                String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("중복 확인 요청이 성공적으로 전송되었습니다.");
            String response =  responseEntity.getBody();
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response);
                String result = jsonNode.get("result").asText();
                isDuplicate = result;
            } catch (Exception e) {
                System.out.println("응답 데이터 처리 중 오류 발생: " + e.getMessage());
            }
        } else {
            System.out.println("중복 확인 요청을 보내는 중 오류가 발생하였습니다. 응답 코드: " + responseEntity.getStatusCodeValue());
        }
    }

    public void signUp(SignUpDto signUpDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{"
                + "\"userBirthDate\":\"" + signUpDto.getUserBirthDate() + "\","
                + "\"name\":\"" + signUpDto.getName() + "\","
                + "\"email\":\"" + signUpDto.getEmail() + "\","
                + "\"phoneNumber\":\"" + signUpDto.getPhoneNumber() + "\","
                + "\"password\":\"" + signUpDto.getPassword() + "\","
                + "\"address\":{"
                + "\"addressGeneral\":\"" + signUpDto.getAddress().getAddressGeneral() + "\","
                + "\"addressDetail\":\"" + signUpDto.getAddress().getAddressDetail() + "\","
                + "\"addressAlias\":\"" + signUpDto.getAddress().getAddressAlias() + "\","
                + "\"addressCode\":" + signUpDto.getAddress().getAddressCode() + ","
                + "\"isDefaultAddress\":" + true
                + "}"
                + "}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                gateway + "/open/bookstore/v1/users/sign-up",
                requestEntity,
                String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("회원가입 요청이 성공적으로 전송되었습니다.");
        } else {
            System.out.println("회원가입 요청을 보내는 중 오류가 발생하였습니다. 응답 코드: " + responseEntity.getStatusCodeValue());
        }
    }

}
