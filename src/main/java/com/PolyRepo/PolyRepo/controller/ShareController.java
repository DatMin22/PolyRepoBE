package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.service.imp.ShareServiceImp;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/share")
@CrossOrigin("*")
public class ShareController {
    @Autowired
    ShareServiceImp shareServiceImp;

    @GetMapping("/all")
    public ResponseEntity<?> getAllShare() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(shareServiceImp.getAllShare());
        baseResponse.setMessage("Get All Share");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
