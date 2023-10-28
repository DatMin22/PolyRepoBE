//package com.PolyRepo.PolyRepo.service;
//
//
//import com.PolyRepo.PolyRepo.Entity.UserEntity;
//import com.PolyRepo.PolyRepo.repository.UserRepository;
//import jakarta.mail.Message;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//public class EmailService {
//
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
//}
