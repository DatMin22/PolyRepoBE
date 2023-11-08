package com.PolyRepo.PolyRepo.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;


public class SignupRequest {

    private String username;
    @NotNull
    @NotEmpty


    private String password;

    @NotNull(message = "Email not null")
    @NotEmpty(message = "Email not empty")
    @Email(message = "Email invalid format")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
