package com.PolyRepo.PolyRepo.service;


import com.PolyRepo.PolyRepo.Entity.RoleEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.exception.InvalidPasswordException;
import com.PolyRepo.PolyRepo.payload.request.SignupRequest;
import com.PolyRepo.PolyRepo.payload.request.UserRequest;
import com.PolyRepo.PolyRepo.payload.response.*;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import com.PolyRepo.PolyRepo.repository.RoleRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private RoleRepository roleRepository;

    @Override
    public boolean addUser(SignupRequest request) {

        Optional<UserEntity> existingUser = userRepository.findOneByEmailIgnoreCase(request.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("email đã tồn tại"); // Hoặc ném một ngoại lệ khác phù hợp
        }
        boolean isSuccess = false;
        RoleEntity role = new RoleEntity();

        try {
            UserEntity user = new UserEntity();

            user.setUsername(request.getUsername());
            user.setPasswords(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
            user.setRole(new RoleEntity());
            user.getRole().setId(2);
            userRepository.save(user);
            isSuccess = true;

        } catch (Exception e) {
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
                .orElseThrow(() -> new CustomException("Không tìm thấy user với ID: " + id));
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
                .orElseThrow(() -> new CustomException("User not found"));
        System.out.println("roleid:   "+ userRequest.getRoleId());

        RoleEntity roleEntity = roleRepository
                .findById(userRequest.getRoleId())
                .orElseThrow(() -> new CustomException("Role not found"));

        String currentEmail = userEntity.getEmail();

        if(!currentEmail.equals(userRequest.getEmail())) {

            Optional<UserEntity> existingUser = userRepository.findOneByEmailIgnoreCase(userRequest.getEmail());

            if(existingUser.isPresent()) {
                throw new CustomException("Email đã tồn tại");
            }
        }

        userEntity.setRole(roleEntity);
        userEntity.setUsername(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        UserEntity updatedUser = userRepository.save(userEntity);
//<<<<<<< HEAD
//        UserResponse userResponse = new UserResponse();
//        userResponse.setId(updatedUser.getId());
//        userResponse.setName(updatedUser.getUsername());
//        userResponse.setRoleId(updatedUser.getRole().getId());
//        userResponse.setEmail(updatedUser.getEmail());
//=======

        UserResponse response = new UserResponse();
        response.setId(updatedUser.getId());
        response.setName(updatedUser.getUsername());
        response.setRoleId(updatedUser.getRole().getId());
        response.setEmail(updatedUser.getEmail());

        return response;

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


    @Override
    public void changePassword(String email, String currentPassword, String newPassword) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Không tìm thấy user với email này");
        }
        if (!passwordEncoder.matches(currentPassword, user.getPasswords())) {
            throw new InvalidPasswordException("Mật khẩu hiện tại không chính xác");
        }
        int minPasswordLength = 8; // Độ dài tối thiểu của mật khẩu
        if (newPassword.length() < minPasswordLength) {
            throw new IllegalArgumentException("Mật khẩu mới phải có ít nhất " + minPasswordLength + " ký tự");
        }
        String hashedNewPassword = passwordEncoder.encode(newPassword);
        user.setPasswords(hashedNewPassword);
        userRepository.save(user);
    }

    private boolean checkPassword(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


}
