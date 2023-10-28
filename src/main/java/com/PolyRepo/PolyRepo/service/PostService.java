package com.PolyRepo.PolyRepo.service;

import com.PolyRepo.PolyRepo.Entity.PostEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.response.PostResponse;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;
import com.PolyRepo.PolyRepo.repository.PostRepository;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import com.PolyRepo.PolyRepo.service.imp.PostServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PostService implements PostServiceImp {

    @Autowired
    private PostRepository postRepository;
    @Override
    public List<PostResponse> getAllPost() {
        try {
            List<PostResponse> listPost = new ArrayList<>();


            List<PostEntity> postEntityList = postRepository.findAll();
            for (PostEntity item : postEntityList) {
                PostResponse post = new PostResponse();
                post.setId(item.getId());
                post.setDescription(item.getDescriptions());
//                post.setPostStatus(item.getPoststatus());
                post.setTitle(item.getTitle());
                post.setUserId(item.getUser().getId());

                post.setPostStatus(item.getPoststatus());
                listPost.add(post);
            }
            return listPost;
        } catch (Exception e) {
            throw new CustomException("Lỗi getallPost " + e.getMessage());
        }
    }
}
