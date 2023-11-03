package com.PolyRepo.PolyRepo.service.imp;

import com.PolyRepo.PolyRepo.payload.request.CommentRequest;
import com.PolyRepo.PolyRepo.payload.response.CommentResponse;
import com.PolyRepo.PolyRepo.payload.response.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentServiceImp {
    List<CommentResponse> getCommentByPostId(int id);
    List<CommentResponse> getAllComments();
    CommentResponse getCommentById(Integer id);
    CommentResponse addComment(CommentRequest commentRequest);
    void deleteCommentById(Integer id);
    CommentResponse updateComment(Integer id, String content);

}
