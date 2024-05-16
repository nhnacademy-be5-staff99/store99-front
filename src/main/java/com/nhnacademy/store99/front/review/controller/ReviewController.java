package com.nhnacademy.store99.front.review.controller;

import com.nhnacademy.store99.front.common.response.CommonHeader;
import com.nhnacademy.store99.front.common.response.CommonResponse;
import com.nhnacademy.store99.front.review.dto.request.TextReviewRegisterRequest;
import com.nhnacademy.store99.front.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getReviewForm() {
        return "review/review_form";
    }


    @PostMapping("/text/register")
    public ResponseEntity<CommonResponse<String>> registerTextReview(@RequestHeader("X-USER-TOKEN") String userToken,
                                                                     @RequestBody TextReviewRegisterRequest request) {
        try {
            reviewService.registerTextReview(request, userToken);
            CommonHeader header = CommonHeader.builder()
                    .httpStatus(HttpStatus.OK)
                    .resultMessage("Success")
                    .build();
            CommonResponse<String> response = CommonResponse.<String>builder()
                    .header(header)
                    .result("Successfully registered text review")
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            CommonHeader header = CommonHeader.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .resultMessage("Error occurred: " + e.getMessage())
                    .build();
            CommonResponse<String> response = CommonResponse.<String>builder()
                    .header(header)
                    .result("faild to register text review: " + e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }
    }
}
