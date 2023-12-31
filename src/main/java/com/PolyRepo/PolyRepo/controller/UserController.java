package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.exception.InvalidPasswordException;
import com.PolyRepo.PolyRepo.payload.request.PasswordChangeRequest;
import com.PolyRepo.PolyRepo.payload.request.UserRequest;
import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import com.PolyRepo.PolyRepo.service.UserService;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import com.PolyRepo.PolyRepo.utils.JwtHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    UserService userService;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUser() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.getAllUser());
        baseResponse.setMessage("Get All User");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable int id) {
        return userServiceImp.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            userServiceImp.deleteUserById(id);
            baseResponse.setMessage("User deleted successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (CustomException e) {
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatusCode(400);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            UserResponse updatedUser = userServiceImp.updateUser(id, userRequest);
            baseResponse.setData(updatedUser);
            baseResponse.setMessage("User with ID " + id + " has been updated successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (CustomException e) {
            baseResponse.setData(null);
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatusCode(400);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUser(@RequestParam("query") String query) {
        List<UserResponse> userList = userServiceImp.searchUserByNameOrEmail(query.toLowerCase());
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getProductByCategory(@PathVariable String email) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(userServiceImp.getUserByemail(email));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }





    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequest request,
                                            @RequestHeader("Authorization") String token) {

        BaseResponse response = new BaseResponse();

        try {
            String email = jwtHelper.getUsernameFromToken(token);

            userService.changePassword(email, request.getCurrentPassword(), request.getNewPassword());

            response.setStatusCode(200);
            response.setMessage("Đổi mật khẩu thành công");

        } catch (UsernameNotFoundException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (InvalidPasswordException e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());

        }

        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));

    }



}
