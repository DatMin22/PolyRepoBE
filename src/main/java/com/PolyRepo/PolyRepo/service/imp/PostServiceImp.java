package com.PolyRepo.PolyRepo.service.imp;

//import com.cybersoft.cozastore_java21.payload.request.SignupRequest;

import com.PolyRepo.PolyRepo.payload.request.SignupRequest;
import com.PolyRepo.PolyRepo.payload.response.PostResponse;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;

import java.util.List;

public interface PostServiceImp {

    List<PostResponse> getAllPost();

}

