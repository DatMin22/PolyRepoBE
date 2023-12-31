package com.PolyRepo.PolyRepo.service.imp;

//import com.cybersoft.cozastore_java21.payload.request.SignupRequest;

import com.PolyRepo.PolyRepo.payload.request.SignupRequest;
import com.PolyRepo.PolyRepo.payload.request.UserRequest;
import com.PolyRepo.PolyRepo.payload.response.CommentResponse;
import com.PolyRepo.PolyRepo.payload.response.PostResponse;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserServiceImp {
    boolean addUser(SignupRequest request);

    List<UserResponse> getAllUser();

    UserResponse getUserById(Integer id);

    void deleteUserById(Integer id);

    UserResponse updateUser(Integer id, UserRequest userRequest);

    //    void getUserByEmail(String email);
//List<UserResponse> getUserByID(int id);
    List<UserResponse> getUserByemail(String email);


    List<UserResponse> searchUserByNameOrEmail(String query);

    void changePassword(String username, String currentPassword, String newPassword);
}

