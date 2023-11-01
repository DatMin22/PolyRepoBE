package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUser() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.getAllUser());
        baseResponse.setMessage("Get All User");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUser(@RequestParam("query") String query) {
        List<UserResponse> userList = userServiceImp.searchUserByNameOrEmail(query.toLowerCase());
        return ResponseEntity.ok(userList);
    }
}
