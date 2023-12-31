package com.PolyRepo.PolyRepo.service.imp;

import com.PolyRepo.PolyRepo.payload.request.ShareRequest;
import com.PolyRepo.PolyRepo.payload.request.SignupRequest;
import com.PolyRepo.PolyRepo.payload.response.PostResponse;
import com.PolyRepo.PolyRepo.payload.response.ShareResponse;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;

import java.util.List;

public interface ShareServiceImp {
    List<ShareResponse> getAllShare();

    ShareResponse addShare(ShareRequest shareRequest);

    ShareResponse updateShare(Integer id,String content);

    void deleteShareByID(Integer id);
    List<ShareResponse> getShareByUserId(int id);

//    void updateShareByID(Integer id);
}
