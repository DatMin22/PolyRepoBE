package com.PolyRepo.PolyRepo.service.imp;

//import com.cybersoft.cozastore_java21.payload.request.SignupRequest;


import com.PolyRepo.PolyRepo.payload.request.PostRequest;
import com.PolyRepo.PolyRepo.payload.response.PostResponse;

import java.util.List;

public interface PostServiceImp {
//    boolean addPosst(PostRequest postRequest);
    List<PostResponse> getAllPost();

    PostResponse addPost(PostRequest postRequest);

    PostResponse getPostById(Integer id);
    List<PostResponse> getPostByCateId(int id);
    void deletePostById(Integer id);
    PostResponse updatePost(Integer id, String title, String desc, String filename);

}

