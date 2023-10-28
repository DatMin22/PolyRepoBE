package com.PolyRepo.PolyRepo.service.imp;

import com.PolyRepo.PolyRepo.payload.response.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentServiceImp {
    List<CommentResponse> getAllComments();

}
