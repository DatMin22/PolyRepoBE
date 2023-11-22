package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.CateRequest;
import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.payload.response.CateResponse;
import com.PolyRepo.PolyRepo.service.imp.CateServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/cate")
@CrossOrigin("*")

public class CateController {
    @Autowired
    CateServiceImp cateServiceImp;
    @GetMapping("/getAll")
    public ResponseEntity<?> getallCate() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(cateServiceImp.getAllCate());
        baseResponse.setMessage("Get All cate");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

//    @GetMapping("/delete/{id}")
//    public ResponseEntity<?> deletecate(int id) {
//        BaseResponse response = new BaseResponse();
//        response.setStatusCode(200);
//        response.setMessage("abc");
//        response.setData(cateServiceImp.deletecate(id));
//        return new ResponseEntity<>("", HttpStatus.OK);
//    }
    @PostMapping("/add")
    public ResponseEntity<?> addCate(@RequestBody CateRequest cateRequest) {
        BaseResponse baseResponse = new BaseResponse();
        try {

            CateResponse cateResponse= cateServiceImp.addCate(cateRequest);
            baseResponse.setData(cateResponse);
            baseResponse.setMessage("Cate added successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (CustomException e) {
            baseResponse.setData(null);
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatusCode(400);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCateById(@PathVariable("id") Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            cateServiceImp.deleteCatetById(id);
            baseResponse.setMessage("Cate deleted successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (CustomException e) {
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatusCode(400);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CateResponse> updateCate(@PathVariable("id") Integer id, @RequestBody CateRequest cateRequest) {
        CateResponse updatedCate = cateServiceImp.updateCate(id, cateRequest.getName(),cateRequest.getShorts());
        return ResponseEntity.ok(updatedCate);
    }
}
