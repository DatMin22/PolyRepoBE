package com.PolyRepo.PolyRepo.service;


import com.PolyRepo.PolyRepo.Entity.PostEntity;
import com.PolyRepo.PolyRepo.Entity.RoleEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.SignupRequest;
import com.PolyRepo.PolyRepo.payload.response.PostResponse;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;
import com.PolyRepo.PolyRepo.repository.CommentRepository;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



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

    public List<UserResponse> searchUserByNameOrEmail(String query) {
        try {
            List<UserResponse> listUser = new ArrayList<>();
            List<UserEntity> userEntityList = userRepository.searchUserByNameOrEmail(query);
            for (UserEntity item : userEntityList) {
                UserResponse user = new UserResponse();
                user.setId(item.getId());
                user.setName(item.getUsername());
                user.setRoleId(item.getRole().getId());
                listUser.add(user);
            }
            return listUser;
        } catch (Exception e) {
            throw new CustomException("Lỗi khi tìm kiếm người dùng " + e.getMessage());
        }

    }

    @Override
    public List<UserResponse> getUserByemail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        // Tạo một danh sách mới có chứa đối tượng UserEntity đó.
        List<UserEntity> list = Arrays.asList(userEntity);

        List<UserResponse> listResponse = new ArrayList<>();
        for (UserEntity data : list) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(data.getId());
            userResponse.setEmail(data.getEmail());
            userResponse.setName(data.getUsername());
            userResponse.setRoleId(data.getRole().getId());
            listResponse.add(userResponse);
        }
        return listResponse;
    }

}
