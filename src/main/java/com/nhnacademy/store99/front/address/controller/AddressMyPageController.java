package com.nhnacademy.store99.front.address.controller;

import com.nhnacademy.store99.front.address.dto.AddressMyPageResponse;
import com.nhnacademy.store99.front.address.service.AddressMyPageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 마이페이지에서 회원의 주소를 조회하고 관리하기 위한 컨트롤러
 *
 * @author Ahyeon Song
 */
@Controller
@RequiredArgsConstructor
public class AddressMyPageController {

    private final AddressMyPageService addressMyPageService;

    /**
     * 회원의 주소 리스트를 보여주기 위한 메소드
     *
     * @return AddressMyPageResponse
     */
    @GetMapping(value = "/my_address_info", produces = "application/json")
    @ResponseBody
    public List<AddressMyPageResponse> getMyAddressInfo() {
        return addressMyPageService.getUserAddresses();
    }

}
