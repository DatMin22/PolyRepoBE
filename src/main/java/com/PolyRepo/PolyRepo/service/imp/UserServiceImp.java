package com.PolyRepo.PolyRepo.service.imp;

//import com.cybersoft.cozastore_java21.payload.request.SignupRequest;

import com.PolyRepo.PolyRepo.payload.request.SignupRequest;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;

import java.util.List;

public interface UserServiceImp {
    boolean addUser(SignupRequest request);
    List<UserResponse> getAllUser();
    List<UserResponse> searchUserByNameOrEmail(String query);
}

