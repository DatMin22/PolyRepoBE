package com.PolyRepo.PolyRepo.service;

import com.PolyRepo.PolyRepo.Entity.CategoryEntity;
import com.PolyRepo.PolyRepo.Entity.LikeEntity;
import com.PolyRepo.PolyRepo.Entity.PostEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.LikeRequest;
import com.PolyRepo.PolyRepo.payload.response.CateResponse;
import com.PolyRepo.PolyRepo.payload.response.LikeResponse;
import com.PolyRepo.PolyRepo.repository.CateRepository;
import com.PolyRepo.PolyRepo.repository.LikeRepository;
import com.PolyRepo.PolyRepo.repository.PostRepository;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import com.PolyRepo.PolyRepo.service.imp.CateServiceImp;
import com.PolyRepo.PolyRepo.service.imp.LikeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikeService implements LikeServiceImp {

        @Autowired
        private LikeRepository likeRepository;
        @Autowired
        private PostRepository postRepository;
        @Autowired
        private UserRepository userRepository;
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

        @Override
        public LikeResponse addLike(LikeRequest likeRequest) {
            try {
                LikeEntity likeEntity = new LikeEntity();
                likeEntity.setLikeStatus(likeRequest.getLikeStatus());
                likeEntity.setLikeStatus("true");
                // Tìm đối tượng PostEntity từ cơ sở dữ liệu
                PostEntity post=  postRepository.findById(likeRequest.getPost_id())
                        .orElseThrow(() -> new CustomException("Không tìm thấy người dùng với ID: " + likeRequest.getPost_id()));

                likeEntity.setPosts(post);

                // Tìm đối tượng UserEntity từ cơ sở dữ liệu
                UserEntity userEntity = userRepository.findById(likeRequest.getUser_id())
                        .orElseThrow(() -> new CustomException("Không tìm thấy người dùng với ID: " + likeRequest.getUser_id()));
                likeEntity.setUser(userEntity);
                LikeEntity savedLike = likeRepository.save(likeEntity);
                LikeResponse likeResponse = new LikeResponse();
                likeResponse.setId(savedLike.getId()); // Đặt giá trị cho thuộc tính id
                likeResponse.setUser_id(savedLike.getUser().getId());
                likeResponse.setPost_id(savedLike.getPosts().getId());
                likeResponse.setLikeStatus(savedLike.getLikeStatus());
                return likeResponse;
            } catch (Exception e) {
                throw new CustomException("Lỗi " + e.getMessage());

            }
        }

        @Override
        public LikeResponse getLikeById(Integer id) {
            Optional<LikeEntity> like = likeRepository.findById(id);
            // Chuyển đổi đối tượng Comment thành CommentResponse để trả về
            LikeResponse likeResponse = new LikeResponse();
            likeResponse.setId(like.get().getId());
            likeResponse.setUser_id(like.get().getUser().getId());
            likeResponse.setPost_id(like.get().getPosts().getId());
            likeResponse.setLikeStatus(like.get().getLikeStatus());

            return likeResponse;    }
        @Override
        public List<LikeResponse> getLikeByUserId(int id) {
            List<LikeEntity>list=likeRepository.findByUserId(id);
            List<LikeResponse> listResponse=new ArrayList<>();
            for (LikeEntity data: list){
                LikeResponse likeResponse=new LikeResponse();
                likeResponse.setId(data.getId());
                likeResponse.setLikeStatus(data.getLikeStatus());
                likeResponse.setPost_id(data.getPosts().getId());
                likeResponse.setUser_id(data.getUser().getId());

                listResponse.add(likeResponse);
            }
            return listResponse;
        }

        @Override
        public List<LikeResponse> getLikeByPostId(int id) {
            List<LikeEntity>list=likeRepository.findByPostsId(id);
            List<LikeResponse> listResponse=new ArrayList<>();
            for (LikeEntity data: list){
                LikeResponse likeResponse=new LikeResponse();
                likeResponse.setId(data.getId());
                likeResponse.setLikeStatus(data.getLikeStatus());
                likeResponse.setPost_id(data.getPosts().getId());
                likeResponse.setUser_id(data.getUser().getId());

                listResponse.add(likeResponse);
            }
            return listResponse;
        }

        @Override
        public void deleteLikeById(Integer id) {
            LikeEntity like = likeRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Không tìm thấy post với ID: " + id));
            likeRepository.delete(like);
        }


    }




