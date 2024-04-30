package com.nhnacademy.store99.front.mypage.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MainMyPageResponse {
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;
    private LocalDate userBirthDay;
    private Integer userPoint;
    private UserAddress userAddress;
    private UserGrade userGrade;

    @Getter
    @AllArgsConstructor
    public static class UserAddress {
        private String addressGeneral;
        private String addressDetail;
        private String addressAlias;
        private Integer addressCode;
    }

    @Getter
    @AllArgsConstructor
    public static class UserGrade {
        private String gradeName;
        private Integer gradeStartCost;
        private Integer gradeEndCost;
        private Integer gradeRatio;
    }

}
