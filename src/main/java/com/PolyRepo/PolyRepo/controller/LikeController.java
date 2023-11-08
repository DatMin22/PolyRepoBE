package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.LikeRequest;
import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.payload.response.LikeResponse;
import com.PolyRepo.PolyRepo.service.imp.LikeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/add")
    public ResponseEntity<?> addLike(@RequestBody LikeRequest likeRequest) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            LikeResponse likeResponse = likeServiceImp.addLike(likeRequest);
            baseResponse.setData(likeResponse);
            baseResponse.setMessage("Likes added successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (CustomException e) {
            baseResponse.setData(null);
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatusCode(400);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLikeById(@PathVariable int id) {

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(likeServiceImp.getLikeById(id));
        response.setMessage("Like By ID success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getLikeByuserId(@PathVariable int id) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(likeServiceImp.getLikeByUserId(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getLikeByPostId(@PathVariable int id) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(likeServiceImp.getLikeByUserId(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLikeById(@PathVariable("id") Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            likeServiceImp.deleteLikeById(id);
            baseResponse.setMessage("Like deleted successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (CustomException e) {
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatusCode(400);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getlike")
    public ResponseEntity<BaseResponse> getLikeByPostIdOrUserId(
            @RequestParam(name = "postId", required = false) int postId,
            @RequestParam(name = "userId", required = false) int userId) {

        BaseResponse response = new BaseResponse();

        List<LikeResponse> likes = likeServiceImp.getLikeByPostIdOrUserId(postId, userId);

        if(likes.isEmpty()) {
            response.setStatusCode(204);
            response.setMessage("No content");
            return ResponseEntity.noContent().build();
        }

        response.setStatusCode(200);
        response.setMessage("Success");
        response.setData(likes);

        return ResponseEntity.ok(response);
    }
}
