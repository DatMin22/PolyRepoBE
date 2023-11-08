package com.PolyRepo.PolyRepo.service;


import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {


//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendEmail(String toEmail, String subject, String body) {
//        MimeMessage message = mailSender.createMimeMessage();
//        try {
//            message.setFrom("tn597560@gmail.com");
//            message.setRecipients(Message.RecipientType.TO, toEmail);
//            message.setSubject(subject);
//            message.setText(body);
//
//            mailSender.send(message);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    private final JavaMailSender mailSender;
//
//    public EmailService(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    public void sendPasswordResetMail(String email, String token) {
//        // Lưu mã thông báo vào cơ sở dữ liệu
//        // Gửi email
//        MimeMessage message = mailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom("no-reply@example.com");
//            helper.setTo(email);
//            helper.setSubject("Reset password");
//            helper.setText("Click on the link below to reset your password: ");
//            mailSender.send(message);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
@Autowired
private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        // Tạo một đối tượng Email
        MimeMessage message = mailSender.createMimeMessage();
        try {
            // Đặt người gửi
            message.setFrom(new InternetAddress("no-reply@example.com"));

            // Đặt người nhận
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Đặt chủ đề
            message.setSubject(subject);

            // Đặt nội dung
            message.setText(body);

            // Gửi email
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
