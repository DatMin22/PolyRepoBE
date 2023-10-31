package com.PolyRepo.PolyRepo.service;


import com.PolyRepo.PolyRepo.Entity.CommentEntity;
import com.PolyRepo.PolyRepo.Entity.PostEntity;
import com.PolyRepo.PolyRepo.Entity.RoleEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.CommentRequest;
import com.PolyRepo.PolyRepo.payload.request.SignupRequest;
import com.PolyRepo.PolyRepo.payload.response.CommentResponse;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;
import com.PolyRepo.PolyRepo.repository.CommentRepository;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CommentRepository commentRepository;


    @Override
    public boolean addUser(SignupRequest request) {
        boolean isSuccess = false;
        RoleEntity role=new RoleEntity();
        try{
            UserEntity user = new UserEntity();
            
            user.setUsername(request.getUsername());
            user.setPasswords(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
            user.setRole(new RoleEntity());
            user.getRole().setId(1);
            userRepository.save(user);
            isSuccess = true;

        }catch (Exception e){

        }


        return isSuccess;
    }

    @Override
    public List<UserResponse> getAllUser() {
        try {
            List<UserResponse> listUser = new ArrayList<>();
            List<UserEntity> userEntityList = userRepository.findAll();
            for (UserEntity item :
                    userEntityList) {
                UserResponse user = new UserResponse();
                user.setId(item.getId());
                user.setName(item.getUsername());
                user.setRoleId(item.getRole().getId());
                user.setEmail(item.getEmail());
                listUser.add(user);
            }
            return listUser;
        } catch (Exception e) {
            throw new CustomException("Lỗi add user " + e.getMessage());
        }
    }



}
