package com.PolyRepo.PolyRepo.service.imp;

import org.springframework.stereotype.Service;

@Service
public interface PasswordResetServiceImp {
    void sendPasswordResetMail(String email, String token);

    String generatePasswordResetToken();

    boolean validatePasswordResetToken(String token);

    void changePassword(String token, String password);
}
