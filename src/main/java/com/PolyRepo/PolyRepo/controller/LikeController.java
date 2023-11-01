package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.service.imp.CateServiceImp;
import com.PolyRepo.PolyRepo.service.imp.LikeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/like")
@CrossOrigin("*")

public class LikeController {
    @Autowired
    LikeServiceImp likeServiceImp;

    @GetMapping("/getAll")
    public ResponseEntity<?> getallLike() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(likeServiceImp.getAlllike());
        baseResponse.setMessage("Get All like");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
