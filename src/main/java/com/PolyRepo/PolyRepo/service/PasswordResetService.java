package com.PolyRepo.PolyRepo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordResetService {
    @Autowired
    private EmailService emailService;

    public void sendPasswordResetMail(String email, String token) {
        // Tạo email
        String subject = "Lấy lại mật khẩu";
        String body = "Bạn đã yêu cầu lấy lại mật khẩu. Để đặt lại mật khẩu, vui lòng nhập mã thông báo sau: "
                + token;

        // Gửi email
        emailService.sendEmail(email, subject, body);
    }

    public String generatePasswordResetToken() {
        // Tạo mã thông báo đặt lại mật khẩu
        return UUID.randomUUID().toString().substring(0, 6);
    }
}
