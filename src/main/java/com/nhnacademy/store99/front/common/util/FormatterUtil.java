package com.nhnacademy.store99.front.common.util;

import org.springframework.stereotype.Component;

/**
 * 형식 변환을 위한 유틸 클래스
 *
 * @author Ahyeon Song
 */
@Component
public class FormatterUtil {

    /**
     * 하이픈 없이 오는 전화번호를 하이픈을 붙여서 반환
     *
     * @param phone 01012345678
     * @return phone - 010-1234-5678
     */
    public String setPhoneNum(String phone) {

        if (phone.length() == 11) {
            return phone.substring(0, 3) + "-" + phone.substring(3, 7) + "-" + phone.substring(7);
        } else if (phone.length() == 10) {
            return phone.substring(0, 3) + "-" + phone.substring(3, 6) + "-" + phone.substring(6);
        } else {
            return phone;
        }
    }
}
