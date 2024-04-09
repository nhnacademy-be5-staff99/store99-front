package com.nhnacademy.store99.front.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ahyeon Song
 */
@Getter
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}
