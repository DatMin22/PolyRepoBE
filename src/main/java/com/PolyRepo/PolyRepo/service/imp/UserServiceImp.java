package com.PolyRepo.PolyRepo.service.imp;

//import com.cybersoft.cozastore_java21.payload.request.SignupRequest;

import com.PolyRepo.PolyRepo.payload.request.SignupRequest;

public interface UserServiceImp {
    boolean addUser(SignupRequest request);
}
