package com.PolyRepo.PolyRepo.service.imp;

import com.PolyRepo.PolyRepo.payload.request.CateRequest;
import com.PolyRepo.PolyRepo.payload.response.CateResponse;

import java.util.List;

public interface CateServiceImp  {
    List<CateResponse> getAllCate();
//    boolean deletecate(int id);
    CateResponse addCate(CateRequest cateRequest);
    void deleteCatetById(Integer id);
}
