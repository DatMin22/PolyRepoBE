package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.service.imp.PostServiceImp;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

        @Autowired
        PostServiceImp postServiceImp;

        @GetMapping("/getAll")
        public ResponseEntity<?> getAllpost() {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setData(postServiceImp.getAllPost());
            baseResponse.setMessage("Get All Post");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }
}
