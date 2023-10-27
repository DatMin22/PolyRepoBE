package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.service.imp.CateServiceImp;
import com.PolyRepo.PolyRepo.service.imp.PostServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cate")
@CrossOrigin("*")
public class CateController {
    @Autowired
    CateServiceImp cateServiceImp;

    @GetMapping("/getAll")
    public ResponseEntity<?> getallCate() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(cateServiceImp.getAllCate());
        baseResponse.setMessage("Get All cate");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
