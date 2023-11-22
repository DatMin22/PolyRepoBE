package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.service.imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RoleController {
    @Autowired
    RoleServiceImp roleServiceImp;

    @GetMapping("/getall")
    public ResponseEntity<?> getAllRole() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(roleServiceImp.getAllRole());
        baseResponse.setMessage("Get All role");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
