package com.nhnacademy.store99.front.address.controller;

import com.nhnacademy.store99.front.address.dto.UserAddressAddRequest;
import com.nhnacademy.store99.front.address.dto.UserAddressResponse;
import com.nhnacademy.store99.front.address.dto.UserAddressUpdateRequest;
import com.nhnacademy.store99.front.address.dto.UserChangeDefaultAddressRequest;
import com.nhnacademy.store99.front.address.service.AddressMyPageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public List<UserAddressResponse> getMyAddressInfo() {
        return addressMyPageService.getUserAddresses();
    }

    @GetMapping("/my_address_view")
    public ModelAndView getMyAddressManageView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/mypage_address");
        List<UserAddressResponse> userAddresses = addressMyPageService.getUserAddresses();

        mv.addObject("userAddresses", userAddresses);
        return mv;
    }

    @PostMapping("/address/add")
    public String addUserAddress(@RequestBody UserAddressAddRequest request) {
        addressMyPageService.addUserAddress(request);

        return "redirect:/my_address_view";
    }

    @PatchMapping("/address/update")
    public String updateUserAddress(@RequestBody UserAddressUpdateRequest request) {
        addressMyPageService.updateUserAddress(request);

        return "redirect:/my_address_view";
    }

    @PatchMapping("/address/change_default")
    public ResponseEntity<Void> updateDefaultAddress(@RequestBody UserChangeDefaultAddressRequest request) {
        addressMyPageService.updateDefaultAddress(request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/address/{addressId}")
    @ResponseBody
    public ResponseEntity<Void> deleteUserAddress(@PathVariable("addressId") Long addressId) {
        addressMyPageService.deleteUserAddress(addressId);

        return ResponseEntity.ok().build();
    }

}
