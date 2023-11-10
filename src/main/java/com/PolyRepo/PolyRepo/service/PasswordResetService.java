package com.PolyRepo.PolyRepo.service;

import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import com.PolyRepo.PolyRepo.service.imp.PasswordResetServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordResetService implements PasswordResetServiceImp {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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


    // lưu token khi tạo
    public void savePasswordResetToken(String token, String email) {
        UserEntity user = userRepository.findByEmail(email);
        user.setResetToken(token);
        userRepository.save(user);
    }


    //validate token
    public boolean validatePasswordResetToken(String token) {
        return userRepository.findByResetToken(token) != null;
    }

    // đổi mật khẩu
    public void changePassword(String token, String newPassword) {
        UserEntity user = userRepository.findByResetToken(token);
        if(user != null) {

            String encodedPassword = passwordEncoder.encode(newPassword);

            user.setPasswords(encodedPassword); //set password mới cho user

            userRepository.save(user); //lưu lại user

        }
    }
    // xóa token
    public void deletePasswordResetToken(String token) {
        UserEntity user = userRepository.findByResetToken(token);
        user.setResetToken(null);
        userRepository.save(user);

    }

}
