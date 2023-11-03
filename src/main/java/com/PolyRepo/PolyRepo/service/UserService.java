package com.PolyRepo.PolyRepo.service;


import com.PolyRepo.PolyRepo.Entity.CommentEntity;
import com.PolyRepo.PolyRepo.Entity.PostEntity;
import com.PolyRepo.PolyRepo.Entity.RoleEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.SignupRequest;
import com.PolyRepo.PolyRepo.payload.request.UserRequest;
import com.PolyRepo.PolyRepo.payload.response.*;
import com.PolyRepo.PolyRepo.repository.CommentRepository;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository roleRepository;

    @Override
    public boolean addUser(SignupRequest request) {
        Optional<UserEntity> existingUser = userRepository.findByEmailIgnoreCase(request.getEmail());
        if (existingUser.isPresent()) {
//            throw new BaseResponse("False","email đã tồn tại","null", ErrorConstants.EMAIL_ALREADY_USED_TYPE); // Hoặc ném một ngoại lệ khác phù hợp
        }
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
            throw new RuntimeException("Lỗi không xác định");
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



    @Override
    public UserResponse getUserById(Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        // Chuyển đổi đối tượng User thành UserResponse để trả về
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.get().getId());
        userResponse.setEmail(user.get().getEmail());
        userResponse.setName(user.get().getUsername());
       userResponse.setRoleId(user.get().getId());
        return userResponse;
    }

    @Override
    public void deleteUserById(Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("Không tìm thấy comment với ID: " + id));
        userRepository.delete(userEntity);
    }

    public List<UserResponse> searchUserByNameOrEmail(String query) {
        try {
            List<UserResponse> listUser = new ArrayList<>();
            List<UserEntity> userEntityList = userRepository.searchUserByNameOrEmail(query);
            for (UserEntity item : userEntityList) {
                UserResponse user = new UserResponse();
                user.setId(item.getId());
                user.setName(item.getUsername());
                user.setEmail(item.getEmail());
                user.setRoleId(item.getRole().getId());
                listUser.add(user);
            }
            return listUser;
        } catch (Exception e) {
            throw new CustomException("Lỗi khi tìm kiếm người dùng " + e.getMessage());
        }

    }


    @Override
    public UserResponse updateUser(Integer id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User with ID " + id + " not found"));

        RoleEntity roleEntity = roleRepository.findById(userRequest.getRoleId())
                .orElseThrow(() -> new CustomException("Role with ID " + userRequest.getRoleId() + " not found")).getRole();

        userEntity.setRole(roleEntity);
        userEntity.setUsername(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
//        userEntity.setPasswords(userRequest.getPassword());
        userEntity.setPasswords(passwordEncoder.encode(userRequest.getPassword()));

        UserEntity updatedUser = userRepository.save(userEntity);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(updatedUser.getId());
        userResponse.setName(updatedUser.getUsername());

        userResponse.setRoleId(updatedUser.getRole().getId());
        userResponse.setEmail(updatedUser.getEmail());

        return userResponse;
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
