package com.PolyRepo.PolyRepo.service;

import com.PolyRepo.PolyRepo.Entity.CategoryEntity;
import com.PolyRepo.PolyRepo.Entity.LikeEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.response.CateResponse;
import com.PolyRepo.PolyRepo.payload.response.LikeResponse;
import com.PolyRepo.PolyRepo.repository.CateRepository;
import com.PolyRepo.PolyRepo.repository.LikeRepository;
import com.PolyRepo.PolyRepo.service.imp.CateServiceImp;
import com.PolyRepo.PolyRepo.service.imp.LikeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeService implements LikeServiceImp {
    @Autowired
    private LikeRepository likeRepository;


    @Override
    public List<LikeResponse> getAlllike() {
        try {
            List<LikeResponse> listlike = new ArrayList<>();
            List<LikeEntity> like = likeRepository.findAll();
            for (LikeEntity item :
                    like) {
                LikeResponse likes = new LikeResponse();
                likes.setId(item.getId());
                likes.setLikeStatus(item.getLikeStatus());
                likes.setUser_id(item.getUser().getId());
                likes.setPost_id(item.getPosts().getId());
                listlike.add(likes);
            }
            return listlike;
        } catch (Exception e) {
            throw new CustomException("Lỗi get all likes " + e.getMessage());
        }
    }
}
