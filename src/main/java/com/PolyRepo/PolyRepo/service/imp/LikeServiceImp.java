package com.PolyRepo.PolyRepo.service.imp;

import com.PolyRepo.PolyRepo.payload.request.CateRequest;
import com.PolyRepo.PolyRepo.payload.response.CateResponse;
import com.PolyRepo.PolyRepo.payload.response.LikeResponse;

import java.util.List;

public interface LikeServiceImp {
    List<LikeResponse> getAlllike();

}
