package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.payload.request.ResetPasswordRequest;
import com.PolyRepo.PolyRepo.payload.response.ResetPasswordResponse;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reset-password")
public class ResetPasswordController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ResetPasswordResponse> sendResetPasswordCode(@RequestBody ResetPasswordRequest request) {
        // Lấy email người dùng nhập vào
        String email = request.getEmail();

        // Tạo mã random
        int code = (int) Math.floor(Math.random() * 999999) + 100000;

        // Lưu mã random vào CSDL
        UserEntity user = userRepository.findByEmail(email);
        user.setResetCode(code);
        userRepository.save(user);

        // Gửi email cho người dùng
        // ...

        // Tạo phản hồi thành công
        ResetPasswordResponse response = new ResetPasswordResponse();
        response.setSuccess(true);
        response.setMessage("Yêu cầu đã được gửi thành công.");
        return ResponseEntity.ok(response);
    }
}
