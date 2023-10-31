package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.PostRequest;
import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.payload.response.PostResponse;
//>>>>>>> 69eddf533d2b20a936dcd79ba8268af4c0bed0e9
import com.PolyRepo.PolyRepo.service.imp.PostServiceImp;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody PostRequest postRequest) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            PostResponse postResponse= postServiceImp.addPost(postRequest);
            baseResponse.setData(postResponse);
            baseResponse.setMessage("Posts added successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (CustomException e) {
            baseResponse.setData(null);
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatusCode(400);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
