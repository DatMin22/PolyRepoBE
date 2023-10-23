package com.PolyRepo.PolyRepo.service;


import com.PolyRepo.PolyRepo.Entity.RoleEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.payload.request.SignupRequest;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
