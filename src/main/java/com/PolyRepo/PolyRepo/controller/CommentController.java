package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.Entity.CommentEntity;
import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.service.CommentService;
import com.PolyRepo.PolyRepo.service.imp.CommentServiceImp;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    CommentServiceImp commentServiceImp;
    @GetMapping("/all")
    public ResponseEntity<?> getAllComments() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(commentServiceImp.getAllComments());
        baseResponse.setMessage("Get All Comment");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
