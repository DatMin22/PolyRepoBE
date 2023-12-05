package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.exception.CustomFileNotFoundException;
import com.PolyRepo.PolyRepo.payload.request.CommentRequest;
import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.payload.response.CommentResponse;
import com.PolyRepo.PolyRepo.service.CommentService;
import com.PolyRepo.PolyRepo.service.imp.CommentServiceImp;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    CommentServiceImp commentServiceImp;
    @Autowired
    CommentService commentService;
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllComments() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(commentServiceImp.getAllComments());
        baseResponse.setMessage("Get All Comment");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(commentServiceImp.getCommentById(id));
        baseResponse.setMessage("Get All Comment");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?>getCommentByPostId(@PathVariable int id){
        BaseResponse response=new BaseResponse();
        response.setStatusCode(200);
        response.setData(commentServiceImp.getCommentByPostId(id));

        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addComment( @RequestBody CommentRequest commentRequest) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            CommentResponse addedComment = commentService.addComment(commentRequest);
            baseResponse.setData(addedComment);
            baseResponse.setMessage("Comment added successfully");
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
    public ResponseEntity<?> deleteCommentById(@PathVariable("id") Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            commentService.deleteCommentById(id);
            baseResponse.setMessage("Comment deleted successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (CustomException e) {
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatusCode(400);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

@PutMapping("/update/{id}")
public BaseResponse updateComment(@PathVariable("id") Integer id, @RequestBody CommentRequest commentRequest) {
    if(StringUtils.isEmpty(commentRequest.getContent())) {

        BaseResponse response = new BaseResponse();
        response.setStatusCode(400);
        response.setMessage("Bình luận không được để trống");

        return response;
    }
    try {
        CommentResponse updatedComment = commentServiceImp.updateComment(id, commentRequest.getContent());

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Cập nhật bình luận thành công");
        response.setData(updatedComment);

        return response;

    } catch (Exception e) {

        BaseResponse response = new BaseResponse();
        response.setStatusCode(400);
        response.setMessage("Lỗi xảy ra khi cập nhật bình luận");

        return response;
    }

}

}
