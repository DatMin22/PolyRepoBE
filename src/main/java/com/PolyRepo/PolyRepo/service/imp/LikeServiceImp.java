package com.PolyRepo.PolyRepo.service.imp;

import com.PolyRepo.PolyRepo.payload.request.CateRequest;
import com.PolyRepo.PolyRepo.payload.request.LikeRequest;
import com.PolyRepo.PolyRepo.payload.response.CateResponse;
import com.PolyRepo.PolyRepo.payload.response.LikeResponse;

import java.util.List;

public interface LikeServiceImp {
    List<LikeResponse> getAlllike();
    LikeResponse addLike(LikeRequest likeRequest);


    LikeResponse getLikeById(Integer id);

    List<LikeResponse> getLikeByUserId(int id);
    List<LikeResponse> getLikeByPostId(int id);

    void deleteLikeById(Integer id);
    List<LikeResponse> getLikeByPostIdOrUserId(int postId, int userId);


}
