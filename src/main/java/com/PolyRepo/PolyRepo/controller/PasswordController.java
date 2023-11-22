package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.payload.request.PasswordResetRequest;
import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/reset")
    public ResponseEntity<BaseResponse> resetPassword(
            @RequestBody PasswordResetRequest request
    ) {
        // Lấy địa chỉ email của người dùng
        String email = request.getEmail();

        // Tạo mã thông báo đặt lại mật khẩu
        String token = passwordResetService.generatePasswordResetToken();

        // Gửi mail cho người dùng
        passwordResetService.sendPasswordResetMail(email, token);

        // Tạo đối tượng BaseResponse
        BaseResponse response = new BaseResponse();
        response.setMessage("Một email đã được gửi đến địa chỉ email của bạn. Vui lòng kiểm tra hộp thư đến của bạn.");
        response.setStatusCode(HttpStatus.OK);
        response.setData(new HashMap<String, String>() {{
            put("email", email);
            put("token", token);
        }});

        // Trả về đối tượng BaseResponse
        return ResponseEntity.ok(response);
    }
}
