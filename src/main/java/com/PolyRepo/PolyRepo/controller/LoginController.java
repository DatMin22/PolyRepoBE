package com.PolyRepo.PolyRepo.controller;


import com.PolyRepo.PolyRepo.payload.request.SignupRequest;
import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.payload.response.ErrorResponse;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import com.PolyRepo.PolyRepo.utils.JwtHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@CrossOrigin("*")

public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserServiceImp userServiceImp;

    /*
    {
        "statusCode" : 200
        "message" : ""
        "data" : kiểu gì cũng được
     }
     */


    @RequestMapping(value = "signin",method = RequestMethod.POST)
    public ResponseEntity<?> signin(

            @RequestParam String email, @RequestParam String password){

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(email,password);
        authenticationManager.authenticate(token);

        String jwt = jwtHelper.generateToken(email);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(jwt);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "signup",method = RequestMethod.POST)
    public ResponseEntity<?> signup(@Valid SignupRequest request){
        boolean isSuccess = userServiceImp.addUser(request);

        if (isSuccess) {
            BaseResponse response = new BaseResponse();
            response.setStatusCode(200);
            response.setData(isSuccess);
            response.setMessage("Đăng ký thành công");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            BaseResponse response = new BaseResponse();
            response.setStatusCode(400);
            if (request.getPassword().length() < 8) {
                response.setMessage("mật khẩu phải có ít nhất 8 ký tự");
            } else {
                response.setMessage("Email đã tồn tại");
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
