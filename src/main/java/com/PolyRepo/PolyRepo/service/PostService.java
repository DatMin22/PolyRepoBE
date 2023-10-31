package com.PolyRepo.PolyRepo.service;

import com.PolyRepo.PolyRepo.Entity.CategoryEntity;
import com.PolyRepo.PolyRepo.Entity.PostEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.PostRequest;
import com.PolyRepo.PolyRepo.payload.response.PostResponse;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;
import com.PolyRepo.PolyRepo.repository.CateRepository;
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
    @Autowired
    private CateRepository cateRepository;
    @Autowired
    private UserRepository userRepository;
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
                post.setCategoryId(item.getCategory().getId());
                post.setPostStatus(item.getPoststatus());
                post.setFilename(item.getFilename());
                listPost.add(post);
            }
            return listPost;
        } catch (Exception e) {
            throw new CustomException("Lỗi getallPost " + e.getMessage());
        }
    }

    @Override

    public PostResponse addPost(PostRequest postRequest) {

        try {
            PostEntity postEntity = new PostEntity();
            postEntity.setTitle(postRequest.getTitle());
            postEntity.setDescriptions(postRequest.getDescription());
            postEntity.setPoststatus("true");
            postEntity.setFilename(postRequest.getFilename());
            // Tìm đối tượng PostEntity từ cơ sở dữ liệu
            CategoryEntity category= cateRepository.findById(postRequest.getCategory_id())
                   ;
            postEntity.setCategory(category);

            // Tìm đối tượng UserEntity từ cơ sở dữ liệu
            UserEntity userEntity = userRepository.findById(postRequest.getUser_id())
                    .orElseThrow(() -> new CustomException("Không tìm thấy người dùng với ID: " + postRequest.getUser_id()));
            postEntity.setUser(userEntity);
            PostEntity savedPost = postRepository.save(postEntity);
            PostResponse postResponse = new PostResponse();
            postResponse.setId(savedPost.getId()); // Đặt giá trị cho thuộc tính id
            postResponse.setTitle(savedPost.getTitle());
            postResponse.setDescription(savedPost.getDescriptions());
            postResponse.setPostStatus(savedPost.getPoststatus());
            postResponse.setFilename(savedPost.getFilename());
            postResponse.setCategoryId(savedPost.getCategory().getId());
            postResponse.setUserId(savedPost.getUser().getId());
                return postResponse;
            } catch (Exception e) {
                throw new CustomException("Lỗi " + e.getMessage());
    
            }
    }



    @Override
    public List<PostResponse> getPostByID(int id) {
        List<PostEntity>list=postRepository.findById(id);
        List<PostResponse> listResponse=new ArrayList<>();
        for (PostEntity data: list){
            PostResponse postResponse=new PostResponse();
            postResponse.setId(data.getId());
            postResponse.setCategoryId(data.getCategory().getId());
            postResponse.setUserId(data.getUser().getId());
            postResponse.setPostStatus(data.getPoststatus());
            postResponse.setTitle(data.getTitle());
            postResponse.setFilename(data.getFilename());
            listResponse.add(postResponse);
        }
        return listResponse;
    }

    @Override
    public List<PostResponse> getPostByCateId(int id) {
        List<PostEntity>list=postRepository.findByCategoryId(id);
        List<PostResponse> listResponse=new ArrayList<>();
        for (PostEntity data: list){
            PostResponse postResponse=new PostResponse();
            postResponse.setId(data.getId());
            postResponse.setTitle(data.getTitle());
            postResponse.setDescription(data.getDescriptions());
            postResponse.setUserId(data.getUser().getId());
            postResponse.setCategoryId(data.getCategory().getId());
            postResponse.setFilename(data.getFilename());
            postResponse.setPostStatus(data.getPoststatus());
            listResponse.add(postResponse);
        }
        return listResponse;
    }


}
