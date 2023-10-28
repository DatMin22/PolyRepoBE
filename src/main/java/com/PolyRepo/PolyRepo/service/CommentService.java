package com.PolyRepo.PolyRepo.service;

import com.PolyRepo.PolyRepo.Entity.CommentEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.response.CommentResponse;
import com.PolyRepo.PolyRepo.repository.CommentRepository;
import com.PolyRepo.PolyRepo.service.imp.CommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService implements CommentServiceImp {
    @Autowired
    private  CommentRepository commentRepository;

    @Override
    public List<CommentResponse> getAllComments() {
        try {
            List<CommentResponse> listComment = new ArrayList<>();
            List<CommentEntity> commentEntityList = commentRepository.findAll();
            for (CommentEntity item : commentEntityList) {
                CommentResponse comment = new CommentResponse();
                comment.setId(item.getId());
                comment.setContent(item.getContent()); // Sửa lại thành getContent()
                comment.setCommentstatus(item.getCommentstatus());
                comment.setPost_id(item.getPost().getId());
                comment.setUser_id(item.getUser().getId());
                listComment.add(comment);
            }
            return listComment;
        } catch (Exception e) {
            throw new CustomException("Lỗi  " + e.getMessage());
        }
    }

}
