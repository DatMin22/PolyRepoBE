package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.ShareRequest;
import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.payload.response.ShareResponse;
import com.PolyRepo.PolyRepo.service.imp.ShareServiceImp;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/share")
@CrossOrigin("*")
public class ShareController {
    @Autowired
    ShareServiceImp shareServiceImp;

    @GetMapping("/getall")
    public ResponseEntity<?> getAllShare() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(shareServiceImp.getAllShare());
        baseResponse.setMessage("Get All Share");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addShare(@RequestBody ShareRequest shareRequest) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            ShareResponse addedComment = shareServiceImp.addShare(shareRequest);
            baseResponse.setData(addedComment);
            baseResponse.setMessage("This Share had been added successfully");
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
    public ResponseEntity<?> deleteShareById(@PathVariable("id") Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            shareServiceImp.deleteShareByID(id);
            baseResponse.setMessage("This Share had been deleted successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (CustomException e) {
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatusCode(400);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/update")
//    public ResponseEntity<?> updateShareByID(@RequestBody ShareRequest shareRequest) {
//        BaseResponse baseResponse = new BaseResponse();
//        try {
//            ShareResponse addedComment = shareServiceImp.addShare(shareRequest);
//            baseResponse.setData(addedComment);
//            baseResponse.setMessage("Share added successfully");
//            baseResponse.setStatusCode(200);
//            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
//        } catch (CustomException e) {
//            baseResponse.setData(null);
//            baseResponse.setMessage(e.getMessage());
//            baseResponse.setStatusCode(400);
//            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
//        }
//    }
}
