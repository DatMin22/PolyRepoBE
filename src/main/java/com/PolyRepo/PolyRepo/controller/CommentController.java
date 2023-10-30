package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.Entity.CommentEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.CommentRequest;
import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.payload.response.CommentResponse;
import com.PolyRepo.PolyRepo.service.CommentService;
import com.PolyRepo.PolyRepo.service.imp.CommentServiceImp;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody CommentRequest commentRequest) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            CommentResponse addedComment = commentServiceImp.addComment(commentRequest);
            baseResponse.setData(addedComment);
            baseResponse.setMessage("Comment added successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (CustomException e) {
            baseResponse.setData(null);
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatusCode(400);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable("id") Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            commentServiceImp.deleteCommentById(id);
            baseResponse.setMessage("Comment deleted successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (CustomException e) {
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatusCode(400);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
