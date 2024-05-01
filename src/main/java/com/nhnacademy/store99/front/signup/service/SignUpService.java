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

/**
 * @author jinhyogyeom
 */
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


    /**
     * 북스토어에 이메일 인증요청을 보내는 메소드
     * 이메일 인증번호를 반환
     *
     * @param email
     * @return void
     */
    public void mailConfirm(String email) {
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

    /**
     * 북스토어에 비밀번호 중복검사 요청을 보내는 메소드
     * 중복검사한 값 (true/false)를 반환
     *
     * @param password
     * @return void
     */

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


    /**
     * form에서 받은  값들로 북스토어에 회원가입 요청을 보내는 메소드
     * 회원가입성공여부 메세지를 반환
     *
     * @param signUpDto
     * @return void
     */
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
