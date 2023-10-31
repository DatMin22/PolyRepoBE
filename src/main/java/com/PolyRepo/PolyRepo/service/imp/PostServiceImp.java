package com.PolyRepo.PolyRepo.service.imp;

//import com.cybersoft.cozastore_java21.payload.request.SignupRequest;


import com.PolyRepo.PolyRepo.payload.request.PostRequest;
import com.PolyRepo.PolyRepo.payload.request.SignupRequest;
import com.PolyRepo.PolyRepo.payload.response.PostResponse;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;

import java.util.List;

public interface PostServiceImp {

    List<PostResponse> getAllPost();

    PostResponse addPost(PostRequest postRequest);
//   List <PostResponse> getByCateId(int id);
List<PostResponse> getPostByID(int id);
    List<PostResponse> getPostByCateId(int id);

}

